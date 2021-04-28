
package acme.entities.workPlans;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
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

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
	
	@Transient
	protected String 			workloadParsed;

	@NotNull
	protected Boolean			isPrivate;
	// Object interface -------------------------------------------------------

	
	public String getWorkloadParsed() {
		String res = "";
		if(this.getWorkloadMinutes() != null) {
			if(this.getWorkloadMinutes()>9) {
				res = this.getWorkloadHours() + ":" + this.getWorkloadMinutes();
			}else {
				res = this.getWorkloadHours() + ":0" + this.getWorkloadMinutes();
			}
			
		}else {
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
