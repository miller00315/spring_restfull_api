package com.miller.exceptions;

public class SolicitationNotFoundException extends RuntimeException {
    public SolicitationNotFoundException() {
        super("Solicitation not found");
    }
}
