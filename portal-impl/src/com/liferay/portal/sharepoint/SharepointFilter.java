/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.sharepoint;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.Authenticator;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SharepointFilter extends BasePortalFilter {

	protected boolean isSharepointRequest(String uri) {
		if (uri == null) {
			return false;
		}

		if (uri.endsWith("*.asmx")) {
			return true;
		}

		for (String prefix : _PREFIXES) {
			if (uri.startsWith(prefix)) {
				return true;
			}
		}

		return false;
	}

	protected User login(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		User user = null;

		// Get the Authorization header, if one was supplied

		String authorization = request.getHeader("Authorization");

		if (authorization == null) {
			return user;
		}

		StringTokenizer st = new StringTokenizer(authorization);

		if (!st.hasMoreTokens()) {
			return user;
		}

		String basic = st.nextToken();

		// We only handle HTTP Basic authentication

		if (!basic.equalsIgnoreCase(HttpServletRequest.BASIC_AUTH)) {
			return user;
		}

		String encodedCredentials = st.nextToken();

		if (_log.isDebugEnabled()) {
			_log.debug("Encoded credentials are " + encodedCredentials);
		}

		String decodedCredentials = new String(
			Base64.decode(encodedCredentials));

		if (_log.isDebugEnabled()) {
			_log.debug("Decoded credentials are " + decodedCredentials);
		}

		int pos = decodedCredentials.indexOf(StringPool.COLON);

		if (pos == -1) {
			return user;
		}

		Company company = PortalUtil.getCompany(request);

		String login = GetterUtil.getString(
			decodedCredentials.substring(0, pos));
		long userId = GetterUtil.getLong(login);
		String password = decodedCredentials.substring(pos + 1);

		Map<String, String[]> headerMap = new HashMap<String, String[]>();
		Map<String, String[]> parameterMap = new HashMap<String, String[]>();

		int authResult = Authenticator.FAILURE;

		if (company.getAuthType().equals(CompanyConstants.AUTH_TYPE_EA)) {
			authResult = UserLocalServiceUtil.authenticateByEmailAddress(
				company.getCompanyId(), login, password, headerMap,
				parameterMap);

			userId = UserLocalServiceUtil.getUserIdByEmailAddress(
				company.getCompanyId(), login);
		}
		else if (company.getAuthType().equals(CompanyConstants.AUTH_TYPE_SN)) {
			authResult = UserLocalServiceUtil.authenticateByScreenName(
				company.getCompanyId(), login, password, headerMap,
				parameterMap);

			userId = UserLocalServiceUtil.getUserIdByScreenName(
				company.getCompanyId(), login);
		}
		else if (company.getAuthType().equals(CompanyConstants.AUTH_TYPE_ID)) {
			authResult = UserLocalServiceUtil.authenticateByUserId(
				company.getCompanyId(), userId, password, headerMap,
				parameterMap);
		}

		if (authResult == Authenticator.SUCCESS) {
			user = UserLocalServiceUtil.getUser(userId);
		}

		return user;
	}

	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		String method = request.getMethod();

		String userAgent = GetterUtil.getString(
			request.getHeader(HttpHeaders.USER_AGENT));

		if ((userAgent.startsWith(
				"Microsoft Data Access Internet Publishing") ||
			 userAgent.startsWith("Microsoft Office Protocol Discovery")) &&
			method.equals(HttpMethods.OPTIONS)) {

			setOptionsHeaders(response);

			return;
		}

		if (!isSharepointRequest(request.getRequestURI())) {
			processFilter(
				SharepointFilter.class, request, response, filterChain);

			return;
		}

		if (method.equals(HttpMethods.GET) || method.equals(HttpMethods.HEAD)) {
			setGetHeaders(response);
		}
		else if (method.equals(HttpMethods.POST)) {
			setPostHeaders(response);
		}

		HttpSession session = request.getSession();

		User user = (User)session.getAttribute(WebKeys.USER);

		try {
			if (user == null) {
				user = login(request, response);

				if (user == null) {
					throw new PrincipalException("User is null");
				}

				session.setAttribute(WebKeys.USER, user);
			}

			PrincipalThreadLocal.setName(user.getUserId());

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user, false);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}
		catch (Exception e) {
			sendUnauthorized(response);

			return;
		}

		try {
			processFilter(
				SharepointFilter.class, request, response, filterChain);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void sendUnauthorized(HttpServletResponse response)
		throws IOException {

		response.setHeader("WWW-Authenticate", "BASIC realm=\"Liferay\"");

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		response.flushBuffer();
	}

	protected void setGetHeaders(HttpServletResponse response) {
		response.setContentType("text/html");

		response.setHeader(
			"Public-Extension", "http://schemas.microsoft.com/repl-2");
		response.setHeader(
			"MicrosoftSharePointTeamServices", SharepointUtil.VERSION);
		response.setHeader("Cache-Control", "no-cache");
	}

	protected void setOptionsHeaders(HttpServletResponse response) {
		response.setHeader("MS-Author-Via", "MS-FP/4.0,DAV");
		response.setHeader("MicrosoftOfficeWebServer", "5.0_Collab");
		response.setHeader(
			"MicrosoftSharePointTeamServices", SharepointUtil.VERSION);
		response.setHeader("DAV", "1,2");
		response.setHeader("Accept-Ranges", "none");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader(
			"Allow",
			"COPY, DELETE, GET, GETLIB, HEAD, LOCK, MKCOL, MOVE, OPTIONS, " +
				"POST, PROPFIND, PROPPATCH, PUT, UNLOCK");
	}

	protected void setPostHeaders(HttpServletResponse response) {
		response.setContentType("application/x-vermeer-rpc");

		response.setHeader(
			"MicrosoftSharePointTeamServices", SharepointUtil.VERSION);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Connection", "close");
	}

	private static final String[] _PREFIXES =
		new String[] {
			"/_vti_inf.html", "/_vti_bin", "/sharepoint", "/history",
			"/resources"};

	private static Log _log = LogFactoryUtil.getLog(SharepointFilter.class);

}