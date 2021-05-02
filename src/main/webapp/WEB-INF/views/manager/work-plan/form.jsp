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

	<div class="col">
		<div class="row">
			<label for="tasks"> <acme:message
					code="anonymous.work-plan.form.label.tasks" />
			</label>
		</div>
		<div class="row">
			<div class="col">
				<select id="allTasks" size="6" class="form-control">
					<c:forEach items="${allTasks}" var="task">
						<option value="${task.id}"><c:out value="${task.title}" />
							(<fmt:formatDate pattern="dd/MM/yy HH:mm" value="${task.executionStart}" />-<fmt:formatDate pattern="dd/MM/yy HH:mm" value="${task.executionEnd}" />)
						</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-1 align-self-center ">
				<div class="row justify-content-center">
					<button type="button" class="btn" id="task-add">
						<i class="fa fa-arrow-right" aria-hidden="true"></i>
					</button>
				</div>
				<div class="row justify-content-center">
					<button type="button" class="btn" id="task-remove">
						<i class="fa fa-arrow-left" aria-hidden="true"></i>
					</button>
				</div>
			</div>
			<div class="form-group col">
				<input type="hidden" id="tasksParsed" name="tasksParsed"
					value="<acme:print value="${requestScope[tasksParsed]}"/>" /> <select
					id="selectedTasks" size="6" class="form-control">
					<c:forEach items="${tasks}" var="task">
						<option value="${task.id}"><c:out value="${task.title}" /></option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">
			<acme:form-errors path="tasks"/>	
		</div>
	</div>
	<acme:form-textbox code="anonymous.work-plan.form.label.executionStart"
		path="executionStart" />
	<acme:form-textbox code="anonymous.work-plan.form.label.executionEnd"
		path="executionEnd" />
	<acme:form-checkbox code="manager.work-plan.form.label.isPrivate" path="isPrivate"/>


	<acme:form-submit test="${command == 'show' }"
		code="manager.work-plan.form.button.update"
		action="/manager/work-plan/update" />
	<acme:form-submit test="${command == 'show' }"
		code="manager.work-plan.form.button.delete"
		action="/manager/work-plan/delete" />
	<acme:form-submit test="${command == 'create'}"
		code="manager.work-plan.form.button.create"
		action="/manager/work-plan/create" />
	<acme:form-submit test="${command == 'update'}"
		code="manager.work-plan.form.button.update"
		action="/manager/work-plan/update" />
	<acme:form-submit test="${command == 'delete'}"
		code="manager.work-plan.form.button.delete"
		action="/manager/work-plan/delete" />
	<acme:form-return code="anonymous.work-plan.form.button.return" />
</acme:form>

<script type="text/javascript">
	var taskSelection = [];
	var taskSelectionParsed = "";
	var tasksStartDates = {
		<c:forEach items="${allTasks}" var="task">
			<c:out value="${task.id}" />: new Date('<c:out value="${task.executionStart}" />'),
		</c:forEach>
	};
	var tasksSelectedStartDates = [];
	var tasksEndDates = {
		<c:forEach items="${allTasks}" var="task">
			<c:out value="${task.id}" />: new Date('<c:out value="${task.executionEnd}" />'),
		</c:forEach>
	};
	var tasksSelectedEndDates = [];

	function getDateAsStringInternationalized(date) {
		var languageCode = document.documentElement.lang;
		var dateString = "";
		if(languageCode.startsWith("es")) {
			dateString += ("0" + date.getDate()).slice(-2);
			dateString += "/";
			dateString += ("0" + date.getMonth()).slice(-2);
			dateString += "/";
			dateString += ("0000" + date.getFullYear()).slice(-4);
			dateString += " ";
			dateString += ("0" + date.getHours()).slice(-2);
			dateString += ":";
			dateString += ("0" + date.getMinutes()).slice(-2);
		} else if(languageCode.startsWith("en")) {
			dateString += ("0000" + date.getFullYear()).slice(-4);
			dateString += "/";
			dateString += ("0" + date.getMonth()).slice(-2);
			dateString += "/";
			dateString += ("0" + date.getDate()).slice(-2);
			dateString += " ";
			dateString += ("0" + date.getHours()).slice(-2);
			dateString += ":";
			dateString += ("0" + date.getMinutes()).slice(-2);
		}
		return dateString;
	}

	function calculateSelectedTasksDates() {
		if(taskSelection.length > 0) {
			tasksSelectedStartDates = [];
			tasksSelectedEndDates = [];
			for(var i = 0; i < taskSelection.length; ++i) {
				tasksSelectedStartDates.push(tasksStartDates[taskSelection[i]])
				tasksSelectedEndDates.push(tasksEndDates[taskSelection[i]])
			}
			var earliestStartDate = new Date(Math.min.apply(null,tasksSelectedStartDates));
			var latestEndDate = new Date(Math.max.apply(null,tasksSelectedEndDates));
			var suggestedStartDate = earliestStartDate;
			suggestedStartDate.setDate(suggestedStartDate.getDate()-1);
			suggestedStartDate.setHours(8);
			suggestedStartDate.setMinutes(0);
			suggestedStartDate.setSeconds(0);
			suggestedStartDate.setMilliseconds(0);
			var executionStartInput = document.getElementById("executionStart");
			executionStartInput.value = getDateAsStringInternationalized(suggestedStartDate)
			var suggestedEndDate = latestEndDate;
			suggestedEndDate.setDate(suggestedEndDate.getDate()+1);
			suggestedEndDate.setHours(17);
			suggestedEndDate.setMinutes(0);
			suggestedEndDate.setSeconds(0);
			suggestedEndDate.setMilliseconds(0);
			var executionEndInput = document.getElementById("executionEnd");
			executionEndInput.value = getDateAsStringInternationalized(suggestedEndDate)
		} else {
			var executionStartInput = document.getElementById("executionStart");
			executionStartInput.value = "";
			var executionEndInput = document.getElementById("executionEnd");
			executionEndInput.value = "";
		}
	}

	function addToSelected() {
		var id = $("#allTasks option:selected").val();
		var value = $("#allTasks option:selected").text();
		$("#selectedTasks").append("<option value='"+id+"'>" + value + "</option>");
		$("#allTasks option:selected").remove();
		addTask(id);
	}

	function removeFromSelected() {
		var id = $("#selectedTasks option:selected").val();
		var value = $("#selectedTasks option:selected").text();
		$("#allTasks").append("<option value='"+id+"'>" + value + "</option>");
		$("#selectedTasks option:selected").remove();
		removeTask(id);
	}
	
	function addTask(id){
		taskSelection.push(id);
		taskSelectionParsed = taskSelection.toString();
		$("#tasksParsed").val(taskSelectionParsed);
		calculateSelectedTasksDates();
	}
	
	function removeTask(id){
		taskSelection = taskSelection.filter(function(value){
			return value != id;
		});
		taskSelectionParsed = taskSelection.toString();
		$("#tasksParsed").val(taskSelectionParsed);
		calculateSelectedTasksDates();
	}
	
	$(document).on("click", "#task-add",function() {
		addToSelected();
    });
	
	$(document).on("click", "#task-remove",function() {
		removeFromSelected();
    });
	

</script>