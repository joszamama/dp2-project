
package acme.features.administrator.parameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorParametersShowService implements AbstractShowService<Administrator, Spam> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorParametersRepository repository;

	// AbstractListService<Administrator, Task> interface --------------


	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "wordList", "threshold");
	}

	@Override
	public Spam findOne(final Request<Spam> request) {
		assert request != null;

		Spam result;
		result = this.repository.findSpamParameters().iterator().next();

		return result;
	}

}

