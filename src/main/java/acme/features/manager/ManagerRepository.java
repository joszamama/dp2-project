package acme.features.manager;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Manager;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerRepository extends AbstractRepository {

	@Query("select m from Manager m where m.id = ?1")
	Manager findOne(int id);

	@Query("select m from Manager m where m.id = ?1")
	Manager findManagerById(int id);

	@Query("select m from Manager m where m.userAccount.id = ?1")
	Manager findManagerByUserAccountId(int id);
}
