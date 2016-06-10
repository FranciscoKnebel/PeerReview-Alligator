package Interface.exceptions;

public class ConferenceNotFoundException extends Exception {

	private static final long serialVersionUID = -6544930467932823977L;
	
	public String[] args;
	
	public ConferenceNotFoundException() {
		this.args = new String[0];
	}
	
	public ConferenceNotFoundException(final String message) {
		super(message);
		this.args = new String[0];
	}

}
