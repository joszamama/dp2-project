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

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.work-plan.form.label.title" path="title"/>
	<acme:form-textbox code="anonymous.work-plan.form.label.tasks" path="tasks"/>
	<acme:form-textbox code="anonymous.work-plan.form.label.executionStart" path="executionStart"/>
	<acme:form-textbox code="anonymous.work-plan.form.label.executionEnd" path="executionEnd"/>
	<acme:form-textbox code="anonymous.work-plan.form.label.workloadHours" path="workloadHours"/>
	<acme:form-textbox code="anonymous.work-plan.form.label.workloadMinutes" path="workloadMinutes"/>
	
	<acme:form-return code="anonymous.work-plan.form.button.return"/>
</acme:form>
