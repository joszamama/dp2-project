package acme.features.manager.workplan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlans.WorkPlan;
import acme.features.manager.ManagerRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class ManagerWorkPlanListService implements AbstractListService<Manager, WorkPlan>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected ManagerWorkPlanRepository repository;
		
		@Autowired
		private ManagerRepository		managerRepository;


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

			request.unbind(entity, model, "title", "executionStart", "executionEnd", "workloadParsed");
		}

		@Override
		public Collection<WorkPlan> findMany(final Request<WorkPlan> request) {
			assert request != null;

			Collection<WorkPlan> result;
			final Principal principal = request.getPrincipal();
			final Manager manager = this.managerRepository.findManagerByUserAccountId(principal.getAccountId());
			
			result = this.repository.findByOwner(manager.getId());

			return result;
		}

}
