package acme.features.manager.workplan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workPlans.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkPlanRepository extends AbstractRepository {
	
	@Query("select t from WorkPlan t where t.id = ?1")
	WorkPlan findOne(int id);

	@Query("select t from WorkPlan t where t.id = ?1 and t.isPrivate = false and t.executionEnd > current_date")
	WorkPlan findOnePublicAndNotFinished(int id);

	@Query("select t from WorkPlan t where t.id = ?1 and t.isPrivate = false and t.executionEnd <= current_date")
	WorkPlan findOnePublicAndFinished(int id);

	@Query("select t from WorkPlan t")
	Collection<WorkPlan> findMany();

	@Query("select t from WorkPlan t where t.isPrivate = false and t.executionEnd > current_date")
	Collection<WorkPlan> findManyPublicAndNotFinished();

	@Query("select t from WorkPlan t where t.isPrivate = false and t.executionEnd <= current_date")
	Collection<WorkPlan> findManyPublicAndFinished();
	
	@Query("select t from WorkPlan t where t.owner.id = ?1")
	Collection<WorkPlan> findByOwner(int id);
	

}
