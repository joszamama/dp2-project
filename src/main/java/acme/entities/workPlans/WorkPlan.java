
package acme.entities.workPlans;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	protected List<Task>		tasks;

	@NotBlank
	@Length(max = 80)
	protected String			title;

	@NotNull
	protected Date				executionStart;

	@NotNull
	protected Date				executionEnd;

	@Min(0)
	@NotNull
	protected Integer			workloadHours;

	@Min(0)
	@Max(60)
	protected Integer			workloadMinutes;

	@NotNull
	protected Boolean			isPrivate;
	// Object interface -------------------------------------------------------

}
