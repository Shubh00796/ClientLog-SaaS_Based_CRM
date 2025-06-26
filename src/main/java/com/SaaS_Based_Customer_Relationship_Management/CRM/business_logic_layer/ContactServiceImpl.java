package com.SaaS_Based_Customer_Relationship_Management.CRM.business_logic_layer;

import com.SaaS_Based_Customer_Relationship_Management.CRM.data_access_layer.ContactRepoService;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.ContactResponseDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateContactDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateContactDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.Contact;
import com.SaaS_Based_Customer_Relationship_Management.CRM.mappers.ContactMapper;
import com.SaaS_Based_Customer_Relationship_Management.CRM.service_provider_interface.ContactService;
import com.SaaS_Based_Customer_Relationship_Management.CRM.valadations_utils.ContactValidator;
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

@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepoService contactRepoService;
    private final ContactMapper mapper;
    private final ContactValidator contactValidator;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ContactResponseDTO createContact(CreateContactDTO createContactDTO) {
        contactValidator.validateCreateRequest(createContactDTO);
        Contact contact = mapToEntity(createContactDTO);
        contact.setCreatedAt(LocalDateTime.now());
        return mapToDto(saveContact(contact));
    }

    @Override
    @Cacheable(value = "contacts", key = "#id")
    public ContactResponseDTO getContactById(Long id) {
        return mapToDto(findContactById(id));
    }

    @Override
    public Page<ContactResponseDTO> getContactsByTenantId(Long tenantId, Pageable pageable) {
        return contactRepoService.findByTenantIdPageable(tenantId, pageable)
                .map(this::mapToDto);
    }

    @Override
    public List<ContactResponseDTO> searchContactsByName(String name, Long tenantId) {
        return mapContactsToDtoList(contactRepoService.searchByName(name, tenantId));
    }

    @Override
    public List<ContactResponseDTO> getContactsByCustomerId(Long customerId, Long tenantId) {
        return mapContactsToDtoList(contactRepoService.findByCustomerId(customerId, tenantId));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ContactResponseDTO updateContact(Long id, UpdateContactDTO updateContactDTO) {
        contactValidator.validateUpdateRequest(id, updateContactDTO);
        Contact contact = findContactById(id);
        mapUpdateEntity(contact, updateContactDTO);
        return mapToDto(saveContact(contact));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteContact(Contact contact) {
        contactRepoService.delete(contact);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deactivateContact(Long id) {
        Contact contact = findContactById(id);
        contact.setIsActive(false);
        saveContact(contact);
    }
//  private helper methods

    private Contact findContactById(Long id) {
        return contactRepoService.findById(id);
    }

    private Contact saveContact(Contact contact) {
        return contactRepoService.save(contact);
    }

    private ContactResponseDTO mapToDto(Contact contact) {
        return mapper.toDto(contact);
    }

    private Contact mapToEntity(CreateContactDTO createContactDTO) {
        return mapper.toEntity(createContactDTO);
    }

    private void mapUpdateEntity(Contact contact, UpdateContactDTO updateContactDTO) {
        mapper.updateEntity(contact, updateContactDTO);
    }

    private List<ContactResponseDTO> mapContactsToDtoList(List<Contact> contacts) {
        return contacts.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}