package com.SaaS_Based_Customer_Relationship_Management.CRM.business_logic_layer;

import com.SaaS_Based_Customer_Relationship_Management.CRM.data_access_layer.CustomerRepoService;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateCustomerDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CustomerResponseDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateCustomerDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Customer;
import com.SaaS_Based_Customer_Relationship_Management.CRM.mappers.CustomerMapper;
import com.SaaS_Based_Customer_Relationship_Management.CRM.service_provider_interface.CustomerService;
import com.SaaS_Based_Customer_Relationship_Management.CRM.valadations_utils.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepoService repoService;
    private final CustomerMapper mapper;
    private final CustomerValidator customerValidator;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public CustomerResponseDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        customerValidator.validateCreateRequest(createCustomerDTO);
        Customer customer = mapper.toEntity(createCustomerDTO);
        customer.setCreatedAt(LocalDateTime.now());
        return mapAndSave(customer);
    }

    @Override
    @Cacheable(value = "customers", key = "#id")
    public CustomerResponseDTO getCustomerById(Long id) {
        return map(repoService.findById(id));
    }

    @Override
    public Page<CustomerResponseDTO> getCustomersByTenantId(Long tenantId, Pageable pageable) {
        return repoService.findByTenantIdPagable(tenantId, pageable)
                .map(mapper::toDto);
    }

    @Override
    public List<CustomerResponseDTO> searchCustomersByName(String name, Long tenantId) {
        return repoService.searchByName(name, tenantId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public CustomerResponseDTO updateCustomer(Long id, UpdateCustomerDTO updateCustomerDTO) {
        customerValidator.validateUpdateRequest(id, updateCustomerDTO);
        Customer customer = repoService.findById(id);
        mapper.updateEntity(customer, updateCustomerDTO);
        customer.setUpdatedAt(LocalDateTime.now());
        return mapAndSave(customer);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteCustomer(Customer customer) {
        repoService.delete(customer);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deactivateCustomer(Long id) {
        Customer customer = repoService.findById(id);
        customer.setIsActive(false);
        customer.setUpdatedAt(LocalDateTime.now());
        repoService.update(customer);
    }

    @Override
    public boolean existsByEmailAndTenantId(String email, Long tenantId) {
        return repoService.existsByEmailAndTenant(tenantId, email);
    }

    // 🔽 Private helper methods

    private CustomerResponseDTO map(Customer customer) {
        return mapper.toDto(customer);
    }

    private CustomerResponseDTO mapAndSave(Customer customer) {
        return mapper.toDto(repoService.save(customer));
    }
}
