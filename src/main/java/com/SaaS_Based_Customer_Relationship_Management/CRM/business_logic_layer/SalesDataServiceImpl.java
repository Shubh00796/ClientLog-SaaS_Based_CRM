package com.SaaS_Based_Customer_Relationship_Management.CRM.business_logic_layer;

import com.SaaS_Based_Customer_Relationship_Management.CRM.data_access_layer.SalesDataRepoService;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateSalesDataDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.SalesDataResponseDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateSalesDataDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.SalesData;
import com.SaaS_Based_Customer_Relationship_Management.CRM.mappers.SalesDataMapper;
import com.SaaS_Based_Customer_Relationship_Management.CRM.service_provider_interface.SalesDataService;
import com.SaaS_Based_Customer_Relationship_Management.CRM.valadations_utils.SalesDataValidator;
import lombok.RequiredArgsConstructor;
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
public class SalesDataServiceImpl implements SalesDataService {
    private final SalesDataRepoService salesDataRepoService;
    private final SalesDataMapper mapper;
    private final SalesDataValidator salesDataValidator;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public SalesDataResponseDTO createSalesData(CreateSalesDataDTO createSalesDataDTO) {
        salesDataValidator.validateCreateRequest(createSalesDataDTO);
        SalesData salesData = mapToEntity(createSalesDataDTO);
        salesData.setCreatedAt(LocalDateTime.now());
        return mapToDto(saveSalesData(salesData));
    }

    @Override
    public SalesDataResponseDTO getSalesDataById(Long id) {
        return mapToDto(findSalesDataById(id));
    }

    @Override
    public Page<SalesDataResponseDTO> getSalesDataByTenantId(Long tenantId, Pageable pageable) {
        return salesDataRepoService.findByTenantId(tenantId, pageable)
                .map(this::mapToDto);
    }

    @Override
    public List<SalesDataResponseDTO> searchSalesDataByProductName(String productName, Long tenantId) {
        return mapToDtoList(salesDataRepoService.searchByProductName(productName, tenantId));
    }

    @Override
    public List<SalesDataResponseDTO> getSalesDataByCustomerId(Long customerId, Long tenantId) {
        return mapToDtoList(salesDataRepoService.findByCustomerId(customerId, tenantId));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public SalesDataResponseDTO updateSalesData(Long id, UpdateSalesDataDTO updateSalesDataDTO) {
        salesDataValidator.validateUpdateRequest(id, updateSalesDataDTO);
        SalesData salesData = findSalesDataById(id);
        mapUpdateEntity(salesData, updateSalesDataDTO);
        salesData.setUpdatedAt(LocalDateTime.now());
        return mapToDto(saveSalesData(salesData));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteSalesData(SalesData salesData) {
        salesDataRepoService.delete(salesData);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deactivateSalesData(Long id) {
        SalesData salesData = findSalesDataById(id);
        salesData.setIsActive(false);
        saveSalesData(salesData);
    }

    private SalesData findSalesDataById(Long id) {
        return salesDataRepoService.findById(id);
    }

    private SalesData saveSalesData(SalesData salesData) {
        return salesDataRepoService.save(salesData);
    }

    private SalesDataResponseDTO mapToDto(SalesData salesData) {
        return mapper.toDto(salesData);
    }

    private SalesData mapToEntity(CreateSalesDataDTO createSalesDataDTO) {
        return mapper.toEntity(createSalesDataDTO);
    }

    private void mapUpdateEntity(SalesData salesData, UpdateSalesDataDTO updateSalesDataDTO) {
        mapper.updateEntity(salesData, updateSalesDataDTO);
    }

    private List<SalesDataResponseDTO> mapToDtoList(List<SalesData> salesDataList) {
        return salesDataList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}