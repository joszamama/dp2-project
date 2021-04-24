package acme.features.administrator.parameters;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.Spam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorParametersRepository extends AbstractRepository {

	@Query("select t from Spam t")
	Collection<Spam> findSpamParameters(); //????


}
