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

		request.unbind(entity, model, //
			"countNotFinishedTasks", // 
			"countFinishedTasks", "countPublicTasks", //
			"countPrivateTasks", //
			"averageWorkloadsHours", "deviationWorkloadsHours", "minimumWorkloadsHours", "maximumWorkloadsHours", //
			"averageWorkloadsMinutes", "deviationWorkloadsMinutes", "minimumWorkloadsMinutes", "maximumWorkloadsMinutes", //
			"averageExecutionPeriods", "deviationExecutionPeriods", "minimumExecutionPeriods", "maximumExecutionPeriods");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

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

		result.setAverageWorkloadsHours((int) (averageWorkloads / 60));
		result.setDeviationWorkloadsHours((int) (deviationWorkloads / 60));
		result.setMinimumWorkloadsHours((int) (minimumWorkloads / 60));
		result.setMaximumWorkloadsHours((int) (maximumWorkloads / 60));

		result.setAverageWorkloadsMinutes((int) (averageWorkloads % 60));
		result.setDeviationWorkloadsMinutes((int) (deviationWorkloads % 60));
		result.setMinimumWorkloadsMinutes((int) (minimumWorkloads % 60));
		result.setMaximumWorkloadsMinutes((int) (maximumWorkloads % 60));
		
		result.setAverageExecutionPeriods(averageExecutionPeriods);
		result.setDeviationExecutionPeriods(deviationExecutionPeriods);
		result.setMinimumExecutionPeriods(minimumExecutionPeriods);
		result.setMaximumExecutionPeriods(maximumExecutionPeriods);

		return result;
	}

}
