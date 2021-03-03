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
	<%-- Query parameters --%>
	
	<acme:form-money code="authenticated.money-exchange.form.label.source" path="source"/>
	<acme:form-textbox code="authenticated.money-exchange.form.label.target-currency" path="targetCurrency"/>
	
	<%-- Results --%>
	
	<jstl:if test="${date != null}">
		<acme:form-money code="authenticated.money-exchange.form.label.date" path="date" readonly="true"/>
	</jstl:if>	
	<jstl:if test="${target != null}">
		<acme:form-money code="authenticated.money-exchange.form.label.target" path="target" readonly="true"/>
	</jstl:if>
	
	<%-- Action buttons --%>
		
	<acme:form-submit code="authenticated.money-exchange.form.button.perform" action="/authenticated/money-exchange/perform"/>
	<acme:form-return code="authenticated.employer.form.button.return"/>
</acme:form>
