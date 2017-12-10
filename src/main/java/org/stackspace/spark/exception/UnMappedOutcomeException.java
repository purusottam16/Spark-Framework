package org.stackspace.spark.exception;

public class UnMappedOutcomeException extends RuntimeException {

	public UnMappedOutcomeException() {
		super();
	}

	public UnMappedOutcomeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnMappedOutcomeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnMappedOutcomeException(String message) {
		super(message);
	}

	public UnMappedOutcomeException(Throwable cause) {
		super(cause);
	}

}
