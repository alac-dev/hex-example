package com.example.demo.infraestructure.db;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.domain.model.PackageDO;
import com.example.demo.domain.repository.PackageStorageRepository;
import com.example.demo.infraestructure.db.entities.PackageEntity;
import com.example.demo.infraestructure.db.h2.H2PackageRepository;
import com.example.demo.infraestructure.db.mapper.PackageDOToPackageEntityMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PackageStorage implements PackageStorageRepository {

  private final H2PackageRepository repository;

  private final PackageDOToPackageEntityMapper mapper;

  public PackageStorage(final H2PackageRepository repository, final PackageDOToPackageEntityMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public PackageDO savePackage(PackageDO newPackage) {
    PackageEntity newPackageEntity = this.mapper.toEntity(newPackage);
    newPackageEntity.setLastUpdate(Instant.now().toEpochMilli());
    PackageEntity savedEntity = this.repository.save(newPackageEntity);
    return this.mapper.toDO(savedEntity);
  }

  @Override
  public PackageDO findPackageById(long id) {
    Optional<PackageEntity> optionalPackageEntity = repository.findById(id);
    if (optionalPackageEntity.isEmpty()) {
      return null;
    }
    return this.mapper.toDO(optionalPackageEntity.get());
  }

  @Override
  public List<PackageDO> findAllPackages() {
    return repository.findAll().stream().map(this.mapper::toDO).collect(Collectors.toList());
  }

  @Override
  public PackageDO updatePackage(PackageDO updatePackage) {
    PackageEntity entityToUpdate = this.mapper.toEntity(updatePackage);

    Optional<PackageEntity> optionalPackageEntity = this.repository.findById(entityToUpdate.getId());
    if (optionalPackageEntity.isEmpty()) {
      throw new IllegalArgumentException("Package with Id: " + updatePackage.getId() + " not found");
    }
    entityToUpdate.setLastUpdate(Instant.now().toEpochMilli());
    PackageEntity updatedEntity = this.repository.save(entityToUpdate);
    return this.mapper.toDO(updatedEntity);
  }

  @Override
  public void deletePackage(long id) {
    this.repository.deleteById(id);
  }
}
