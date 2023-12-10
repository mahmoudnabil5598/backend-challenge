package com.example.backend_challenge.exception;

public class SoldOutException extends RuntimeException{

    public SoldOutException(int quantity , Long id){

        super("You can not purchase this quantity "+quantity +" from the product with id "+id);
    }
}
