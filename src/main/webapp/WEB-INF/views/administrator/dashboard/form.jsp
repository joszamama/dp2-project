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
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-public-tasks"/>
		</th>
		<td>
			<acme:print value="${countPublicTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-private-tasks"/>
		</th>
		<td>
			<acme:print value="${countPrivateTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-finished-tasks"/>
		</th>
		<td>
			<acme:print value="${countFinishedTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-non-finished-tasks"/>
		</th>
		<td>
			<acme:print value="${countNotFinishedTasks}"/>
		</td>
	</tr>	
</table>

<table class="table table-sm">	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-average-execution-periods"/>
		</th>
		<td>
			<acme:print value="${averageExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-deviation-execution-periods"/>
		</th>
		<td>
			<acme:print value="${deviationExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-minimum-execution-periods"/>
		</th>
		<td>
			<acme:print value="${minimumExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-maximum-execution-periods"/>
		</th>
		<td>
			<acme:print value="${maximumExecutionPeriods}"/>
		</td>
	</tr>	
</table>

<table class="table table-sm">	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-average-workloads"/>
		</th>
		<td>
			<acme:print value="${averageWorkloadsHours}"/>:<acme:print value="${averageWorkloadsMinutes}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-deviation-workloads"/>
		</th>
		<td>
			<acme:print value="${deviationWorkloadsHours}"/>:<acme:print value="${deviationWorkloadsMinutes}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-minimum-workloads"/>
		</th>
		<td>
			<acme:print value="${minimumWorkloadsHours}"/>:<acme:print value="${minimumWorkloadsMinutes}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-maximum-workloads"/>
		</th>
		<td>
			<acme:print value="${maximumWorkloadsHours}"/>:<acme:print value="${maximumWorkloadsMinutes}"/>
		</td>
	</tr>	
</table>

