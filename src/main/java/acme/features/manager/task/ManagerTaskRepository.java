package acme.features.manager.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.entities.Manager;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {

	@Query("select t from Task t where t.id = ?1")
	Task findOne(int id);

	@Query("select t from Task t where t.id = ?1 and t.isPrivate = false and t.executionEnd > current_date")
	Task findOnePublicAndNotFinished(int id);

	@Query("select t from Task t where t.id = ?1 and t.isPrivate = false and t.executionEnd <= current_date")
	Task findOnePublicAndFinished(int id);

	@Query("select t from Task t")
	Collection<Task> findMany();

	@Query("select t from Task t where t.isPrivate = false and t.executionEnd > current_date")
	Collection<Task> findManyPublicAndNotFinished();

	@Query("select t from Task t where t.isPrivate = false and t.executionEnd <= current_date")
	Collection<Task> findManyPublicAndFinished();
	
	@Query("select t from Task t where t.owner.id = ?1")
	Collection<Task> findByOwner(int id);
	
	@Query("select m from Manager m where m.id = ?1")
	Manager findManagerById(int id);
	
	@Query("select m from Manager m where m.userAccount.id = ?1")
	Manager findManagerByUserAccountId(int id);

}
