package com.nelioalves.cursomc.services.exceptions;

public class DataIntegrityViolation extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataIntegrityViolation(String msg) {
        super(msg);
    }

    public DataIntegrityViolation(String msg, Throwable cause) {
        super(msg, cause);
    }

}
