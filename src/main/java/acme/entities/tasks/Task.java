
package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.Manager;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@ManyToOne
	@NotNull
	protected Manager owner;

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

	@NotBlank
	@Length(max = 500)
	protected String			description;
	
	@URL
	protected String			link;

	@NotNull
	protected Boolean			isPrivate;
	// Object interface -------------------------------------------------------

}
