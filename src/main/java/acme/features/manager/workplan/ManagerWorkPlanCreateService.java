package acme.features.manager.workplan;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamFilterService;
import acme.entities.tasks.Task;
import acme.entities.workPlans.WorkPlan;
import acme.features.manager.ManagerRepository;
import acme.features.manager.task.ManagerTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerWorkPlanCreateService implements AbstractCreateService<Manager, WorkPlan>{
	
	@Autowired
	private ManagerWorkPlanRepository	repository;
	@Autowired
	private ManagerRepository		managerRepo;
	
	@Autowired
	private ManagerTaskRepository		managerTaskRepo;

	@Autowired
	protected SpamFilterService		spamFilterService;


	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;

		return true;
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

		request.unbind(entity, model, "title", "tasks", "executionStart", "executionEnd", "workloadHours", "workloadMinutes","workloadParsed", "isPrivate");

	}

	@Override
	public WorkPlan instantiate(final Request<WorkPlan> request) {
		assert request != null;

		final WorkPlan result;
		final Manager manager;
		final List<Task> tasks;

		//manager = this.managerRepo.findOne(request.getPrincipal().getActiveRoleId());
		tasks = this.managerTaskRepo.findMany().stream().collect(Collectors.toList());
		
		result = new WorkPlan();
		result.setTasks(tasks);
//		result.setOwner(manager);
		result.setWorkloadParsed("01:00");

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
		
		if (!errors.hasErrors("executionStart") && !errors.hasErrors("executionEnd") && !errors.hasErrors("workloadHours") && !errors.hasErrors("workloadMinutes")) {
			// workload can't exceed the time between execution start and execution end
			final long minutes = Math.abs(entity.getExecutionStart().getTime() - entity.getExecutionEnd().getTime()) / (60 * 1000);
			final boolean tooMuchWorkload = minutes < (entity.getWorkloadHours() * 60 + (entity.getWorkloadMinutes() == null ? 0 : entity.getWorkloadMinutes()));
			errors.state(request, !tooMuchWorkload, "*", "manager.work-plan.form.error.tooMuchWorkload");
		}
		
	}

	@Override
	public void create(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;
		
		System.out.println("Workload: "+ entity.getWorkloadParsed());
		
		entity.setWorkloadParsed(entity.getWorkloadParsed());

		final boolean isSpam = this.spamFilterService.isSpam(entity.getTitle(), entity.getTasks().toString());
		if (isSpam == false) {
			this.repository.save(entity);
		} else {
			System.out.println("SPAM: " + entity.getTitle() + " " + entity.getTasks().toString());
			System.out.println("Mensaje borrado");
		}
	}

}
