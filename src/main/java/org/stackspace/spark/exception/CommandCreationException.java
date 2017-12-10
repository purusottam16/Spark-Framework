package org.stackspace.spark.exception;

public class CommandCreationException extends RuntimeException {

	public CommandCreationException() {
		super();
	}

	public CommandCreationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CommandCreationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandCreationException(String message) {
		super(message);
	}

	public CommandCreationException(Throwable cause) {
		super(cause);
	}

}
