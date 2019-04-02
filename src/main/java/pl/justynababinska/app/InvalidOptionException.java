package pl.justynababinska.app;

public class InvalidOptionException extends RuntimeException{
	private static final long serialVersionUID = 6000428720802508309L;

	public InvalidOptionException() {
		super("Podany numer opcji nie istenieje");
	}
}
