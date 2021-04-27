package acme.features.manager.workplan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlans.WorkPlan;
import acme.features.manager.ManagerRepository;
import acme.features.manager.task.ManagerTaskRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class ManagerWorkPlanListService implements AbstractListService<Manager, WorkPlan>{

	@Autowired
	private ManagerWorkPlanRepository	repository;
	
	@Autowired
	private ManagerTaskRepository	taskRepository;

	@Autowired
	private ManagerRepository		managerRepository;


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

		request.unbind(entity, model, "title", "executionStart", "executionEnd", "workloadHours", "workloadMinutes", "workloadParsed");
	}

	@Override
	public Collection<WorkPlan> findMany(final Request<WorkPlan> request) {
		assert request != null;

		final Collection<WorkPlan> result;

		final Principal principal = request.getPrincipal();
		final Manager manager = this.managerRepository.findManagerByUserAccountId(principal.getAccountId());

//		final Principal principal = request.getPrincipal();
//		final Manager manager = this.managerRepository.findManagerByUserAccountId(principal.getAccountId());
//		result = this.repository.findByOwner(manager.getId());
		
//		final Task task = this.taskRepository.find
		result = this.repository.findByTasks(this.taskRepository.findByOwner(manager.getId()).hashCode());

		return result;
	}
}
