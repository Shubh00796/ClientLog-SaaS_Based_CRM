package com.SaaS_Based_Customer_Relationship_Management.CRM.reposiotries;

import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByTenantIdAndIsActiveTrue(Long tenantId);

    List<Customer> findByNameContainingIgnoreCaseAndTenantIdAndIsActiveTrue(String name, Long tenantId);

    Optional<Customer> findByEmailAndTenantIdAndIsActiveTrue(String email, Long tenantId);
}
