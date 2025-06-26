package com.SaaS_Based_Customer_Relationship_Management.CRM.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
