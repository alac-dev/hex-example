package com.example.demo.application.usecase;

import com.example.demo.domain.model.PackageDO;
import com.example.demo.domain.service.PackageService;
import org.springframework.stereotype.Component;

@Component
public class PackageUseCase {

  private final PackageService service;

  public PackageUseCase(PackageService service) {
    this.service = service;
  }

  public PackageDO findPackageById(final long id) {
    return this.service.findPackageById(id);
  }

  public PackageDO getLastUpdatedPackage() {
    return this.service.getLastUpdatedPackage();
  }

  public PackageDO savePackage(final PackageDO packageDO) {
    return this.service.savePackage(packageDO);
  }

  public PackageDO updatePackage(final PackageDO packageDO) {
    return this.service.updatePackage(packageDO);
  }

  public void deletePackage(final long id) {
    this.service.deletePackage(id);
  }

}
