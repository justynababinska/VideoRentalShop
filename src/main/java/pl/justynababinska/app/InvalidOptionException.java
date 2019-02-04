package pl.justynababinska.app;

public class InvalidOptionException extends RuntimeException{

	public InvalidOptionException() {
		super("Podany numer opcji nie istenieje");
	}
}
