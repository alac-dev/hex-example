package com.example.demo.infraestructure.db.mapper;

import com.example.demo.domain.model.PackageDO;
import com.example.demo.infraestructure.db.entities.PackageEntity;
import org.springframework.stereotype.Component;

@Component
public class PackageDOToPackageEntityMapper {

  public PackageDO toDO (final PackageEntity entity) {
    return PackageDO.builder()
        .id(entity.getId())
        .height(entity.getHeight())
        .weight(entity.getWeight())
        .lastUpdate(entity.getLastUpdate())
        .build();
  }

  public PackageEntity toEntity (final PackageDO entity) {
    PackageEntity packageEntity = new PackageEntity();
    packageEntity.setId(entity.getId());
    packageEntity.setHeight(entity.getHeight());
    packageEntity.setWeight(entity.getWeight());
    packageEntity.setLastUpdate(entity.getLastUpdate());

    return packageEntity;
  }

}
