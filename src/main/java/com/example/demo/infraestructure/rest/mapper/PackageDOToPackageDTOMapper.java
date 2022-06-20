package com.example.demo.infraestructure.rest.mapper;

import com.example.demo.domain.model.PackageDO;
import com.example.demo.infraestructure.db.entities.PackageEntity;
import com.example.demo.infraestructure.rest.dto.PackageDTO;
import org.springframework.stereotype.Component;

@Component
public class PackageDOToPackageDTOMapper {

  public PackageDO toDO (final PackageDTO dto) {
    return PackageDO.builder()
        .id(dto.getId())
        .height(dto.getHeight())
        .weight(dto.getWeight())
        .lastUpdate(dto.getLastUpdate())
        .build();
  }

  public PackageDTO toDTO (final PackageDO packageDO) {
    return PackageDTO.builder()
        .id(packageDO.getId())
        .height(packageDO.getHeight())
        .weight(packageDO.getWeight())
        .lastUpdate(packageDO.getLastUpdate())
        .build();
  }

}
