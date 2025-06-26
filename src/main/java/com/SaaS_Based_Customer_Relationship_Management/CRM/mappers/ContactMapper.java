package com.SaaS_Based_Customer_Relationship_Management.CRM.mappers;


import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.ContactResponseDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateContactDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateContactDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactResponseDTO toDto(Contact contact);

    Contact toEntity(CreateContactDTO createDto);

    void updateEntity(@MappingTarget Contact contact, UpdateContactDTO updateDto);
}

