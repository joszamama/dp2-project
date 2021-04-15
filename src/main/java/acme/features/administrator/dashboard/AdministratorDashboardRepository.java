
package acme.features.administrator.dashboard;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(t) from Task t where t.executionEnd > current_date")
	Integer countNotFinishedTasks();

	@Query("select count(t) from Task t where t.executionEnd <= current_date")
	Integer countFinishedTasks();

	@Query("select count(t) from Task t where t.isPrivate = false")
	Integer countPublicTasks();

	@Query("select count(t) from Task t where t.isPrivate = true")
	Integer countPrivateTasks();
	
	
	@Query("select avg(t.executionStart) from Task t")
	Double averageExecutionPeriods();

	@Query("select stddev(t.executionStart) from Task t")
	Double deviationExecutionPeriods();
	
	@Query("select min(t.executionStart) from Task t")
	Date minimumExecutionPeriods();
	
	@Query("select max(t.executionStart) from Task t")
	Date maximumExecutionPeriods();
	

	@Query("select avg(t.workload) from Task t")
	Double averageWorkloads();

	@Query("select stddev(t.workload) from Task t")
	Double deviationWorkloads();
	
	@Query("select min(t.workload) from Task t")
	Integer minimumWorkloads();
	
	@Query("select max(t.workload) from Task t")
	Integer maximumWorkloads();

}
