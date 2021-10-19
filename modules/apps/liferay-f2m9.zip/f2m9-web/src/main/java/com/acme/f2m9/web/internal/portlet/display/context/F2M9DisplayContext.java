package com.acme.f2m9.web.internal.portlet.display.context;

import com.acme.f2m9.model.Todo;

import com.liferay.portal.kernel.model.WorkflowInstanceLink;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService;

public class F2M9DisplayContext {

	public F2M9DisplayContext(
		WorkflowInstanceLinkLocalService workflowInstanceLinkLocalService) {

		_workflowInstanceLinkLocalService = workflowInstanceLinkLocalService;
	}

	public Long getWorkflowInstanceId(Todo todo) {
		WorkflowInstanceLink workflowDefinitionLink =
			_workflowInstanceLinkLocalService.fetchWorkflowInstanceLink(
				todo.getCompanyId(), todo.getGroupId(), Todo.class.getName(),
				todo.getPrimaryKey());

		if (workflowDefinitionLink == null) {
			return null;
		}

		return workflowDefinitionLink.getWorkflowInstanceId();
	}

	private final WorkflowInstanceLinkLocalService
		_workflowInstanceLinkLocalService;

}