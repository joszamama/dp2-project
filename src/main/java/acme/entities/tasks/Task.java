
package acme.entities.tasks;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
	@ManyToOne(cascade=CascadeType.ALL)
	@NotNull
	protected Manager owner;
	
	@NotBlank
	@Length(max = 80)
	protected String			title;

	//Commented out to create finished tasks in the database
	//@Future
	@NotNull
	protected Date				executionStart;

	//Commented out to create finished tasks in the database
	//@Future
	@NotNull
	protected Date				executionEnd;

	@NotNull
	protected Double			workload; // hours 

	@NotBlank
	@Length(max = 500)
	protected String			description;
	
	@URL
	protected String			link;

	@NotNull
	protected Boolean			isPrivate;
	// Object interface -------------------------------------------------------

}
