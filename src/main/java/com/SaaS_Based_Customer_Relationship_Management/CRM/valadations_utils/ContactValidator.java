package com.SaaS_Based_Customer_Relationship_Management.CRM.valadations_utils;

import com.SaaS_Based_Customer_Relationship_Management.CRM.data_access_layer.ContactRepoService;
import com.SaaS_Based_Customer_Relationship_Management.CRM.data_access_layer.CustomerRepoService;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateContactDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateContactDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ContactValidator {

    private final ContactRepoService contactRepoService;
    private final CustomerRepoService customerRepoService;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validateCreateRequest(CreateContactDTO dto) {
        Objects.requireNonNull(dto, "CreateContactDTO cannot be null");
        validateJsrViolations(dto);
        validateCustomerExists(dto.getCustomerId());
    }

    public void validateUpdateRequest(Long contactId, UpdateContactDTO dto) {
        Objects.requireNonNull(dto, "UpdateContactDTO cannot be null");
        validateJsrViolations(dto);
        contactRepoService.findById(contactId); // ensures contact exists
    }

    private <T> void validateJsrViolations(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Validation failed: " + violations);
        }
    }

    private void validateCustomerExists(Long customerId) {
        customerRepoService.findById(customerId); // throws if not found
        Objects.requireNonNull(customerId, "You canr create the contact if the customer is not present");
    }
}
