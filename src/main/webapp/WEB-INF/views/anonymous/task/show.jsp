<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<table>
	<tr>
		<td><acme:message code="anonymous.task.show.label.title" /></td>
		<td><acme:print value="title" /></td>
	</tr>
	<tr>
		<td><acme:message code="anonymous.task.show.label.description" /></td>
		<td><acme:print value="description" /></td>
	</tr>
	<tr>
		<td><acme:message code="anonymous.task.show.label.link" /></td>
		<td><acme:print value="link" /></td>
	</tr>
	<tr>
		<td><acme:message code="anonymous.task.show.label.executionStart" /></td>
		<td><acme:print value="executionStart" /></td>
	</tr>
	<tr>
		<td><acme:message code="anonymous.task.show.label.executionEnd" /></td>
		<td><acme:print value="executionEnd" /></td>
	</tr>
	<tr>
		<td><acme:message code="anonymous.task.show.label.workload" /></td>
		<td>
			<jstl:if test="${workload != null}">
				<acme:print value="workload" />
			</jstl:if>
		</td>
	</tr>
</table>
