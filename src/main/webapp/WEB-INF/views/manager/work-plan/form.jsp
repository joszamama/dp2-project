<%@page language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="manager.work-plan.form.label.title" path="title"/>
		<div class="form-group">
		<label for="tasks">
			<acme:message code="manager.work-plan.form.label.tasks" />
		</label>
		<select id="tasks" size="3" class="form-control">
			<c:forEach items="${tasks}" var="task">
				<option><c:out value="${task.title}"/></option>
			</c:forEach>
		</select>
	</div>
	<acme:form-moment code="manager.work-plan.form.label.executionStart" path="executionStart"/>
	<acme:form-moment code="manager.work-plan.form.label.executionEnd" path="executionEnd"/>
	<acme:form-textbox code="anonymous.work-plan.form.label.workloadHours" path="workloadHours"/>
	<acme:form-textbox code="anonymous.work-plan.form.label.workloadMinutes" path="workloadMinutes"/>
	<acme:form-checkbox code="manager.work-plan.form.label.isPrivate" path="isPrivate"/>
	
	<acme:form-submit test="${command == 'show' }" code="manager.work-plan.form.button.update" action="/manager/work-plan/update"/>
	<acme:form-submit test="${command == 'show' }" code="manager.work-plan.form.button.delete" action="/manager/work-plan/delete"/>
	<acme:form-submit test="${command == 'create'}" code="manager.work-plan.form.button.create" action="/manager/work-plan/create"/>
	<acme:form-submit test="${command == 'update'}" code="manager.work-plan.form.button.update" action="/manager/work-plan/update"/>
	<acme:form-submit test="${command == 'delete'}" code="manager.work-plan.form.button.delete" action="/manager/work-plan/delete"/>
	<acme:form-return code="manager.work-plan.form.button.return"/>
</acme:form>