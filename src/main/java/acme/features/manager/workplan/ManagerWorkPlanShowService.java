package acme.features.manager.workplan;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.entities.workPlans.WorkPlan;
import acme.features.manager.ManagerRepository;
import acme.features.manager.task.ManagerTaskRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerWorkPlanShowService implements AbstractShowService<Manager, WorkPlan>{
	
	@Autowired
	protected ManagerWorkPlanRepository repository;
	@Autowired
	private ManagerTaskRepository		managerTaskRepo;
	@Autowired
	private ManagerRepository managerRepo;
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;

		boolean result;
		final int workPlanId;
		final WorkPlan workPlan;
		final Manager manager;
		final Principal principal;

		workPlanId = request.getModel().getInteger("id");
		workPlan = this.repository.findOne(workPlanId);
		manager = workPlan.getOwner();
		principal = request.getPrincipal();
		result = manager.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final List<Task> tasks;
		final Manager manager = this.managerRepo.findOne(request.getPrincipal().getActiveRoleId());
		tasks = this.managerTaskRepo.findByOwnerAndNotStarted(manager.getId(), new Date()).stream().collect(Collectors.toList());

		model.setAttribute("allTasks", tasks);
		request.unbind(entity, model, "title", "tasks", "executionStart", "executionEnd", "isPrivate", "tasksParsed");

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
