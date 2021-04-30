/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (c) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "countNotFinishedTasks", "countFinishedTasks", "countPublicTasks", "countPrivateTasks", "averageWorkloadsHours", "deviationWorkloadsHours", "minimumWorkloadsHours", "maximumWorkloadsHours", "averageWorkloadsMinutes",
			"deviationWorkloadsMinutes", "minimumWorkloadsMinutes", "maximumWorkloadsMinutes", "averageExecutionPeriods", "deviationExecutionPeriods", "minimumExecutionPeriods", "maximumExecutionPeriods", "countNotFinishedWorkplan",
			"countFinishedWorkplan", "countPublicWorkplan", "countPrivateWorkplan", "averageWorkplanExecutionPeriods", "deviationWorkplanExecutionPeriods", "minimumWorkplanExecutionPeriods", "maximumWorkplanExecutionPeriods",
			"averageWorkplanWorkloadsHours", "deviationWorkplanWorkloadsHours", "minimumWorkplanWorkloadsHours", "maximumWorkplanWorkloadsHours", "averageWorkplanWorkloadsMinutes", "deviationWorkplanWorkloadsMinutes", "minimumWorkplanWorkloadsMinutes",
			"maximumWorkplanWorkloadsMinutes");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		// Task information
		final Dashboard result = new Dashboard();
		final Integer countNotFinishedTasks = this.repository.countNotFinishedTasks();
		final Integer countFinishedTasks = this.repository.countFinishedTasks();
		final Integer countPublicTasks = this.repository.countPublicTasks();
		final Integer countPrivateTasks = this.repository.countPrivateTasks();

		final Long averageWorkloads = Math.round(this.repository.averageWorkloads());
		final Long deviationWorkloads = Math.round(this.repository.deviationWorkloads());
		final Long minimumWorkloads = Math.round(this.repository.minimumWorkloads());
		final Long maximumWorkloads = Math.round(this.repository.maximumWorkloads());

		final Double averageExecutionPeriods = this.repository.averageExecutionPeriods();
		final Double deviationExecutionPeriods = this.repository.deviationExecutionPeriods();
		final Double minimumExecutionPeriods = this.repository.minimumExecutionPeriods();
		final Double maximumExecutionPeriods = this.repository.maximumExecutionPeriods();

		result.setCountFinishedTasks(countFinishedTasks);
		result.setCountNotFinishedTasks(countNotFinishedTasks);
		result.setCountPrivateTasks(countPrivateTasks);
		result.setCountPublicTasks(countPublicTasks);

		result.setAverageWorkloadsHours(Long.toString(averageWorkloads / 60));
		result.setDeviationWorkloadsHours(Long.toString(deviationWorkloads / 60));
		result.setMinimumWorkloadsHours(Long.toString(minimumWorkloads / 60));
		result.setMaximumWorkloadsHours(Long.toString(maximumWorkloads / 60));

		result.setAverageWorkloadsMinutes(String.format("%02d", (averageWorkloads % 60)));
		result.setDeviationWorkloadsMinutes(String.format("%02d", (deviationWorkloads % 60)));
		result.setMinimumWorkloadsMinutes(String.format("%02d", (minimumWorkloads % 60)));
		result.setMaximumWorkloadsMinutes(String.format("%02d", (maximumWorkloads % 60)));

		result.setAverageExecutionPeriods(averageExecutionPeriods);
		result.setDeviationExecutionPeriods(deviationExecutionPeriods);
		result.setMinimumExecutionPeriods(minimumExecutionPeriods);
		result.setMaximumExecutionPeriods(maximumExecutionPeriods);

		// WorkPlan Information

		final Integer countNotFinishedWorkplan = this.repository.countNotFinishedWorkplans();
		final Integer countFinishedWorkplan = this.repository.countFinishedWorkplans();
		final Integer countPublicWorkplan = this.repository.countPublicWorkplans();
		final Integer countPrivateWorkplan = this.repository.countPrivateWorkplans();

		final Long averageWorkplanWorkloads = Math.round(this.repository.averageWorkplanWorkloads());
		final Long deviationWorkplanWorkloads = Math.round(this.repository.deviationWorkplanWorkloads());
		final Long minimumWorkplanWorkloads = Math.round(this.repository.minimumWorkplanWorkloads());
		final Long maximumWorkplanWorkloads = Math.round(this.repository.maximumWorkplanWorkloads());

		final Double averageWorkplanExecutionPeriods = this.repository.averageWorkplanExecutionPeriods();
		final Double deviationWorkplanExecutionPeriods = this.repository.deviationWorkplanExecutionPeriods();
		final Double minimumWorkplanExecutionPeriods = this.repository.minimumWorkplanExecutionPeriods();
		final Double maximumWorkplanExecutionPeriods = this.repository.maximumWorkplanExecutionPeriods();

		result.setCountFinishedWorkplan(countFinishedWorkplan);
		result.setCountNotFinishedWorkplan(countNotFinishedWorkplan);
		result.setCountPrivateWorkplan(countPrivateWorkplan);
		result.setCountPublicWorkplan(countPublicWorkplan);

		result.setAverageWorkplanWorkloadsHours(Long.toString(averageWorkloads / 60));
		result.setDeviationWorkplanWorkloadsHours(Long.toString(deviationWorkloads / 60));
		result.setMinimumWorkplanWorkloadsHours(Long.toString(minimumWorkloads / 60));
		result.setMaximumWorkplanWorkloadsHours(Long.toString(maximumWorkloads / 60));

		result.setAverageWorkplanWorkloadsMinutes(String.format("%02d", (averageWorkplanWorkloads % 60)));
		result.setDeviationWorkplanWorkloadsMinutes(String.format("%02d", (deviationWorkplanWorkloads % 60)));
		result.setMinimumWorkplanWorkloadsMinutes(String.format("%02d", (minimumWorkplanWorkloads % 60)));
		result.setMaximumWorkplanWorkloadsMinutes(String.format("%02d", (maximumWorkplanWorkloads % 60)));

		result.setAverageWorkplanExecutionPeriods(averageWorkplanExecutionPeriods);
		result.setDeviationWorkplanExecutionPeriods(deviationWorkplanExecutionPeriods);
		result.setMinimumWorkplanExecutionPeriods(minimumWorkplanExecutionPeriods);
		result.setMaximumWorkplanExecutionPeriods(maximumWorkplanExecutionPeriods);

		return result;
	}

}
