
package acme.features.administrator.dashboard;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workPlans.WorkPlan;
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

	@Query("select min(to_days(t.executionEnd)-to_days(t.executionStart)) from Task t")
	Integer minimumExecutionPeriods();

	@Query("select max(to_days(t.executionEnd)-to_days(t.executionStart)) from Task t")
	Integer maximumExecutionPeriods();

	@Query("select avg(t.workloadHours * 60 + t.workloadMinutes) from Task t")
	Double averageWorkloads();

	@Query("select stddev(t.workloadHours * 60 + t.workloadMinutes) from Task t")
	Double deviationWorkloads();

	@Query("select min(t.workloadHours * 60 + t.workloadMinutes) from Task t")
	Double minimumWorkloads();

	@Query("select max(t.workloadHours * 60 + t.workloadMinutes) from Task t")
	Double maximumWorkloads();

	// WorkPlan queries

	@Query("select count(wp) from WorkPlan wp where wp.isPrivate = false")
	Integer countPublicWorkplans();

	@Query("select count(wp) from WorkPlan wp where wp.isPrivate = true")
	Integer countPrivateWorkplans();

	@Query("select count(wp) from WorkPlan wp where wp.executionEnd > current_date")
	Integer countNotFinishedWorkplans();

	@Query("select count(wp) from WorkPlan wp where wp.executionEnd <= current_date")
	Integer countFinishedWorkplans();

	@Query("select avg(to_days(wp.executionEnd)-to_days(wp.executionStart)) from WorkPlan wp")
	Double averageWorkplanExecutionPeriods();

	@Query("select stddev(to_days(wp.executionEnd)-to_days(wp.executionStart)) from WorkPlan wp")
	Double deviationWorkplanExecutionPeriods();

	@Query("select min(to_days(wp.executionEnd)-to_days(wp.executionStart)) from WorkPlan wp")
	Integer minimumWorkplanExecutionPeriods();

	@Query("select max(to_days(wp.executionEnd)-to_days(wp.executionStart)) from WorkPlan wp")
	Integer maximumWorkplanExecutionPeriods();

	@Query("select avg(wp.workloadHours * 60 + wp.workloadMinutes) from WorkPlan wp")
	Double averageWorkplanWorkloads();

	@Query("select stddev(wp.workloadHours * 60 + wp.workloadMinutes) from WorkPlan wp")
	Double deviationWorkplanWorkloads();

	@Query("select min(wp.workloadHours * 60 + wp.workloadMinutes) from WorkPlan wp")
	Double minimumWorkplanWorkloads();

	@Query("select max(wp.workloadHours * 60 + wp.workloadMinutes) from WorkPlan wp")
	Double maximumWorkplanWorkloads();
	
	@Query("select w from WorkPlan w ")
	Set<WorkPlan> findAllWorkPlans ();
}
