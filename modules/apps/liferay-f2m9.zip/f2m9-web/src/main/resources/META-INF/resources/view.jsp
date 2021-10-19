<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/react" prefix="react" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.acme.f2m9.model.Todo" %><%@
page import="com.acme.f2m9.service.TodoLocalServiceUtil" %><%@
page import="com.acme.f2m9.web.internal.portlet.display.context.F2M9DisplayContext" %>

<%@ page import="com.liferay.portal.kernel.util.HashMapBuilder" %>

<%@ page import="java.util.List" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<h4>F2M9 Portlet</h4>

<portlet:actionURL name="addTodo" var="addTodoURL" />

<p>
	<aui:form action="<%= addTodoURL %>">
		<aui:input name="item" type="text" />

		<aui:button type="submit" value="submit" />
	</aui:form>
</p>

<%
F2M9DisplayContext f2M9DisplayContext = (F2M9DisplayContext)renderRequest.getAttribute("f2M9DisplayContext");

List<Todo> todoList = TodoLocalServiceUtil.getTodos(-1, -1);
%>

<h5>Todos</h5>
<c:choose>
	<c:when test="<%= (todoList != null) && !todoList.isEmpty() %>">
		<table>
			<tbody>
				<%for(Todo todo: todoList) { %>
				<tr>
					<td><%= todo.getName() %></td>
					<td><%= todo.getStatus() %></td>
					<td>

						<%
						Long workflowInstanceId = f2M9DisplayContext.getWorkflowInstanceId(todo);

						if (workflowInstanceId != null) {
						%>

						<react:component
							module="js/Index"
							props='<%=
								HashMapBuilder.<String, Object>put(
									"workflowInstanceId", workflowInstanceId
								).build()
							%>'
						/>

						<%} %>
					</td>
				</tr>
				<%} %>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<em>None</em>
	</c:otherwise>
</c:choose>