package com.miller.exception;

public class SolicitationNotFoundException extends RuntimeException {
    public SolicitationNotFoundException() {
        super("Solicitation not found");
    }
}
