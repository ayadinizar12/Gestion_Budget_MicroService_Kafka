package com.gestion.budget.communes.manager.exception;

public class MapperException extends RuntimeException {

	public MapperException(String msg) {
	        super(msg);
	    }
	public MapperException(String msg, Throwable t) {
        super(msg, t);
    }

   
}
