
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

	//@Query(value = "select avg(TIMESTAMPDIFF(SECOND,execution_start,execution_end)) as avgTimeSeconds from task", nativeQuery = true)
	@Query("select avg(to_seconds(t.executionEnd)-to_seconds(t.executionStart)) from Task t")
	Double averageExecutionPeriods();

	//@Query(value = "select stddev(TIMESTAMPDIFF(SECOND,execution_start,execution_end)) as stddevTimeSeconds from task", nativeQuery = true)
	@Query("select stddev(to_seconds(t.executionEnd)-to_seconds(t.executionStart)) from Task t")
	Double deviationExecutionPeriods();

	//@Query(value = "select min(TIMESTAMPDIFF(SECOND,execution_start,execution_end)) as minTimeSeconds from task", nativeQuery = true)
	@Query("select min(to_seconds(t.executionEnd)-to_seconds(t.executionStart)) from Task t") 
	Double minimumExecutionPeriods();

	//@Query(value = "select max(TIMESTAMPDIFF(SECOND,execution_start,execution_end)) as maxTimeSeconds from task", nativeQuery = true)
	@Query("select max(to_seconds(t.executionEnd)-to_seconds(t.executionStart)) from Task t")
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
