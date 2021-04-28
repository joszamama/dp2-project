package acme.features.manager.workplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlans.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerWorkPlanShowService implements AbstractShowService<Manager, WorkPlan>{
	
	@Autowired
	protected ManagerWorkPlanRepository repository;
	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;

		final boolean result;
		int WorkPlanId;
		WorkPlan WorkPlan;
		final Manager manager;
		Principal principal;

		WorkPlanId = request.getModel().getInteger("id");
		WorkPlan = this.repository.findOne(WorkPlanId);
		// manager = WorkPlan.getOwner();
		principal = request.getPrincipal();
		// result = manager.getUserAccount().getId() == principal.getAccountId();
		result = true;
		return result;
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "executionStart", "executionEnd", "workloadHours", "workloadMinutes", "workloadParsed");
		request.unbind(entity, model, "tasks","isPrivate");	
//		request.unbind(entity, model, );
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
