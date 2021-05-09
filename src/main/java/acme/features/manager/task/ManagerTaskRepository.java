
package acme.features.manager.task;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {

	@Query("select t from Task t where t.id = ?1")
	Task findOne(int id);

	@Query("select t from Task t")
	Collection<Task> findMany();

	@Query("select t from Task t where t.owner.id = ?1")
	Collection<Task> findByOwner(int id);

	@Query("select t from Task t where t.owner.id= ?1 and t.executionStart > ?2")
	Collection<Task> findByOwnerAndNotStarted(int id, Date date);

}
