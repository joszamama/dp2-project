
package acme.features.administrator.dashboard;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Task;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorDashboardListService implements AbstractListService<Administrator, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractListService<Administrator, Task> interface --------------


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

		request.unbind(entity, model, "executionStart", "executionEnd", "workload", "isPrivate");
		model.setAttribute("total", this.countTotalTasks(request));
		model.setAttribute("notFinished", this.countNotFinishedTasks(request));
		model.setAttribute("finished", this.countFinishedTasks(request));
		model.setAttribute("public", this.countPublicTasks(request));
		model.setAttribute("private", this.countPrivateTasks(request));
	}

	public Integer countTotalTasks(final Request<Task> request) {
		assert request != null;

		Integer result;

		result = this.repository.countTotalTasks();

		return result;
	}

	public Integer countNotFinishedTasks(final Request<Task> request) {
		assert request != null;

		Integer result;

		result = this.repository.countNotFinishedTasks();

		return result;
	}

	public Integer countFinishedTasks(final Request<Task> request) {
		assert request != null;

		Integer result;

		result = this.repository.countFinishedTasks();

		return result;
	}

	public Integer countPublicTasks(final Request<Task> request) {
		assert request != null;

		Integer result;

		result = this.repository.countPublicTasks();

		return result;
	}

	public Integer countPrivateTasks(final Request<Task> request) {
		assert request != null;

		Integer result;

		result = this.repository.countPrivateTasks();

		return result;
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		assert request != null;

		Collection<Task> result;

		result = this.repository.findMany();

		return result;
	}

}
