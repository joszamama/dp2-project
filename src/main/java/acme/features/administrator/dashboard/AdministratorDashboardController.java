package acme.features.administrator.dashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;
import acme.framework.entities.Task;

@Controller
@RequestMapping("/administrator/dashboard/")
public class AdministratorDashboardController extends AbstractController<Administrator, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardListService	listService;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCustomCommand(CustomCommand.DASHBOARD, BasicCommand.LIST, this.listService);
	}

}
