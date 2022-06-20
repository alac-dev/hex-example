package com.example.demo.domain.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.example.demo.domain.model.PackageDO;
import com.example.demo.domain.repository.PackageStorageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PackageServiceTest {

  private PackageStorageRepository repository;

  private PackageService packageService;

  @BeforeEach
  public void setUp() {
    this.repository = Mockito.mock(PackageStorageRepository.class);
    this.packageService = new PackageService(repository);
  }

  @Test
  public void findByIdWorksProperly() {
    PackageDO packageDO = buildPackageDO();

    when(this.repository.findPackageById(packageDO.getId())).thenReturn(packageDO);

    PackageDO foundPackage = this.packageService.findPackageById(packageDO.getId());

    assertThat(foundPackage.getId(), is(packageDO.getId()));
    verify(this.repository, times(1)).findPackageById(packageDO.getId());
  }

  @Test
  public void findLastUpdatedPackageWorksProperly() {
    PackageDO first = buildPackageDO()
        .toBuilder().id(1L).lastUpdate(Instant.now().minus(1, ChronoUnit.DAYS).toEpochMilli()).build();
    PackageDO second = buildPackageDO()
        .toBuilder().id(2L).lastUpdate(Instant.now().toEpochMilli()).build();
    PackageDO third = buildPackageDO()
        .toBuilder().id(3L).lastUpdate(Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()).build();

    when(repository.findAllPackages()).thenReturn(List.of(second, third, first));

    PackageDO lastUpdatedPackage = this.packageService.getLastUpdatedPackage();

    assertThat(lastUpdatedPackage.getId(), is(third.getId()));
  }

  @Test
  public void savePackageWorksProperly() {
    PackageDO packageDO = buildPackageDO();

    when(this.repository.savePackage(packageDO)).thenReturn(packageDO);

    PackageDO savedPackage = this.packageService.savePackage(packageDO);

    assertThat(savedPackage.getId(), is(packageDO.getId()));
    verify(this.repository, times(1)).savePackage(packageDO);
  }

  @Test
  public void updatePackageWorksProperly() {
    PackageDO packageDO = buildPackageDO();

    when(this.repository.updatePackage(packageDO)).thenReturn(packageDO);

    PackageDO savedPackage = this.packageService.updatePackage(packageDO);

    assertThat(savedPackage.getId(), is(packageDO.getId()));
    verify(this.repository, times(1)).updatePackage(packageDO);
  }

  @Test
  public void deletePackageWorksProperly() {
    this.packageService.deletePackage(1234L);
    verify(this.repository, times(1)).deletePackage(1234L);
  }

  public PackageDO buildPackageDO() {
    return PackageDO.builder().id(1234L).height(300L).weight(500L).lastUpdate(12345678L).build();
  }
}