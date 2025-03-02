package org.example.apex.exceptions;

public class CipherNotFound extends RuntimeException {
    public CipherNotFound(String message) {
        super(message);
    }
}
