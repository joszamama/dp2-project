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
	<acme:form-textbox code="employer.application.form.label.reference" path="reference" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.moment" path="moment" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.skills" path="skills" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.qualifications" path="qualifications" readonly="true"/>
	
	<jstl:if test="${status != 'PENDING'}">
		<acme:form-textbox code="employer.application.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${status == 'PENDING'}">
		<acme:form-select path="status" code="employer.application.form.label.new-status">
			<acme:form-option code="PENDING" value="PENDING" selected="true"/>
			<acme:form-option code="ACCEPTED" value="ACCEPTED"/>
			<acme:form-option code="REJECTED" value="REJECTED"/>
		</acme:form-select>		
	</jstl:if>
			
	<acme:form-submit test="${command == 'show' && status == 'PENDING'}" code="employer.application.form.button.update" action="/employer/application/update"/>
	<acme:form-return code="employer.application.form.button.return"/>	
</acme:form>


