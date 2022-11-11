package edu.school21.exceptions;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException() {
        super("Error: no user is found with the login specified");
    }
}
