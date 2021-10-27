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

import com.liferay.portal.workflow.taglib.internal.constants.WorkflowStatusConstants;
import com.liferay.portal.workflow.taglib.internal.constants.WorkflowStatusWebKeys;
import com.liferay.portal.workflow.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Feliphe Marinho
 */
public class WorkflowStatusTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(WorkflowStatusConstants.ATTRIBUTE_NAMESPACE);

		return super.doStartTag();
	}

	public Object getBean() {
		return _bean;
	}

	public String getId() {
		return _id;
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

	public boolean isShowInstanceTracker() {
		return _showInstanceTracker;
	}

	public boolean isShowStatusLabel() {
		return _showStatusLabel;
	}

	public void setBean(Object bean) {
		_bean = bean;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setModel(Class<?> model) {
		_model = model;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		setServletContext(ServletContextUtil.getServletContext());
	}

	public void setShowInstanceTracker(boolean showInstanceTracker) {
		_showInstanceTracker = showInstanceTracker;
	}

	public void setShowStatusLabel(boolean showStatusLabel) {
		_showStatusLabel = showStatusLabel;
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
		_id = null;
		_model = null;
		_showInstanceTracker = false;
		_showStatusLabel = true;
		_status = null;
		_statusMessage = null;
		_version = null;
	}

	@Override
	protected String getPage() {
		return "/workflow_status/page.jsp";
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return true;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		setNamespacedAttribute(
			httpServletRequest, WorkflowStatusWebKeys.BEAN, _bean);
		setNamespacedAttribute(
			httpServletRequest, WorkflowStatusWebKeys.ID, _id);
		setNamespacedAttribute(
			httpServletRequest, WorkflowStatusWebKeys.MODEL, _model);
		setNamespacedAttribute(
			httpServletRequest, WorkflowStatusWebKeys.SHOW_INSTANCE_TRACKER,
			_showInstanceTracker);
		setNamespacedAttribute(
			httpServletRequest, WorkflowStatusWebKeys.SHOW_STATUS_LABEL,
			_showStatusLabel);
		setNamespacedAttribute(
			httpServletRequest, WorkflowStatusWebKeys.STATUS, _status);
		setNamespacedAttribute(
			httpServletRequest, WorkflowStatusWebKeys.STATUS_MESSAGE,
			_statusMessage);
		setNamespacedAttribute(
			httpServletRequest, WorkflowStatusWebKeys.VERSION, _version);

		if (getBean() == null) {
			setNamespacedAttribute(
				httpServletRequest, WorkflowStatusWebKeys.BEAN,
				pageContext.getAttribute("aui:model-context:bean"));
		}

		if (getModel() == null) {
			setNamespacedAttribute(
				httpServletRequest, WorkflowStatusWebKeys.MODEL,
				pageContext.getAttribute("aui:model-context:model"));
		}
	}

	private Object _bean;
	private String _id;
	private Class<?> _model;
	private boolean _showInstanceTracker;
	private boolean _showStatusLabel = true;
	private Integer _status;
	private String _statusMessage;
	private String _version;

}