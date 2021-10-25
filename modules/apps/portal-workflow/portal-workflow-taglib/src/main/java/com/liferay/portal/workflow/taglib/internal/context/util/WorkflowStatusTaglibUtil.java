/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.taglib.internal.context.util;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.workflow.taglib.internal.WorkflowStatusTaglibWebKeys;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Feliphe Marinho
 */
public class WorkflowStatusTaglibUtil {

	public static final String ATTRIBUTE_NAMESPACE =
		"liferay-portal-workflow:status:";

	public static String getHelpMessage(HttpServletRequest httpServletRequest) {
		return GetterUtil.getString(
			_getAttribute(
				WorkflowStatusTaglibWebKeys.HELP_MESSAGE, httpServletRequest));
	}

	public static String getId(HttpServletRequest httpServletRequest) {
		return GetterUtil.getString(
			_getAttribute(WorkflowStatusTaglibWebKeys.ID, httpServletRequest));
	}

	public static Integer getStatus(HttpServletRequest httpServletRequest) {
		Object bean = _getBean(httpServletRequest);

		if (bean != null) {
			return BeanPropertiesUtil.getInteger(bean, "status");
		}

		return GetterUtil.getInteger(
			_getAttribute(
				WorkflowStatusTaglibWebKeys.STATUS, httpServletRequest));
	}

	public static String getStatusMessage(
		HttpServletRequest httpServletRequest) {

		if (Validator.isNotNull(
				GetterUtil.getString(
					_getAttribute(
						WorkflowStatusTaglibWebKeys.STATUS_MESSAGE,
						httpServletRequest)))) {

			return GetterUtil.getString(
				_getAttribute(
					WorkflowStatusTaglibWebKeys.STATUS_MESSAGE,
					httpServletRequest));
		}

		return WorkflowConstants.getStatusLabel(getStatus(httpServletRequest));
	}

	public static String getVersion(HttpServletRequest httpServletRequest) {
		return GetterUtil.getString(
			_getAttribute(
				WorkflowStatusTaglibWebKeys.VERSION, httpServletRequest));
	}

	public static boolean isShowHelpMessage(
		HttpServletRequest httpServletRequest) {

		return GetterUtil.getBoolean(
			_getAttribute(
				WorkflowStatusTaglibWebKeys.SHOW_HELP_MESSAGE,
				httpServletRequest),
			true);
	}

	public static boolean isShowStatusLabel(
		HttpServletRequest httpServletRequest) {

		return GetterUtil.getBoolean(
			_getAttribute(
				WorkflowStatusTaglibWebKeys.SHOW_STATUS_LABEL,
				httpServletRequest),
			true);
	}

	private static Object _getAttribute(
		String attribute, HttpServletRequest httpServletRequest) {

		return httpServletRequest.getAttribute(ATTRIBUTE_NAMESPACE + attribute);
	}

	private static Object _getBean(HttpServletRequest httpServletRequest) {
		return _getAttribute(
			WorkflowStatusTaglibWebKeys.BEAN, httpServletRequest);
	}

}