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
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						countNotFinishedTasks;
	Integer						countFinishedTasks;
	Integer						countPublicTasks;
	Integer						countPrivateTasks;
	
	Double						averageExecutionPeriods;
	Double						deviationExecutionPeriods;
	Date						minimumExecutionPeriods;
	Date						maximumExecutionPeriods;

	Double						averageWorkloads;
	Double						deviationWorkloads;
	Double						minimumWorkloads;
	Double						maximumWorkloads;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
