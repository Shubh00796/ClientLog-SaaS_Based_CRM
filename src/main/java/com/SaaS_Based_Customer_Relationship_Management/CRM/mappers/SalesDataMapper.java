package com.SaaS_Based_Customer_Relationship_Management.CRM.mappers;


import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.CreateSalesDataDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.SalesDataResponseDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.dtos.UpdateSalesDataDTO;
import com.SaaS_Based_Customer_Relationship_Management.CRM.entities.SalesData;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SalesDataMapper {

    SalesDataResponseDTO toDto(SalesData salesData);

    SalesData toEntity(CreateSalesDataDTO createDto);

    void updateEntity(@MappingTarget SalesData salesData, UpdateSalesDataDTO updateDto);
}
