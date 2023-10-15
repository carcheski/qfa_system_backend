package br.com.qfa.services.exceptions;

public class DataIntegrityExceptions extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataIntegrityExceptions(String msg) {
		super(msg);
	}
	
	public DataIntegrityExceptions(String msg, Throwable cause) {
		super(msg, cause);
	}

}
