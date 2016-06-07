package Interface;

public class ReviewerNotFoundException extends Exception {

	private static final long serialVersionUID = 6993346947973925528L;
	
	public String[] args;
	
	public ReviewerNotFoundException() {
		this.args = new String[0];
	}
	
	public ReviewerNotFoundException(final String message) {
		super(message);
		this.args = new String[0];
	}
}
