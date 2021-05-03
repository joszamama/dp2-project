
<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<acme:form>

	<acme:form-textbox code="anonymous.work-plan.form.label.title"
		path="title" />
			<div class="form-group">
		<label for="tasks">
			<acme:message code="anonymous.work-plan.form.label.tasks" />
		</label>
		<select id="tasks" size="3" class="form-control">
			<c:forEach items="${tasks}" var="task">
				<option><c:out value="${task.title}"/></option>
			</c:forEach>
		</select>
	</div>
	<acme:form-textbox code="anonymous.work-plan.form.label.executionStart"
		path="executionStart" />
	<acme:form-textbox code="anonymous.work-plan.form.label.executionEnd"
		path="executionEnd" />
	<acme:form-textbox code="anonymous.work-plan.form.label.workloadParsed"
		path="workloadParsed" />

	<acme:form-return code="anonymous.work-plan.form.button.return" />
</acme:form>
