package org.stackspace.spark.exception;

public class UnableToDispatchViewException extends RuntimeException {

	public UnableToDispatchViewException() {
		super();
	}

	public UnableToDispatchViewException(String arg0, Throwable arg1,
			boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public UnableToDispatchViewException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UnableToDispatchViewException(String arg0) {
		super(arg0);
	}

	public UnableToDispatchViewException(Throwable arg0) {
		super(arg0);
	}

}