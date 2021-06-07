
package acme.features.administrator.dashboard;

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

	@Query("select avg(to_days(t.executionEnd)-to_days(t.executionStart)) from Task t")
	Double averageExecutionPeriods();

	@Query("select stddev(to_days(t.executionEnd)-to_days(t.executionStart)) from Task t")
	Double deviationExecutionPeriods();

	@Query("select min(t.executionEnd-t.executionStart) from Task t") 
	Double minimumExecutionPeriods();

	@Query("select max(t.executionEnd-t.executionStart) from Task t")
	Double maximumExecutionPeriods();

	@Query("select avg(t.workloadHours * 60 + t.workloadMinutes) from Task t")
	Double averageWorkloads();

	@Query("select stddev(t.workloadHours * 60 + t.workloadMinutes) from Task t")
	Double deviationWorkloads();

	@Query("select min(t.workloadHours * 60 + t.workloadMinutes) from Task t")
	Double minimumWorkloads();

	@Query("select max(t.workloadHours * 60 + t.workloadMinutes) from Task t")
	Double maximumWorkloads();
}
