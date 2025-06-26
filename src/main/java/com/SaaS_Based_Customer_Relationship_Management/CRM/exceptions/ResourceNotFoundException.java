package com.SaaS_Based_Customer_Relationship_Management.CRM.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
