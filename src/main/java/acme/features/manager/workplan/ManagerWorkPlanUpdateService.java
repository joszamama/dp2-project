package acme.features.manager.workplan;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamFilterService;
import acme.entities.tasks.Task;
import acme.entities.workPlans.WorkPlan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkPlanUpdateService implements AbstractUpdateService<Manager, WorkPlan> {

	@Autowired
	private ManagerWorkPlanRepository repository;

	@Autowired
	protected SpamFilterService			spamFilterService;


	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;

		boolean result;
		final int WorkPlanId;
		final WorkPlan WorkPlan;
		final Manager manager;
		final Principal principal;

//		WorkPlanId = request.getModel().getInteger("id");
//		WorkPlan = this.repository.findOne(WorkPlanId);
//		manager = WorkPlan.getOwner();
//		principal = request.getPrincipal();
//		result = manager.getUserAccount().getId() == principal.getAccountId();
		result = true;
		return result;
	}

	@Override
	public void bind(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "tasks", "executionStart", "executionEnd", "workloadHours", "workloadMinutes", "isPrivate");
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		assert request != null;

		WorkPlan result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOne(id);

		return result;
	}

	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;


		if (!errors.hasErrors("executionStart")) {
			// executionStart must be in the future
			final Date now = new Date();
			errors.state(request, now.before(entity.getExecutionStart()), "executionStart", "manager.work-plan.form.error.start");

		}
		if (!errors.hasErrors("executionEnd")) {
			// executionEnd must be after executionStart
			errors.state(request, entity.getExecutionEnd().after(entity.getExecutionStart()), "executionEnd", "manager.work-plan.form.error.end");
		}
		final Boolean isSpam = this.spamFilterService.isSpam(entity.getTitle(), entity.getTasks().toString());
		errors.state(request, !isSpam, "*", "manager.work-plan.form.error.spamDetected");

		// WORKPLAN CAN'T START AFTER THE FIRST TASK HAS STARTED
		for (final Task t : entity.getTasks()) {
			if (t.getExecutionStart().before(entity.getExecutionStart())) {
				errors.state(request, false, "*", "manager.work-plan.form.error.executionStartTooLate");
				break;
			}
		}
		// WORKPLAN CAN'T FINISH BEFORE THE LAST TASK HAS FINISHED
		for (final Task t : entity.getTasks()) {
			if (t.getExecutionEnd().after(entity.getExecutionEnd())) {
				errors.state(request, false, "*", "manager.work-plan.form.error.executionEndTooEarly");
				break;
			}
		}

	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;
		
//		System.out.println("Workload: "+ entity.getWorkloadParsed());
//		
//		entity.setWorkloadParsed(entity.getWorkloadParsed());

		final boolean isSpam = this.spamFilterService.isSpam(entity.getTitle(), entity.getTasks().toString());
		if (isSpam == false) {
			this.repository.save(entity);
		} else {
			System.out.println("SPAM: " + entity.getTitle() + " " + entity.getTasks().toString());
			System.out.println("Mensaje borrado");
		}
	}

}

