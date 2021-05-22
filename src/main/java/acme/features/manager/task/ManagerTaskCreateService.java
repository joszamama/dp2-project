
package acme.features.manager.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamFilterService;
import acme.entities.tasks.Task;
import acme.features.manager.ManagerRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task> {

	@Autowired
	private ManagerTaskRepository	repository;
	@Autowired
	private ManagerRepository		managerRepo;

	@Autowired
	protected SpamFilterService		spamFilterService;


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "executionStart", "executionEnd", "workloadHours", "workloadMinutes", "workloadParsed", "description", "link", "isPrivate");

	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;

		Task result;
		Manager manager;

		manager = this.managerRepo.findOne(request.getPrincipal().getActiveRoleId());
		result = new Task();
		result.setOwner(manager);
		result.setWorkloadParsed("01:00");

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		errors.state(request, entity.getWorkloadParsed().matches("^\\d+:[0-5][0-9]$"), "workloadParsed", "manager.task.form.error.workloadParsedFormat");
		
		if (!errors.hasErrors("executionStart")) {
			if (entity.getExecutionStart() != null && entity.getExecutionEnd() != null) {
				// executionStart must be in the future
				final Date now = new Date();
				errors.state(request, now.before(entity.getExecutionStart()), "executionStart", "manager.task.form.error.start");
			} else {
				if (entity.getExecutionStart() == null) {
					errors.state(request, true, "executionStart", "manager.task.form.error.start");
				}
				if (entity.getExecutionEnd() == null) {
					errors.state(request, true, "executionEnd", "manager.task.form.error.end");
				}
			}
		}
		if (!errors.hasErrors("executionEnd")) {
			if (entity.getExecutionStart() != null && entity.getExecutionEnd() != null) {
				// executionEnd must be after executionStart
				errors.state(request, entity.getExecutionEnd().after(entity.getExecutionStart()), "executionEnd", "manager.task.form.error.end");
			} else {
				if (entity.getExecutionStart() == null) {
					errors.state(request, true, "executionStart", "manager.task.form.error.start");
				}
				if (entity.getExecutionEnd() == null) {
					errors.state(request, true, "executionEnd", "manager.task.form.error.end");
				}
			}
		}
		final Boolean isSpam = this.spamFilterService.isSpam(entity.getTitle(), entity.getDescription());
		errors.state(request, !isSpam, "*", "manager.task.form.error.spamDetected");

		if (!errors.hasErrors("executionStart") && !errors.hasErrors("executionEnd") && !errors.hasErrors("workloadHours") && !errors.hasErrors("workloadMinutes")) {
			if (entity.getExecutionStart() != null && entity.getExecutionEnd() != null) {
				// workload can't exceed the time between execution start and execution end
				final long minutes = Math.abs(entity.getExecutionStart().getTime() - entity.getExecutionEnd().getTime()) / (60 * 1000);
				final boolean tooMuchWorkload = minutes < (entity.getWorkloadHours() * 60 + (entity.getWorkloadMinutes() == null ? 0 : entity.getWorkloadMinutes()));
				errors.state(request, !tooMuchWorkload, "*", "manager.task.form.error.tooMuchWorkload");
			} else {
				if (entity.getExecutionStart() == null) {
					errors.state(request, true, "executionStart", "manager.task.form.error.start");
				}
				if (entity.getExecutionEnd() == null) {
					errors.state(request, true, "executionEnd", "manager.task.form.error.end");
				}
			}
		}

	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		System.out.println("Workload: " + entity.getWorkloadParsed());

		entity.setWorkloadParsed(entity.getWorkloadParsed());

		final boolean isSpam = this.spamFilterService.isSpam(entity.getTitle(), entity.getDescription());
		if (!isSpam) {
			this.repository.save(entity);
		} else {
			System.out.println("SPAM: " + entity.getTitle() + " " + entity.getDescription());
			System.out.println("Mensaje borrado");
		}
	}

}
