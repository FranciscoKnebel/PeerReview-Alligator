package Interface.exceptions;

public class GradeValueException extends Exception {

	private static final long serialVersionUID = 3445331850582014073L;
	
	public String[] args;
	
	public GradeValueException() {
		this.args = new String[0];
	}
	
	public GradeValueException(final String message) {
		super(message);
		this.args = new String[0];
	}
}
