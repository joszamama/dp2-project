
package acme.framework.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@NotEmpty
	@Length(max = 80)
	protected String			title;

	@Future
	protected Date			executionStart;

	@Future
	protected Date			executionEnd;

	protected Integer			workload;

	@NotEmpty
	@Length(max = 500)
	protected String			description;

	protected String			link;

	@NotNull
	protected Boolean			isPrivate;

	// Object interface -------------------------------------------------------

}
