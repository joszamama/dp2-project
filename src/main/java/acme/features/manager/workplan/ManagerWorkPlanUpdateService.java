package acme.features.manager.workplan;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamFilterService;
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

		request.unbind(entity, model, "title", "executionStart", "executionEnd", "workloadHours", "workloadMinutes", "workloadParsed", "description", "link", "isPrivate");
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
			errors.state(request, now.before(entity.getExecutionStart()), "executionStart", "manager.WorkPlan.form.error.start");

		}
		if (!errors.hasErrors("executionEnd")) {
			// executionEnd must be after executionStart
			errors.state(request, entity.getExecutionEnd().after(entity.getExecutionStart()), "executionEnd", "manager.WorkPlan.form.error.end");
		}
		final Boolean isSpam = this.spamFilterService.isSpam(entity.getTitle(), entity.getTasks().toString());
		errors.state(request, !isSpam, "*", "manager.WorkPlan.form.error.spamDetected");
		
		if (!errors.hasErrors("executionStart") && !errors.hasErrors("executionEnd") && !errors.hasErrors("workloadHours") && !errors.hasErrors("workloadMinutes")) {
			// workload can't exceed the time between execution start and execution end
			final long minutes = Math.abs(entity.getExecutionStart().getTime() - entity.getExecutionEnd().getTime()) / (60 * 1000);
			final boolean tooMuchWorkload = minutes < (entity.getWorkloadHours() * 60 + (entity.getWorkloadMinutes() == null ? 0 : entity.getWorkloadMinutes()));
			errors.state(request, !tooMuchWorkload, "*", "manager.WorkPlan.form.error.tooMuchWorkload");
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

