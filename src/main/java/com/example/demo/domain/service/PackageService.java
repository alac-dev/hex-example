package com.example.demo.domain.service;

import com.example.demo.domain.model.PackageDO;
import com.example.demo.domain.repository.PackageStorageRepository;
import org.springframework.stereotype.Service;

@Service
public class PackageService {

  private final PackageStorageRepository repository;

  public PackageService(PackageStorageRepository repository) {
    this.repository = repository;
  }

  public PackageDO findPackageById(final long id) {
    return this.repository.findPackageById(id);
  }

  public PackageDO getLastUpdatedPackage() {
    return this.repository.findAllPackages().stream()
        .reduce((first, second) -> first.getLastUpdate() > second.getLastUpdate() ? first : second).orElse(null);
  }

  public PackageDO savePackage(final PackageDO packageDO) {
    return this.repository.savePackage(packageDO);
  }

  public PackageDO updatePackage(final PackageDO packageDO) {
    return this.repository.updatePackage(packageDO);
  }

  public void deletePackage(final long id) {
    this.repository.deletePackage(id);
  }

}
