package com.SaaS_Based_Customer_Relationship_Management.CRM.controllers;


import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.ApiResponse;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.ContactResponseDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateContactDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateContactDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Contact;
import com.SaaS_Based_Customer_Relationship_Management.CRM.service_provider_interface.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ApiResponse<ContactResponseDTO>> createContact(@RequestBody CreateContactDTO dto) {
        ContactResponseDTO created = contactService.createContact(dto);
        return ResponseEntity.ok(new ApiResponse<>(created, "Contact created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContactResponseDTO>> getContactById(@PathVariable Long id) {
        ContactResponseDTO contact = contactService.getContactById(id);
        return ResponseEntity.ok(new ApiResponse<>(contact, "Contact fetched successfully"));
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<ApiResponse<Page<ContactResponseDTO>>> getContactsByTenantId(
            @PathVariable Long tenantId,
            Pageable pageable) {
        Page<ContactResponseDTO> contacts = contactService.getContactsByTenantId(tenantId, pageable);
        return ResponseEntity.ok(new ApiResponse<>(contacts, "Contacts fetched successfully"));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ContactResponseDTO>>> searchContactsByName(
            @RequestParam String name,
            @RequestParam Long tenantId) {
        List<ContactResponseDTO> results = contactService.searchContactsByName(name, tenantId);
        return ResponseEntity.ok(new ApiResponse<>(results, "Search completed successfully"));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<ContactResponseDTO>>> getContactsByCustomerId(
            @PathVariable Long customerId,
            @RequestParam Long tenantId) {
        List<ContactResponseDTO> contacts = contactService.getContactsByCustomerId(customerId, tenantId);
        return ResponseEntity.ok(new ApiResponse<>(contacts, "Contacts fetched successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ContactResponseDTO>> updateContact(
            @PathVariable Long id,
            @RequestBody UpdateContactDTO dto) {
        ContactResponseDTO updated = contactService.updateContact(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(updated, "Contact updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteContact(Contact contact) {
        contactService.deleteContact(contact);
        return ResponseEntity.ok(new ApiResponse<>("Contact deleted successfully", "Success"));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<String>> deactivateContact(@PathVariable Long id) {
        contactService.deactivateContact(id);
        return ResponseEntity.ok(new ApiResponse<>("Contact deactivated successfully", "Success"));
    }
}
