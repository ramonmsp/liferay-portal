<%--
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
--%>

<%@ include file="/workflow_status/init.jsp" %>

<span class="taglib-workflow-status">
	<c:if test="<%= Validator.isNotNull(workflowStatusTaglibHelper.getId()) %>">
		<span class="mr-2 workflow-id">
			<span class="workflow-label"><liferay-ui:message key="id" />:</span>
			<span class="workflow-value"><%= HtmlUtil.escape(workflowStatusTaglibHelper.getId()) %></span>
		</span>
	</c:if>

	<c:if test="<%= Validator.isNotNull(workflowStatusTaglibHelper.getVersion()) %>">
		<span class="mr-2 workflow-version">
			<span class="workflow-label"><liferay-ui:message key="version" />:</span>

			<strong class="workflow-value"><%= workflowStatusTaglibHelper.getVersion() %></strong>
		</span>
	</c:if>

	<span class="<%= workflowStatusTaglibHelper.getShowIcon() ? "icon-file-alt workflow-status" : "workflow-status" %>">
		<c:if test="<%= workflowStatusTaglibHelper.getShowLabel() %>">
			<span class="workflow-label"><liferay-ui:message key="status" />:</span>
		</c:if>

		<strong class="label label-<%= WorkflowConstants.getStatusStyle(workflowStatusTaglibHelper.getStatus()) %> status text-uppercase workflow-status-<%= WorkflowConstants.getStatusLabel(workflowStatusTaglibHelper.getStatus()) %> <%= WorkflowConstants.getStatusCssClass(workflowStatusTaglibHelper.getStatus()) %> workflow-value">
			<liferay-ui:message key="<%= workflowStatusTaglibHelper.getStatusMessage() %>" /><%= workflowStatusTaglibHelper.getAdditionalText() %>
		</strong>
	</span>

	<c:if test="<%= workflowStatusTaglibHelper.getShowHelpMessage() && Validator.isNotNull(workflowStatusTaglibHelper.getHelpMessage()) %>">
		<liferay-ui:icon-help message="<%= workflowStatusTaglibHelper.getHelpMessage() %>" />
	</c:if>
</span>