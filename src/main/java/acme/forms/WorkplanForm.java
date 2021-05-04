
package acme.forms;

import java.io.Serializable;
import java.util.Date;

public class WorkplanForm implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2448257876750745871L;

	protected String			title;
	protected String			tasks;
	protected Date				executionStart;
	protected Date				executionEnd;
	protected Boolean			isPrivate;
}
