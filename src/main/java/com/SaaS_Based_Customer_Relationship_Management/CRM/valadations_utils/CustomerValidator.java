package com.SaaS_Based_Customer_Relationship_Management.CRM.valadations_utils;

import com.SaaS_Based_Customer_Relationship_Management.CRM.data_access_layer.CustomerRepoService;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateCustomerDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateCustomerDTO;
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
public class CustomerValidator {

    private final CustomerRepoService customerRepoService;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validateCreateRequest(CreateCustomerDTO dto) {
        Objects.requireNonNull(dto, "CreateCustomerDTO cannot be null");
        validateJsrViolations(dto);
    }

    public void validateUpdateRequest(Long customerId, UpdateCustomerDTO dto) {
        Objects.requireNonNull(dto, "UpdateCustomerDTO cannot be null");
        validateJsrViolations(dto);
        customerRepoService.findById(customerId); // ensures customer exists
    }

    private <T> void validateJsrViolations(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Validation failed: " + violations);
        }
    }

}
