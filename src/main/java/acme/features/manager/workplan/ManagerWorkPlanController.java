package acme.features.manager.workplan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.workPlans.WorkPlan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Manager;

@Controller
@RequestMapping("/manager/work-plan/")
public class ManagerWorkPlanController extends AbstractController<Manager, WorkPlan>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkPlanUpdateService	updateService;
	
	@Autowired
	protected ManagerWorkPlanShowService	showService;

	@Autowired
	protected ManagerWorkPlanCreateService	createService;

	@Autowired
	protected ManagerWorkPlanDeleteService	deleteService;

	@Autowired
	protected ManagerWorkPlanListService	workPlanService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.LIST, this.workPlanService); 
	}

}
