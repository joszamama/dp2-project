<%@page language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="manager.work-plan.form.label.title"
		path="title" />

	<jstl:if test="${command == 'show' }">
	
<%-- 			<label for="tasks"> <acme:message
					code="manager.work-plan.form.label.tasks" />
			</label> --%>

			<acme:form-select code="manager.work-plan.form.label.tasks"
				path="workplan.tasks">
				<acme:form-option code="workplan.tasks.title" value=""/>
				<jstl:forEach items="tasks" var="task">
					<acme:form-option code="${task.title}" value="workplan.tasks.id" />
					<%-- 			<acme:form-option code="ENABLED" value="ENABLED"/>
			<acme:form-option code="DISABLED" value="DISABLED"/>	 --%>
				</jstl:forEach>
			</acme:form-select>

			<%-- 			<acme:form-select code="manager.work-plan.form.label.selectedTasks" path="workplan.tasks">
			<jstl:forEach items="${workplan.tasks}" var="task">
 			<acme:form-option code="${task.title}" value="tasks"/> 
			</jstl:forEach>
			</acme:form-select> --%>

<%-- 			<acme:form-select code="manager.work-plan.form.label.tasks"
				path="tasks">
				<jstl:forEach items="${tasks}" var="task">
					<acme:form-option code="${task.title}" value="tasks" />
				</jstl:forEach>
			</acme:form-select> --%>

	</jstl:if>
	
	<jstl:if test="${command == 'create'}">


		<acme:form-select code="manager.work-plan.form.label.tasks"	path="workplan.tasks.id">
			<jstl:forEach items="${tasks}" var="task">
				<acme:form-option code="${task.title}" value="workplan.tasks.id" />
				<%-- 			<acme:form-option code="ENABLED" value="ENABLED"/>
			<acme:form-option code="DISABLED" value="DISABLED"/>	 --%>
			</jstl:forEach>
		</acme:form-select>


		<%-- 		<label for="tasks">
			<acme:message code="manager.work-plan.form.label.tasks" />
		</label>
		
		<acme:form-option code="manager.work-plan.form.label.tasks" value="tasks"/> --%>

		<%-- 	<acme:form-select code="manager.work-plan.form.label.tasks" path="tasks">
			<jstl:forEach items="${tasks}" var="task">
 			<acme:form-option code="${task.title}" value="tasks"/> 
				<option><jstl:out value="${task.title}"></jstl:out><option>
			</jstl:forEach>
		</acme:form-select> --%>

		<%-- 		<c:forEach items="${tasks}" var="task">
			<option><c:out value="${task.title}" /></option>
		</c:forEach> --%>

		<%-- 		<select id="tasks" size="3" class="form-control" multiple>
			<c:forEach items="${tasks}" var="task">
				<acme:form-option code="${task.title}" value="workplan.tasks"/>
			</c:forEach>
			</select> --%>





	</jstl:if>
	<%-- 	<acme:form-textbox code="manager.work-plan.form.label.tasks" path="tasks.title"/> --%>
	<acme:form-moment code="manager.work-plan.form.label.executionStart"
		path="executionStart" />
	<acme:form-moment code="manager.work-plan.form.label.executionEnd"
		path="executionEnd" />
	<acme:form-double code="manager.work-plan.form.label.workloadParsed"
		path="workloadParsed" />
	<acme:form-checkbox code="manager.work-plan.form.label.isPrivate"
		path="isPrivate" />

	<acme:form-submit test="${command == 'show' }"
		code="manager.work-plan.form.button.update"
		action="/manager/work-plan/update" />
	<acme:form-submit test="${command == 'show' }"
		code="manager.work-plan.form.button.delete"
		action="/manager/work-plan/delete" />

	<acme:form-submit test="${command == 'create'}"
		code="manager.work-plan.form.button.create"
		action="/manager/work-plan/create" />

	<acme:form-submit test="${command == 'update'}"
		code="manager.work-plan.form.button.update"
		action="/manager/work-plan/update" />

	<acme:form-submit test="${command == 'delete'}"
		code="manager.work-plan.form.button.delete"
		action="/manager/work-plan/delete" />

	<acme:form-return code="manager.work-plan.form.button.return" />
</acme:form>