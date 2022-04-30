package net.stone.blue.codetest.exception;

public class EntityNotRegisterException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntityNotRegisterException() {
		super();
	}
	
	public EntityNotRegisterException(String message) {
		super(message);
	}
	
	public EntityNotRegisterException(Throwable cause) {
		super(cause);
	}

	public EntityNotRegisterException(String message, Throwable cause) {
		super(message, cause);
	}
}
