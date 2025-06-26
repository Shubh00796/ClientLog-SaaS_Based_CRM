package com.SaaS_Based_Customer_Relationship_Management.CRM.reposiotries;

import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Contact;
import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {


    boolean existsByEmailAndTenantId(String email, Long tenantId);


    Page<Contact> findByTenantIdAndIsActiveTrue(Long tenantId, Pageable pageable);

    List<Contact> findByTenantId(Long tenantId);


    List<Contact> findByNameContainingIgnoreCaseAndTenantIdAndIsActiveTrue(String name, Long tenantId);

    List<Contact> findByCustomerIdAndTenantIdAndIsActiveTrue(Long customerId, Long tenantId);
}
