package acme.features.manager.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.features.manager.ManagerRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class ManagerTaskListService implements AbstractListService<Manager, Task> {

	@Autowired
	private ManagerTaskRepository	repository;

	@Autowired
	private ManagerRepository		managerRepository;


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "executionStart", "executionEnd", "workload");
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		assert request != null;

		Collection<Task> result;

		final Principal principal = request.getPrincipal();
		final Manager manager = this.managerRepository.findManagerByUserAccountId(principal.getAccountId());
		result = this.repository.findByOwner(manager.getId());

		return result;
	}

}
