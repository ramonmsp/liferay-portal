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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.WorkflowInstanceLink;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.workflow.taglib.internal.constants.WorkflowStatusConstants;
import com.liferay.portal.workflow.taglib.internal.constants.WorkflowStatusWebKeys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Feliphe Marinho
 */
public class WorkflowStatusDisplayContext {

	public String getHelpMessage(HttpServletRequest httpServletRequest) {
		return GetterUtil.getString(
			_getAttribute(
				WorkflowStatusWebKeys.HELP_MESSAGE, httpServletRequest));
	}

	public String getId(HttpServletRequest httpServletRequest) {
		return GetterUtil.getString(
			_getAttribute(WorkflowStatusWebKeys.ID, httpServletRequest));
	}

	public Long getInstanceId(HttpServletRequest httpServletRequest) {
		Object bean = _getBean(httpServletRequest);
		Class<?> model = _getModel(httpServletRequest);

		if ((bean != null) && (model != null)) {
			try {
				WorkflowInstanceLink workflowInstanceLink =
					WorkflowInstanceLinkLocalServiceUtil.
						getWorkflowInstanceLink(
							BeanPropertiesUtil.getLong(bean, "companyId"),
							BeanPropertiesUtil.getLong(bean, "groupId"),
							model.getName(),
							BeanPropertiesUtil.getLong(bean, "primaryKey"));

				return workflowInstanceLink.getWorkflowInstanceId();
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(portalException.getMessage(), portalException);
				}
			}
		}

		return null;
	}

	public Map<String, Object> getProps(HttpServletRequest httpServletRequest) {
		boolean showInstanceTracker = isShowInstanceTracker(httpServletRequest);

		return HashMapBuilder.<String, Object>put(
			WorkflowStatusWebKeys.HELP_MESSAGE,
			getHelpMessage(httpServletRequest)
		).put(
			WorkflowStatusWebKeys.ID, HtmlUtil.escape(getId(httpServletRequest))
		).put(
			WorkflowStatusWebKeys.INSTANCE_ID,
			() -> {
				if (!showInstanceTracker) {
					return null;
				}

				return getInstanceId(httpServletRequest);
			}
		).put(
			WorkflowStatusWebKeys.SHOW_HELP_MESSAGE,
			isShowHelpMessage(httpServletRequest)
		).put(
			WorkflowStatusWebKeys.SHOW_INSTANCE_TRACKER, showInstanceTracker
		).put(
			WorkflowStatusWebKeys.SHOW_STATUS_LABEL,
			isShowStatusLabel(httpServletRequest)
		).put(
			WorkflowStatusWebKeys.STATUS_MESSAGE,
			getStatusMessage(httpServletRequest)
		).put(
			WorkflowStatusWebKeys.VERSION, getVersion(httpServletRequest)
		).put(
			"statusStyle",
			WorkflowConstants.getStatusStyle(getStatus(httpServletRequest))
		).build();
	}

	public Integer getStatus(HttpServletRequest httpServletRequest) {
		Object bean = _getBean(httpServletRequest);

		if (bean != null) {
			return BeanPropertiesUtil.getInteger(bean, "status");
		}

		return GetterUtil.getInteger(
			_getAttribute(WorkflowStatusWebKeys.STATUS, httpServletRequest));
	}

	public String getStatusMessage(HttpServletRequest httpServletRequest) {
		if (Validator.isNotNull(
				GetterUtil.getString(
					_getAttribute(
						WorkflowStatusWebKeys.STATUS_MESSAGE,
						httpServletRequest)))) {

			return GetterUtil.getString(
				_getAttribute(
					WorkflowStatusWebKeys.STATUS_MESSAGE, httpServletRequest));
		}

		return WorkflowConstants.getStatusLabel(getStatus(httpServletRequest));
	}

	public String getVersion(HttpServletRequest httpServletRequest) {
		return GetterUtil.getString(
			_getAttribute(WorkflowStatusWebKeys.VERSION, httpServletRequest));
	}

	public boolean isShowHelpMessage(HttpServletRequest httpServletRequest) {
		return GetterUtil.getBoolean(
			_getAttribute(
				WorkflowStatusWebKeys.SHOW_HELP_MESSAGE, httpServletRequest),
			true);
	}

	public boolean isShowInstanceTracker(
		HttpServletRequest httpServletRequest) {

		return GetterUtil.getBoolean(
			_getAttribute(
				WorkflowStatusWebKeys.SHOW_INSTANCE_TRACKER,
				httpServletRequest),
			true);
	}

	public boolean isShowStatusLabel(HttpServletRequest httpServletRequest) {
		return GetterUtil.getBoolean(
			_getAttribute(
				WorkflowStatusWebKeys.SHOW_STATUS_LABEL, httpServletRequest),
			true);
	}

	private Object _getAttribute(
		String attribute, HttpServletRequest httpServletRequest) {

		return httpServletRequest.getAttribute(
			WorkflowStatusConstants.ATTRIBUTE_NAMESPACE + attribute);
	}

	private Object _getBean(HttpServletRequest httpServletRequest) {
		return _getAttribute(WorkflowStatusWebKeys.BEAN, httpServletRequest);
	}

	private Class<?> _getModel(HttpServletRequest httpServletRequest) {
		return (Class<?>)_getAttribute(
			WorkflowStatusWebKeys.MODEL, httpServletRequest);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WorkflowStatusDisplayContext.class);

}