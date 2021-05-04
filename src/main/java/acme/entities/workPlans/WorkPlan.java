
package acme.entities.workPlans;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import acme.framework.entities.Manager;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@ManyToOne
	protected Manager			owner;

	@ManyToMany(fetch = FetchType.EAGER)
	protected List<Task>		tasks;

	@Transient
	protected String			tasksParsed;

	@NotBlank
	@Length(max = 80)
	protected String			title;

	@Min(0)
	protected Integer			workloadHours;

	@Min(0)
	@Max(60)
	protected Integer			workloadMinutes;

	@Transient
	protected String			workloadParsed;

	@NotNull
	protected Date				executionStart;

	@NotNull
	protected Date				executionEnd;

	@NotNull
	protected Boolean			isPrivate;

	// Object interface -------------------------------------------------------

	// Derived attributes -----------------------------------------------------


	public void setWorkloadParsed(String workload) {
		workload = workload.trim();
		final String[] work = workload.split(":");
		this.setWorkloadHours(Integer.valueOf(work[0]));
		this.setWorkloadMinutes(Integer.valueOf(work[1]));
	}

	public void getExecutionPeriod() {
		Date start = this.executionStart;
		Date end = this.executionEnd;
		for (final Task task : this.tasks) {
			if (task.getExecutionStart().before(start)) {
				start = task.getExecutionStart();
			}
			if (task.getExecutionEnd().after(end)) {
				end = task.getExecutionEnd();
			}
		}
		this.setExecutionEnd(end);
		this.setExecutionStart(start);
	}

	public String getWorkloadParsed() {
		Integer resH = 0;
		Integer resM = 0;
		for (final Task task : this.tasks) {
			resH += task.getWorkloadHours();
			resM += task.getWorkloadMinutes();
		}
		this.setWorkloadHours(resH);
		this.setWorkloadMinutes(resM);

		while (resM >= 60) {
			resH += 1;
			resM -= 60;
		}
		String res = "";
		if (resM != null) {
			if (resM > 9) {
				res = resH + ":" + resM;
			} else {
				res = resH + ":0" + resM;
			}

		}
		return res;

	}

}
