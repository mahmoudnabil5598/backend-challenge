package com.example.backend_challenge.exception;

public class PromoCodeUsageLimitExceededException extends Throwable {
    public PromoCodeUsageLimitExceededException(String s) {
        super(s);
    }
}
