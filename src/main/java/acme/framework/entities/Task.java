
package acme.framework.entities;

import java.time.Duration;
import java.time.Instant;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Task extends DomainEntity {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@NotEmpty
	@Length(max = 80)
	protected String			title;

	@Future
	protected Instant			executionStart;

	@Future
	protected Instant			executionEnd;

	@NotBlank
	// NO SÉ A QUE SE REFIERE CON "obviously, the workload must fit within the execution period".
	protected Duration			workload;

	@NotEmpty
	@Length(max = 500)
	protected String			description;

	protected String			link;

	@NotNull
	protected Boolean			isPrivate;

	// Object interface -------------------------------------------------------


	public void setWorkload() {
		this.workload = Duration.between(this.executionStart, this.executionEnd);
	}
}
