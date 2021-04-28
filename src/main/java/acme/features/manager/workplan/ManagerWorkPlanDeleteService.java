package acme.features.manager.workplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlans.WorkPlan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagerWorkPlanDeleteService implements AbstractDeleteService<Manager, WorkPlan> {

	@Autowired
	private ManagerWorkPlanRepository repository;


	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;

		boolean result;
		int WorkPlanId;
		WorkPlan WorkPlan;
		final Manager manager;
		final Principal principal;

		WorkPlanId = request.getModel().getInteger("id");
		WorkPlan = this.repository.findOne(WorkPlanId);
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

		request.unbind(entity, model, "title","tasks", "workloadParsed", "executionStart", "executionEnd", "workloadHours", "workloadMinutes", "isPrivate");
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
	}

	@Override
	public void delete(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}
}
