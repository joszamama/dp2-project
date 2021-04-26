<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.task.list.label.title" path="title" width="40%"/>
	<acme:list-column code="authenticated.task.list.label.executionStart" path="executionStart" sortable="true" width="20%"/>
	<acme:list-column code="authenticated.task.list.label.executionEnd" path="executionEnd" sortable="true" width="20%"/>
	<acme:list-column code="authenticated.task.list.label.workloadHours" path="workloadHours" sortable="true" width="10%"/>
	<acme:list-column code="authenticated.task.list.label.workloadMinutes" path="workloadMinutes" sortable="true" width="10%"/>
</acme:list>
