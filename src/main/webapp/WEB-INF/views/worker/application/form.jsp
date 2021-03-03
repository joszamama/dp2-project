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
	<acme:form-hidden path="jobId"/>

	<acme:form-textbox code="worker.application.label.reference" path="reference" readonly="true"/>
	<jstl:if test="${command == 'show'}">
		<acme:form-textbox code="worker.application.label.moment" path="moment" readonly="true"/>
		<acme:form-textbox code="worker.application.label.status" path="status"/>
	</jstl:if>	
	<acme:form-textarea code="worker.application.label.statement" path="statement" />
	<acme:form-textarea code="worker.application.label.qualifications" path="qualifications"/>
	<acme:form-textarea code="worker.application.label.skills" path="skills"/>	
			
	<acme:form-submit test="${command == 'create'}" code="worker.application.button.create" action="/worker/application/create"/>
	<acme:form-return code="worker.application.button.return"/>	
</acme:form>

