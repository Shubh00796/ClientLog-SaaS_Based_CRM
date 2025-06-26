package com.SaaS_Based_Customer_Relationship_Management.CRM.valadations_utils;

import com.SaaS_Based_Customer_Relationship_Management.CRM.data_access_layer.CustomerRepoService;
import com.SaaS_Based_Customer_Relationship_Management.CRM.data_access_layer.SalesDataRepoService;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateSalesDataDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateSalesDataDTO;
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
public class SalesDataValidator {

    private final SalesDataRepoService salesDataRepoService;
    private final CustomerRepoService customerRepoService;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validateCreateRequest(CreateSalesDataDTO dto) {
        Objects.requireNonNull(dto, "CreateSalesDataDTO cannot be null");
        validateJsrViolations(dto);
        validateCustomerExists(dto.getCustomerId());
    }

    public void validateUpdateRequest(Long salesDataId, UpdateSalesDataDTO dto) {
        Objects.requireNonNull(dto, "UpdateSalesDataDTO cannot be null");
        validateJsrViolations(dto);
        salesDataRepoService.findById(salesDataId); // ensures sales data exists
    }

    private <T> void validateJsrViolations(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Validation failed: " + violations);
        }
    }

    private void validateCustomerExists(Long customerId) {
        customerRepoService.findById(customerId); // throws if not found
    }
}
