
package acme.features.administrator.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(t) from Task t")
	Integer countTotalTasks();

	@Query("select count(t) from Task t where t.executionEnd > current_date()")
	Integer countNotFinishedTasks();

	@Query("select count(t) from Task t where t.executionEnd <= current_date()")
	Integer countFinishedTasks();

	@Query("select count(t) from Task t where t.isPrivate = false")
	Integer countPublicTasks();

	@Query("select count(t) from Task t where t.isPrivate = true")
	Integer countPrivateTasks();
	
	@Query("select t from Task t")
	Collection<Task> findMany();

}
