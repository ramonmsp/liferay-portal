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

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.WorkflowInstanceLink;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Feliphe Marinho
 */
public class WorkflowStatusTaglibHelper {

	public static final String ATTRIBUTE_NAMESPACE =
		"liferay-portal-workflow:status:";

	public WorkflowStatusTaglibHelper(
		HttpServletRequest httpServletRequest, ResourceBundle resourceBundle) {

		_httpServletRequest = httpServletRequest;
		_resourceBundle = resourceBundle;
	}

	public String getAdditionalText() {
		if ((getStatus() == WorkflowConstants.STATUS_PENDING) &&
			(getBean() != null) && (getModel() != null)) {

			try {
				Class<?> model = getModel();

				String workflowStatus =
					WorkflowInstanceLinkLocalServiceUtil.getState(
						BeanPropertiesUtil.getLong(getBean(), "companyId"),
						BeanPropertiesUtil.getLong(getBean(), "groupId"),
						model.getName(),
						BeanPropertiesUtil.getLong(getBean(), "primaryKey"));

				return StringBundler.concat(
					StringPool.SPACE, StringPool.OPEN_PARENTHESIS,
					HtmlUtil.escape(
						LanguageUtil.get(_resourceBundle, workflowStatus)),
					StringPool.CLOSE_PARENTHESIS);
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(portalException.getMessage(), portalException);
				}
			}
		}

		return StringPool.BLANK;
	}

	public Object getBean() {
		if (_bean == null) {
			_bean = _getAttribute(Property.BEAN);
		}

		return _bean;
	}

	public String getHelpMessage() {
		if (_helpMessage == null) {
			_helpMessage = GetterUtil.getString(
				(String)_getAttribute(Property.HELP_MESSAGE));
		}

		return _helpMessage;
	}

	public String getId() {
		if (_id == null) {
			_id = GetterUtil.getString((String)_getAttribute(Property.ID));
		}

		return _id;
	}

	public Long getInstanceId() {
		if ((getBean() != null) && (getModel() != null)) {
			try {
				Class<?> model = getModel();

				WorkflowInstanceLink workflowInstanceLink =
					WorkflowInstanceLinkLocalServiceUtil.
						getWorkflowInstanceLink(
							BeanPropertiesUtil.getLong(getBean(), "companyId"),
							BeanPropertiesUtil.getLong(getBean(), "groupId"),
							model.getName(),
							BeanPropertiesUtil.getLong(
								getBean(), "primaryKey"));

				return workflowInstanceLink.getWorkflowInstanceId();
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(portalException.getMessage(), portalException);
				}
			}
		}

		return 0L;
	}

	public Class<?> getModel() {
		if (_model == null) {
			_model = (Class<?>)_getAttribute(Property.MODEL);
		}

		return _model;
	}

	public boolean getShowHelpMessage() {
		return GetterUtil.getBoolean(
			String.valueOf(_getAttribute(Property.SHOW_HELP_MESSAGE)), true);
	}

	public boolean getShowIcon() {
		return GetterUtil.getBoolean(
			String.valueOf(_getAttribute(Property.SHOW_ICON)), true);
	}

	public boolean getShowLabel() {
		return GetterUtil.getBoolean(
			String.valueOf(_getAttribute(Property.SHOW_LABEL)), true);
	}

	public Integer getStatus() {
		if (_status == null) {
			_status = GetterUtil.getInteger(
				String.valueOf(_getAttribute(Property.STATUS)));
		}

		return _status;
	}

	public String getStatusMessage() {
		if (_statusMessage == null) {
			_statusMessage = GetterUtil.getString(
				(String)_getAttribute(Property.STATUS_MESSAGE));

			if (Validator.isNull(
					GetterUtil.getString(
						(String)_getAttribute(Property.STATUS_MESSAGE)))) {

				_statusMessage = WorkflowConstants.getStatusLabel(getStatus());
			}
		}

		return _statusMessage;
	}

	public String getVersion() {
		if (_version == null) {
			_version = GetterUtil.getString(
				(String)_getAttribute(Property.VERSION));
		}

		return _version;
	}

	public enum Property {

		BEAN("bean"), HELP_MESSAGE("helpMessage"), ID("id"),
		MARKUP_VIEW("markupView"), MODEL("model"),
		SHOW_HELP_MESSAGE("showHelpMessage"), SHOW_ICON("showIcon"),
		SHOW_LABEL("showLabel"), STATUS("status"),
		STATUS_MESSAGE("statusMessage"), VERSION("version");

		public String getReference() {
			return ATTRIBUTE_NAMESPACE + _value;
		}

		public String getValue() {
			return _value;
		}

		private Property(String value) {
			_value = value;
		}

		private final String _value;

	}

	private Object _getAttribute(Property property) {
		return _httpServletRequest.getAttribute(property.getReference());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WorkflowStatusTaglibHelper.class);

	private Object _bean;
	private String _helpMessage;
	private final HttpServletRequest _httpServletRequest;
	private String _id;
	private Class<?> _model;
	private final ResourceBundle _resourceBundle;
	private Integer _status;
	private String _statusMessage;
	private String _version;

}