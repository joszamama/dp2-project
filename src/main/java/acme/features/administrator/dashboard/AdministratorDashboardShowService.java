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
			"deviationWorkloadsMinutes", "minimumWorkloadsMinutes", "maximumWorkloadsMinutes", "averageExecutionPeriods", "deviationExecutionPeriods", "minimumExecutionPeriods", "maximumExecutionPeriods");

	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		// Task information
		final Dashboard result = new Dashboard();

		if (this.repository.averageWorkloads() != null) {
			final Long averageWorkloads = Math.round(this.repository.averageWorkloads());
			result.setAverageWorkloadsHours(Long.toString(averageWorkloads / 60));
			result.setAverageWorkloadsMinutes(String.format("%02d", (averageWorkloads % 60)));
		} else {
			result.setAverageWorkloadsHours(Long.toString(0));
			result.setAverageWorkloadsMinutes(String.format("%02d", (0)));
		}

		if (this.repository.deviationWorkloads() != null) {
			final Long deviationWorkloads = Math.round(this.repository.deviationWorkloads());
			result.setDeviationWorkloadsHours(Long.toString(deviationWorkloads / 60));
			result.setDeviationWorkloadsMinutes(String.format("%02d", (deviationWorkloads % 60)));
		} else {
			result.setDeviationWorkloadsHours(Long.toString(0));
			result.setDeviationWorkloadsMinutes(String.format("%02d", (0)));
		}

		if (this.repository.minimumWorkloads() != null) {
			final Long minimumWorkloads = Math.round(this.repository.minimumWorkloads());
			result.setMinimumWorkloadsHours(Long.toString(minimumWorkloads / 60));
			result.setMinimumWorkloadsMinutes(String.format("%02d", (minimumWorkloads % 60)));
		} else {
			result.setMinimumWorkloadsHours(Long.toString(0));
			result.setMinimumWorkloadsMinutes(String.format("%02d", (0)));
		}

		if (this.repository.maximumWorkloads() != null) {
			final Long maximumWorkloads = Math.round(this.repository.maximumWorkloads());
			result.setMaximumWorkloadsHours(Long.toString(maximumWorkloads / 60));
			result.setMaximumWorkloadsMinutes(String.format("%02d", (maximumWorkloads % 60)));
		} else {
			result.setMaximumWorkloadsHours(Long.toString(0));
			result.setMaximumWorkloadsMinutes(String.format("%02d", (0)));
		}

		result.setCountFinishedTasks(this.repository.countNotFinishedTasks());
		result.setCountNotFinishedTasks(this.repository.countFinishedTasks());
		result.setCountPrivateTasks(this.repository.countPublicTasks());
		result.setCountPublicTasks(this.repository.countPrivateTasks());

		if (this.repository.averageExecutionPeriods() != null) {
			result.setAverageExecutionPeriods(this.repository.averageExecutionPeriods());
		}
		if (this.repository.deviationExecutionPeriods() != null) {
			result.setDeviationExecutionPeriods(this.repository.deviationExecutionPeriods());
		}
		if (this.repository.minimumExecutionPeriods() != null) {
			result.setMinimumExecutionPeriods(this.repository.minimumExecutionPeriods());
		}
		if (this.repository.maximumExecutionPeriods() != null) {
			result.setMaximumExecutionPeriods(this.repository.maximumExecutionPeriods());
		}

		return result;
	}

}
