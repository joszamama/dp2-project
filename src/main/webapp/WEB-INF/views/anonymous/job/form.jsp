<%--
- form.jsp
-
- Copyright (c) 2012-2021 Rafael Corchuelo.
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

<acme:form>
	<acme:form-textbox code="anonymous.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="anonymous.job.form.label.title" path="title"/>
	<acme:form-moment code="anonymous.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="anonymous.job.form.label.salary" path="salary"/>
	<acme:form-money code="anonymous.job.form.label.score" path="score"/>
	<acme:form-url code="anonymous.job.form.label.more-info" path="moreInfo"/>
	<acme:form-textarea code="anonymous.job.form.label.description" path="description"/>
	
	<acme:form-return code="anonymous.job.form.button.return"/>	
</acme:form>
