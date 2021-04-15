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
			"countPrivateTasks", "averageWorkloads", "deviationWorkloads", "minimumWorkloads", "maximumWorkloads", "averageExecutionPeriods", "deviationExecutionPeriods", "minimumExecutionPeriods", "maximumExecutionPeriods");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		final Dashboard result = new Dashboard();
		final Integer countNotFinishedTasks = this.repository.countNotFinishedTasks();
		final Integer countFinishedTasks = this.repository.countFinishedTasks();
		final Integer countPublicTasks = this.repository.countPublicTasks();
		final Integer countPrivateTasks = this.repository.countPrivateTasks();

		final Double averageWorkloads = this.repository.averageWorkloads();
		final Double deviationWorkloads = this.repository.deviationWorkloads();
		final Double minimumWorkloads = this.repository.minimumWorkloads();
		final Double maximumWorkloads = this.repository.maximumWorkloads();

		final Double averageExecutionPeriods = this.repository.averageExecutionPeriods();
		final Double deviationExecutionPeriods = this.repository.deviationExecutionPeriods();
		final Double minimumExecutionPeriods = this.repository.minimumExecutionPeriods();
		final Double maximumExecutionPeriods = this.repository.maximumExecutionPeriods();

		result.setCountFinishedTasks(countFinishedTasks);
		result.setCountNotFinishedTasks(countNotFinishedTasks);
		result.setCountPrivateTasks(countPrivateTasks);
		result.setCountPublicTasks(countPublicTasks);

		result.setAverageWorkloads(averageWorkloads);
		result.setDeviationWorkloads(deviationWorkloads);
		result.setMinimumWorkloads(minimumWorkloads);
		result.setMaximumWorkloads(maximumWorkloads);

		result.setAverageExecutionPeriods(averageExecutionPeriods);
		result.setDeviationExecutionPeriods(deviationExecutionPeriods);
		result.setMinimumExecutionPeriods(minimumExecutionPeriods);
		result.setMaximumExecutionPeriods(maximumExecutionPeriods);

		return result;
	}

}
