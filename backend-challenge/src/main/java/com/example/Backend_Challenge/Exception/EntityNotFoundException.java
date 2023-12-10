package com.example.backend_challenge.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String entityName, Long id){

        super("The " + entityName + " with identifier '" + id + "' does not exist in our records");
    }
}
