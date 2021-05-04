package acme.features.authenticated.workPlan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.workPlans.WorkPlan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/work-plan/")
public class AuthenticatedWorkPlanController extends AbstractController<Authenticated, WorkPlan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedWorkPlanListService	listService;
	
	@Autowired
	protected AuthenticatedWorkPlanShowService	showService;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
