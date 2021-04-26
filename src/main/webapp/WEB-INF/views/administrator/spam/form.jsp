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

<h2>
	<acme:message code="administrator.parameters.form.title.show"/>
</h2>

<acme:form>
	<acme:form-hidden path="spamId"/>
	
	<acme:form-textarea code="administrator.parameters.form.word-list" path="wordList"/>
	<acme:form-textbox code="administrator.parameters.form.threshold" path="threshold"/>
	
	<acme:form-submit code="administrator.parameters.form.button.create" action="/administrator/spam/create"/>
</acme:form>