package com.SaaS_Based_Customer_Relationship_Management.CRM.reposiotries;

import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByTenantIdAndIsActiveTrue(Long tenantId);

    List<Contact> findByNameContainingIgnoreCaseAndTenantIdAndIsActiveTrue(String name, Long tenantId);

    List<Contact> findByCustomerIdAndTenantIdAndIsActiveTrue(Long customerId, Long tenantId);
}
