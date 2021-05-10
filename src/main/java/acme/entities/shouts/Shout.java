
package acme.entities.shouts;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Shout extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Past
	protected Date				moment;

	@NotBlank
	@Length(min = 5, max = 25)
	protected String			author;

	@NotBlank
	@Length(min = 1, max = 100)
	protected String			text;

	@URL
	// This attribute is optional!
	protected String			info;


	// Derived attributes -----------------------------------------------------
	public void setAuthor(final String author) {
		this.author = author.trim();
	}

	// Relationships ----------------------------------------------------------
}
