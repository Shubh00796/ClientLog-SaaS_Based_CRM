package com.SaaS_Based_Customer_Relationship_Management.CRM.data_access_layer;


import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Contact;
import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Customer;
import com.SaaS_Based_Customer_Relationship_Management.CRM.exceptions.ResourceNotFoundException;
import com.SaaS_Based_Customer_Relationship_Management.CRM.reposiotries.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactRepoService {

    private final ContactRepository contactRepository;

    public Contact findById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with ID: " + id));
    }


    public List<Contact> searchByName(String name, Long tenantId) {
        return contactRepository.findByNameContainingIgnoreCaseAndTenantIdAndIsActiveTrue(name, tenantId);
    }

    public List<Contact> findByCustomerId(Long customerId, Long tenantId) {
        return contactRepository.findByCustomerIdAndTenantIdAndIsActiveTrue(customerId, tenantId);
    }

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact update(Contact contact) {
        return contactRepository.save(contact);
    }

    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public List<Contact> findByTenantId(Long id) {
        return contactRepository.findByTenantId(id);
    }

    public Page<Contact> findByTenantIdPageable(Long tenantId, Pageable pageable) {
        return contactRepository.findByTenantIdAndIsActiveTrue(tenantId, pageable);
    }

}
