package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedTaskRepository extends AbstractRepository {

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

}
