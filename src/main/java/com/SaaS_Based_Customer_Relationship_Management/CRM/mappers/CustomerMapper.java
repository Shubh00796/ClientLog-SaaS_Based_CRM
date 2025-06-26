package com.SaaS_Based_Customer_Relationship_Management.CRM.mappers;


import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateCustomerDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CustomerResponseDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateCustomerDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDTO toDto(Customer customer);

    Customer toEntity(CreateCustomerDTO createDto);

    void updateEntity(@MappingTarget Customer customer, UpdateCustomerDTO updateDto);
}
