package com.example.demo.domain.repository;

import java.util.List;

import com.example.demo.domain.model.PackageDO;

public interface PackageStorageRepository {

  PackageDO savePackage(PackageDO newPackage);

  PackageDO findPackageById(long id);

  List<PackageDO> findAllPackages();

  PackageDO updatePackage(PackageDO updatePackage);

  void deletePackage(long id);
}
