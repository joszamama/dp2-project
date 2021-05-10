
package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
	protected Manager			owner;

	@NotBlank
	@Length(min = 1, max = 80)
	protected String			title;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				executionStart;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				executionEnd;

	@Min(0)
	@NotNull
	protected Integer			workloadHours;

	@Min(0)
	@Max(60)
	protected Integer			workloadMinutes;

	@Transient
	protected String			workloadParsed;

	@NotBlank
	@Length(min = 1, max = 500)
	protected String			description;

	@URL
	protected String			link;

	@NotNull
	protected Boolean			isPrivate;

	// Object interface -------------------------------------------------------


	public String getWorkloadParsed() {
		String res = "";
		if (this.getWorkloadMinutes() != null) {
			if (this.getWorkloadMinutes() > 9) {
				res = this.getWorkloadHours() + ":" + this.getWorkloadMinutes();
			} else {
				res = this.getWorkloadHours() + ":0" + this.getWorkloadMinutes();
			}

		} else {
			res = this.getWorkloadHours() + ":00";
		}
		return res;
	}

	public void setWorkloadParsed(String workload) {
		workload = workload.trim();
		final String[] work = workload.split(":");
		this.setWorkloadHours(Integer.valueOf(work[0]));
		this.setWorkloadMinutes(Integer.valueOf(work[1]));
	}

}
