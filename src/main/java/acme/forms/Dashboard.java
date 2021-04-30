/*
 * Dashboard.java
 *
 * Copyright (c) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	// Task information
	Integer						countNotFinishedTasks;
	Integer						countFinishedTasks;
	Integer						countPublicTasks;
	Integer						countPrivateTasks;

	Double						averageExecutionPeriods;
	Double						deviationExecutionPeriods;
	Double						minimumExecutionPeriods;
	Double						maximumExecutionPeriods;

	String						averageWorkloadsHours;
	String						deviationWorkloadsHours;
	String						minimumWorkloadsHours;
	String						maximumWorkloadsHours;

	String						averageWorkloadsMinutes;
	String						deviationWorkloadsMinutes;
	String						minimumWorkloadsMinutes;
	String						maximumWorkloadsMinutes;

	// WorkPlan information
	Integer						countNotFinishedWorkplan;
	Integer						countFinishedWorkplan;
	Integer						countPublicWorkplan;
	Integer						countPrivateWorkplan;

	Double						averageWorkplanExecutionPeriods;
	Double						deviationWorkplanExecutionPeriods;
	Double						minimumWorkplanExecutionPeriods;
	Double						maximumWorkplanExecutionPeriods;

	String						averageWorkplanWorkloadsHours;
	String						deviationWorkplanWorkloadsHours;
	String						minimumWorkplanWorkloadsHours;
	String						maximumWorkplanWorkloadsHours;

	String						averageWorkplanWorkloadsMinutes;
	String						deviationWorkplanWorkloadsMinutes;
	String						minimumWorkplanWorkloadsMinutes;
	String						maximumWorkplanWorkloadsMinutes;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
