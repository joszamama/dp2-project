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
	<acme:message
		code="administrator.dashboard.form.title.general-indicators" />
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-public-tasks" /></th>
		<td><acme:print value="${countPublicTasks}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-private-tasks" /></th>
		<td><acme:print value="${countPrivateTasks}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-finished-tasks" /></th>
		<td><acme:print value="${countFinishedTasks}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-non-finished-tasks" />
		</th>
		<td><acme:print value="${countNotFinishedTasks}" /></td>
	</tr>
</table>

<table class="table table-sm">
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-average-execution-periods" />
		</th>
		<td><acme:print value="${averageExecutionPeriods}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-deviation-execution-periods" />
		</th>
		<td><acme:print value="${deviationExecutionPeriods}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-minimum-execution-periods" />
		</th>
		<td><acme:print value="${minimumExecutionPeriods}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-maximum-execution-periods" />
		</th>
		<td><acme:print value="${maximumExecutionPeriods}" /></td>
	</tr>
</table>

<table class="table table-sm">
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-average-workloads" />
		</th>
		<td><acme:print value="${averageWorkloadsHours}" />:<acme:print
				value="${averageWorkloadsMinutes}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-deviation-workloads" />
		</th>
		<td><acme:print value="${deviationWorkloadsHours}" />:<acme:print
				value="${deviationWorkloadsMinutes}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-minimum-workloads" />
		</th>
		<td><acme:print value="${minimumWorkloadsHours}" />:<acme:print
				value="${minimumWorkloadsMinutes}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-maximum-workloads" />
		</th>
		<td><acme:print value="${maximumWorkloadsHours}" />:<acme:print
				value="${maximumWorkloadsMinutes}" /></td>
	</tr>
</table>



<table class="table table-sm">
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-public-workplans" /></th>
		<td><acme:print value="${countPublicWorkplan}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-private-workplans" /></th>
		<td><acme:print value="${countPrivateWorkplan}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-finished-workplans" /></th>
		<td><acme:print value="${countFinishedWorkplan}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-non-finished-workplans" />
		</th>
		<td><acme:print value="${countNotFinishedWorkplan}" /></td>
	</tr>
</table>

<table class="table table-sm">
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-average-workplan-execution-periods" /></th>
		<td><acme:print value="${averageWorkplanExecutionPeriods}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-deviation-workplan-execution-periods" /></th>
		<td><acme:print value="${deviationWorkplanExecutionPeriods}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-minimum-workplan-execution-periods" /></th>
		<td><acme:print value="${minimumWorkplanExecutionPeriods}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-maximum-workplan-execution-periods" />
		</th>
		<td><acme:print value="${maximumWorkplanExecutionPeriods}" /></td>
	</tr>
</table>

<table class="table table-sm">
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-average-workplan-workloads" /></th>
		<td><acme:print value="${averageWorkplanWorkloadsHours}" />:<acme:print
				value="${averageWorkplanWorkloadsMinutes}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-deviation-workplan-workloads" /></th>
		<td><acme:print value="${deviationWorkplanWorkloadsHours}" />:<acme:print
				value="${deviationWorkplanWorkloadsMinutes}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-minimum-workplan-workloads" /></th>
		<td><acme:print value="${minimumWorkplanWorkloadsHours}" />:<acme:print
				value="${minimumWorkplanWorkloadsMinutes}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.number-maximum-workplan-workloads" />
		</th>
		<td><acme:print value="${maximumWorkplanWorkloadsHours}" />:<acme:print
				value="${maximumWorkplanWorkloadsMinutes}" /></td>
	</tr>
</table>

<h2>
	<acme:message
		code="administrator.dashboard.form.title.application-statuses" />
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"Total number of work plans, irrespective of their publication statuses", "Total number of work plans that are published"
			],
			datasets : [
				{
					data : [
							<jstl:out value="${countPublicWorkplan + countPrivateWorkplan}"/>, <jstl:out value="${countPublicWorkplan}"/>,
					]
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};

		var canvas, context;

		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>
