<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.task.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.task.form.label.description" path="description"/>
	<acme:form-textbox code="authenticated.task.form.label.link" path="link"/>
	<acme:form-moment code="authenticated.task.form.label.executionStart" path="executionStart"/>
	<acme:form-moment code="authenticated.task.form.label.executionEnd" path="executionEnd"/>
	<acme:form-double code="authenticated.task.form.label.workload" path="workload"/>
	<acme:form-checkbox code="authenticated.task.form.label.isPrivate" path="isPrivate"/>
	
	<acme:form-submit test="${command == 'show' }" code="manager.task.form.button.update" action="/manager/task/update"/>
	<acme:form-submit test="${command == 'show' }" code="manager.task.form.button.delete" action="/manager/task/delete"/>
	<acme:form-submit test="${command == 'create'}" code="manager.task.form.button.create" action="/manager/task/create"/>
	<acme:form-submit test="${command == 'update'}" code="manager.task.form.button.update" action="/manager/task/update"/>
	<acme:form-submit test="${command == 'delete'}" code="manager.task.form.button.delete" action="/manager/task/delete"/>
	<acme:form-return code="authenticated.task.form.button.return"/>
</acme:form>
