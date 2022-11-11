package edu.school21.exceptions;

public class AlreadyAuthenticatedException extends Exception{
    public AlreadyAuthenticatedException() {
        super("Error: user already authenticated");
    }
}
