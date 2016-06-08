package Interface.exceptions;

public class PaperNotFoundException extends Exception {
	
	private static final long serialVersionUID = -8198378621415235815L;
	
	public String[] args;
	
	public PaperNotFoundException() {
		this.args = new String[0];
	}
	
	public PaperNotFoundException(final String message) {
		super(message);
		this.args = new String[0];
	}
}
