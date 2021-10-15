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

package com.liferay.portal.workflow.taglib.servlet.taglib;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.workflow.taglib.internal.context.util.WorkflowStatusTaglibHelper;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Feliphe Marinho
 */
public class WorkflowStatusTag extends BaseWorkflowTag {

	public Object getBean() {
		return _bean;
	}

	public String getHelpMessage() {
		return _helpMessage;
	}

	public String getId() {
		return _id;
	}

	public String getMarkupView() {
		return _markupView;
	}

	public Class<?> getModel() {
		return _model;
	}

	public Integer getStatus() {
		return _status;
	}

	public String getStatusMessage() {
		return _statusMessage;
	}

	public String getVersion() {
		return _version;
	}

	public boolean isShowHelpMessage() {
		return _showHelpMessage;
	}

	public boolean isShowIcon() {
		return _showIcon;
	}

	public boolean isShowLabel() {
		return _showLabel;
	}

	public void setBean(Object bean) {
		_bean = bean;
	}

	public void setHelpMessage(String helpMessage) {
		_helpMessage = helpMessage;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setMarkupView(String markupView) {
		_markupView = markupView;
	}

	public void setModel(Class<?> model) {
		_model = model;
	}

	public void setShowHelpMessage(boolean showHelpMessage) {
		_showHelpMessage = showHelpMessage;
	}

	public void setShowIcon(boolean showIcon) {
		_showIcon = showIcon;
	}

	public void setShowLabel(boolean showLabel) {
		_showLabel = showLabel;
	}

	public void setStatus(Integer status) {
		_status = status;
	}

	public void setStatusMessage(String statusMessage) {
		_statusMessage = statusMessage;
	}

	public void setVersion(String version) {
		_version = version;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_bean = null;
		_helpMessage = null;
		_id = null;
		_markupView = null;
		_model = null;
		_showHelpMessage = true;
		_showIcon = true;
		_showLabel = true;
		_status = null;
		_statusMessage = null;
		_version = null;
	}

	@Override
	protected String getPage() {
		if (Objects.equals(getMarkupView(), "lexicon")) {
			return _LEXICON_PAGE;
		}

		return _DEFAULT_PAGE;
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return true;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		setNamespacedAttribute(
			httpServletRequest,
			WorkflowStatusTaglibHelper.Property.BEAN.getValue(), _bean);
		setNamespacedAttribute(
			httpServletRequest,
			WorkflowStatusTaglibHelper.Property.HELP_MESSAGE.getValue(),
			_helpMessage);
		setNamespacedAttribute(
			httpServletRequest,
			WorkflowStatusTaglibHelper.Property.ID.getValue(), _id);
		setNamespacedAttribute(
			httpServletRequest,
			WorkflowStatusTaglibHelper.Property.MARKUP_VIEW.getValue(),
			_markupView);
		setNamespacedAttribute(
			httpServletRequest,
			WorkflowStatusTaglibHelper.Property.MODEL.getValue(), _model);
		setNamespacedAttribute(
			httpServletRequest,
			WorkflowStatusTaglibHelper.Property.SHOW_HELP_MESSAGE.getValue(),
			_showHelpMessage);
		setNamespacedAttribute(
			httpServletRequest,
			WorkflowStatusTaglibHelper.Property.SHOW_ICON.getValue(),
			_showIcon);
		setNamespacedAttribute(
			httpServletRequest,
			WorkflowStatusTaglibHelper.Property.SHOW_LABEL.getValue(),
			_showLabel);
		setNamespacedAttribute(
			httpServletRequest,
			WorkflowStatusTaglibHelper.Property.STATUS.getValue(), _status);
		setNamespacedAttribute(
			httpServletRequest,
			WorkflowStatusTaglibHelper.Property.STATUS_MESSAGE.getValue(),
			_statusMessage);
		setNamespacedAttribute(
			httpServletRequest,
			WorkflowStatusTaglibHelper.Property.VERSION.getValue(), _version);

		if (getBean() == null) {
			setNamespacedAttribute(
				httpServletRequest,
				WorkflowStatusTaglibHelper.Property.BEAN.getValue(),
				pageContext.getAttribute("aui:model-context:bean"));
		}

		if (Validator.isNull(getHelpMessage()) &&
			(getStatus() == WorkflowConstants.STATUS_APPROVED) &&
			Validator.isNotNull(getVersion())) {

			setNamespacedAttribute(
				httpServletRequest,
				WorkflowStatusTaglibHelper.Property.HELP_MESSAGE.getValue(),
				_HELP_MESSAGE_DEFAULT);
		}

		if (getModel() == null) {
			setNamespacedAttribute(
				httpServletRequest,
				WorkflowStatusTaglibHelper.Property.MODEL.getValue(),
				pageContext.getAttribute("aui:model-context:model"));
		}
	}

	private static final String _DEFAULT_PAGE = "/workflow_status/page.jsp";

	private static final String _HELP_MESSAGE_DEFAULT =
		"a-new-version-is-created-automatically-if-this-content-is-modified";

	private static final String _LEXICON_PAGE =
		"/workflow_status/lexicon/page.jsp";

	private Object _bean;
	private String _helpMessage;
	private String _id;
	private String _markupView;
	private Class<?> _model;
	private boolean _showHelpMessage = true;
	private boolean _showIcon = true;
	private boolean _showLabel = true;
	private Integer _status;
	private String _statusMessage;
	private String _version;

}