package com.SaaS_Based_Customer_Relationship_Management.CRM.service_provider_interface;


import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.ContactResponseDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateContactDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateContactDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactService {

    ContactResponseDTO createContact(CreateContactDTO createContactDTO);

    ContactResponseDTO getContactById(Long id);

    Page<ContactResponseDTO> getContactsByTenantId(Long tenantId, Pageable pageable);

    List<ContactResponseDTO> searchContactsByName(String name, Long tenantId);

    List<ContactResponseDTO> getContactsByCustomerId(Long customerId, Long tenantId);

    ContactResponseDTO updateContact(Long id, UpdateContactDTO updateContactDTO);

    void deleteContact(Contact contact);

    void deactivateContact(Long id);
}
