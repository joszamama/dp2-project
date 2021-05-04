
package acme.features.authenticated.workPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlans.WorkPlan;
import acme.features.authenticated.task.AuthenticatedTaskRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedWorkPlanShowService implements AbstractShowService<Authenticated, WorkPlan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedWorkPlanRepository repository;
	@Autowired
	protected AuthenticatedTaskRepository taskRepository;

	// AbstractListService<Administrator, Task> interface --------------


	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "tasks", "executionStart", "executionEnd", "isPrivate", "tasksParsed", "workloadParsed");

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

}
