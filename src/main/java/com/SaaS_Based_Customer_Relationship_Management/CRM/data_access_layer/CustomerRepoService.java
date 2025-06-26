package com.SaaS_Based_Customer_Relationship_Management.CRM.data_access_layer;


import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Customer;
import com.SaaS_Based_Customer_Relationship_Management.CRM.exceptions.ResourceNotFoundException;
import com.SaaS_Based_Customer_Relationship_Management.CRM.reposiotries.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerRepoService {

    private final CustomerRepository customerRepository;

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
    }

    public List<Customer> findByTenantId(Long tenantId) {
        return customerRepository.findByTenantIdAndIsActiveTrue(tenantId);
    }

    public List<Customer> searchByName(String name, Long tenantId) {
        return customerRepository.findByNameContainingIgnoreCaseAndTenantIdAndIsActiveTrue(name, tenantId);
    }

    public Customer findByEmailAndTenantId(String email, Long tenantId) {
        return customerRepository.findByEmailAndTenantIdAndIsActiveTrue(email, tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with email: " + email));
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
