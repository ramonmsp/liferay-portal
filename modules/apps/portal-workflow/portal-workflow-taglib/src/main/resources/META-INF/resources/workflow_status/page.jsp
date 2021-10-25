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
<%@ taglib uri="http://liferay.com/tld/react" prefix="react" %>

<%@ page import="com.liferay.portal.kernel.util.HashMapBuilder" %>

<react:component
	module="workflow_status/js/WorkflowStatus"
	props='<%=
		HashMapBuilder.<String, Object>put(
			"additionalText", workflowStatusTaglibHelper.getAdditionalText()
		).put(
			"helpMessage", workflowStatusTaglibHelper.getHelpMessage()
		).put(
			"id", HtmlUtil.escape(workflowStatusTaglibHelper.getId())
		).put(
			"showIcon", workflowStatusTaglibHelper.getShowHelpMessage()
		).put(
			"showLabel", workflowStatusTaglibHelper.getShowLabel()
		).put(
			"statusMessage", workflowStatusTaglibHelper.getStatusMessage()
		).put(
			"statusStyle", WorkflowConstants.getStatusStyle(workflowStatusTaglibHelper.getStatus())
		).put(
			"version", workflowStatusTaglibHelper.getVersion()
		).put(
			"workflowInstanceId", workflowStatusTaglibHelper.getInstanceId()
		).put(
			"showInstancetracker", true
		).build()
			%>'
/>

<%--<span class="taglib-workflow-status">

		<span class="mr-2 workflow-id">
			<span class="workflow-label"><liferay-ui:message key="id" />:</span>
			<span class="workflow-value">value id</span>
		</span>
		<span class="mr-2 workflow-version">
			<span class="workflow-label"><liferay-ui:message key="version" />:</span>

			<strong class="workflow-value">version id</strong>
		</span>

	<span class="icon-file-alt workflow-status">

			<span class="workflow-label"><liferay-ui:message key="status" />:</span>

		<strong class="label label-<%= WorkflowConstants.getStatusStyle(workflowStatusTaglibHelper.getStatus()) %> status text-uppercase workflow-status-<%= WorkflowConstants.getStatusLabel(workflowStatusTaglibHelper.getStatus()) %> <%= WorkflowConstants.getStatusCssClass(workflowStatusTaglibHelper.getStatus()) %> workflow-value">
			<liferay-ui:message key="label" />oto value
		</strong>
	</span>

		<liferay-ui:icon-help message="icon" />

</span>--%>
