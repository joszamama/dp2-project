package acme.features.manager.workplan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workPlans.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkPlanRepository extends AbstractRepository {
	
	@Query("select w WorkPlan w where w.id = ?1")
	WorkPlan findOne(int id);

	@Query("select w from WorkPlan w")
	Collection<WorkPlan> findMany();

//	@Query("select w from WorkPlan w where w.title = ?1")
//	Collection<WorkPlan> findByOwner(int id);
	
	@Query("select w from WorkPlan w where w.tasks.id = ?1")
	Collection<WorkPlan> findByTasks(int id);
	
	

}
