package acme.features.manager.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task>{

	@Autowired
	private ManagerTaskRepository repository;
	
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

		request.unbind(entity, model, "title", "executionStart", "executionEnd", "workload");
		request.unbind(entity, model, "owner","description","link","isPrivate");		 	
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;

		Task result;
		Manager manager;

		manager = this.repository.findManagerById(request.getPrincipal().getActiveRoleId());
		result = new Task();
		result.setOwner(manager);
		result.setIsPrivate(true);

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("executionStart")) {
			//executionStart in the past
			final Date now = new Date();
			errors.state(request, !now.after(entity.getExecutionStart()), "executionStart", "manager.task.form.error.start");
			
		}
		if (!errors.hasErrors("executionEnd")) {
			//executionEnd before start
			errors.state(request, !entity.getExecutionEnd().before(entity.getExecutionStart()), "executionEnd", "manager.task.form.error.end");
		}
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		

		this.repository.save(entity);
	}

}
