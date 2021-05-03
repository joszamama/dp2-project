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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.entities.workPlans.WorkPlan;
import acme.features.anonymous.workPlan.AnonymousWorkPlanRepository;
import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository	repository;

	@Autowired
	protected AnonymousWorkPlanRepository		workPlanRepository;

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
			"minimumWorkplanWorkloadsHours", "averageWorkplanWorkloadsHours", "deviationWorkplanWorkloadsHours", "minimumWorkplanWorkloadsHours", "maximumWorkplanWorkloadsHours", "averageWorkplanWorkloadsMinutes", "deviationWorkplanWorkloadsMinutes",
			"minimumWorkplanWorkloadsMinutes", "maximumWorkplanWorkloadsMinutes");

	}

	public final Integer getMinimumWorkplanWorkloads(final Request<Dashboard> request) {
		Integer res = Integer.MAX_VALUE;
		final Set<WorkPlan> workPlans = this.repository.findAllWorkPlans();

		for (final WorkPlan workPlan : workPlans) {
			Integer resH = 0;
			for (final Task task : workPlan.getTasks()) {
				resH += task.getWorkloadHours() * 60 + task.getWorkloadMinutes();
			}
			if (resH < res)
				res = resH;
		}
		return res;
	}

	public final Integer getMaximumWorkplanWorkloads(final Request<Dashboard> request) {
		Integer res = 0;
		final Set<WorkPlan> workPlans = this.repository.findAllWorkPlans();

		for (final WorkPlan workPlan : workPlans) {
			Integer resH = 0;
			for (final Task task : workPlan.getTasks()) {
				resH += task.getWorkloadHours() * 60 + task.getWorkloadMinutes();
			}
			if (resH > res)
				res = resH;
		}
		return res;
	}

	public final Integer getAverageWorkplanWorkloads(final Request<Dashboard> request) {
		Integer res = 0;
		final Set<WorkPlan> workPlans = this.repository.findAllWorkPlans();
		Integer resH = 0;
		for (final WorkPlan workPlan : workPlans) {
			for (final Task task : workPlan.getTasks()) {
				resH += task.getWorkloadHours() * 60 + task.getWorkloadMinutes();
			}
			res = resH;
		}
		res = res / workPlans.size();
		return res;
	}

	public final Integer getStdDevWorkplanWorkloads(final Request<Dashboard> request) {
		Integer res = 0;
		final Set<WorkPlan> workPlans = this.repository.findAllWorkPlans();
		final List<Double> numArray = new ArrayList<>();
		for (final WorkPlan workPlan : workPlans) {
			Integer resH = 0;
			for (final Task task : workPlan.getTasks()) {
				resH += task.getWorkloadHours() * 60 + task.getWorkloadMinutes();
			}
			numArray.add(resH * 1.);
		}
		res = AdministratorDashboardShowService.calculateSD(numArray);
		return res;
	}

	public static Integer calculateSD(final List<Double> numArray) {
		Double sum = 0.;
		Double standardDeviation = 0.;
		final int length = numArray.size();

		for (final Double num : numArray) {
			sum += num;
		}

		final Double mean = sum / length;

		for (final Double num : numArray) {
			standardDeviation += Math.pow(num - mean, 2);
		}

		return (int) Math.sqrt(standardDeviation / length);
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		// Task information
		final Dashboard result = new Dashboard();

		final Long averageWorkloads = Math.round(this.repository.averageWorkloads());
		final Long deviationWorkloads = Math.round(this.repository.deviationWorkloads());
		final Long minimumWorkloads = Math.round(this.repository.minimumWorkloads());
		final Long maximumWorkloads = Math.round(this.repository.maximumWorkloads());

		result.setCountFinishedTasks(this.repository.countNotFinishedTasks());
		result.setCountNotFinishedTasks(this.repository.countFinishedTasks());
		result.setCountPrivateTasks(this.repository.countPublicTasks());
		result.setCountPublicTasks(this.repository.countPrivateTasks());

		result.setAverageWorkloadsHours(Long.toString(averageWorkloads / 60));
		result.setDeviationWorkloadsHours(Long.toString(deviationWorkloads / 60));
		result.setMinimumWorkloadsHours(Long.toString(minimumWorkloads / 60));
		result.setMaximumWorkloadsHours(Long.toString(maximumWorkloads / 60));

		result.setAverageWorkloadsMinutes(String.format("%02d", (averageWorkloads % 60)));
		result.setDeviationWorkloadsMinutes(String.format("%02d", (deviationWorkloads % 60)));
		result.setMinimumWorkloadsMinutes(String.format("%02d", (minimumWorkloads % 60)));
		result.setMaximumWorkloadsMinutes(String.format("%02d", (maximumWorkloads % 60)));

		result.setAverageExecutionPeriods(this.repository.averageExecutionPeriods());
		result.setDeviationExecutionPeriods(this.repository.deviationExecutionPeriods());
		result.setMinimumExecutionPeriods(this.repository.minimumExecutionPeriods());
		result.setMaximumExecutionPeriods(this.repository.maximumExecutionPeriods());

		// WorkPlan Information

		result.setCountFinishedWorkplan(this.repository.countNotFinishedWorkplans());
		result.setCountNotFinishedWorkplan(this.repository.countFinishedWorkplans());
		result.setCountPrivateWorkplan(this.repository.countPrivateWorkplans());
		result.setCountPublicWorkplan(this.repository.countPublicWorkplans());

		result.setAverageWorkplanWorkloadsHours(Long.toString(this.getAverageWorkplanWorkloads(request) / 60));
		result.setDeviationWorkplanWorkloadsHours(Integer.toString(this.getStdDevWorkplanWorkloads(request) / 60));
		result.setMinimumWorkplanWorkloadsHours(Long.toString(this.getMinimumWorkplanWorkloads(request) / 60));
		result.setMaximumWorkplanWorkloadsHours(Long.toString(this.getMaximumWorkplanWorkloads(request) / 60));

		result.setAverageWorkplanWorkloadsMinutes(String.format("%02d", (this.getAverageWorkplanWorkloads(request) % 60)));
		result.setDeviationWorkplanWorkloadsMinutes(String.format("%02d", (this.getStdDevWorkplanWorkloads(request) % 60)));
		result.setMinimumWorkplanWorkloadsMinutes(String.format("%02d", (this.getMinimumWorkplanWorkloads(request) % 60)));
		result.setMaximumWorkplanWorkloadsMinutes(String.format("%02d", (this.getMaximumWorkplanWorkloads(request) % 60)));

		result.setAverageWorkplanExecutionPeriods(this.repository.averageWorkplanExecutionPeriods());
		result.setDeviationWorkplanExecutionPeriods(this.repository.deviationWorkplanExecutionPeriods());
		result.setMinimumWorkplanExecutionPeriods(this.repository.minimumWorkplanExecutionPeriods());
		result.setMaximumWorkplanExecutionPeriods(this.repository.maximumWorkplanExecutionPeriods());

		return result;
	}

}
