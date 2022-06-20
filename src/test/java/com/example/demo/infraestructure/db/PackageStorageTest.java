package com.example.demo.infraestructure.db;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.model.PackageDO;
import com.example.demo.infraestructure.db.entities.PackageEntity;
import com.example.demo.infraestructure.db.h2.H2PackageRepository;
import com.example.demo.infraestructure.db.mapper.PackageDOToPackageEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PackageStorageTest {

  private H2PackageRepository h2PackageRepository;

  private PackageStorage packageStorage;

  @BeforeEach
  public void setUp() {
    this.h2PackageRepository = Mockito.mock(H2PackageRepository.class);
    this.packageStorage = new PackageStorage(h2PackageRepository, new PackageDOToPackageEntityMapper());
  }

  @Test
  public void findOnRepositoryWorksProperly() {
    PackageEntity packageEntity = buildPackageEntity();
    PackageDO packageDO = buildPackageDO();
    when(this.h2PackageRepository.findById(packageDO.getId())).thenReturn(Optional.of(packageEntity));

    PackageDO packageFound = this.packageStorage.findPackageById(packageDO.getId());

    verify(this.h2PackageRepository, times(1)).findById(packageDO.getId());
    assertThat(packageFound, is(packageDO));
  }

  @Test
  public void findAllOnRepositoryWorksProperly() {
    PackageEntity packageEntity = buildPackageEntity();
    PackageDO packageDO = buildPackageDO();
    when(this.h2PackageRepository.findAll()).thenReturn(List.of(packageEntity));

    List<PackageDO> allPackages = this.packageStorage.findAllPackages();

    verify(this.h2PackageRepository, times(1)).findAll();
    assertThat(allPackages.get(0), is(packageDO));
  }

  @Test
  public void saveOnRepositoryWorksProperly() {
    PackageEntity packageEntity = buildPackageEntity();
    PackageDO packageDO = buildPackageDO();
    when(this.h2PackageRepository.save(packageEntity)).thenReturn(packageEntity);

    PackageDO savedPackageDO = this.packageStorage.savePackage(packageDO);

    verify(this.h2PackageRepository, times(1)).save(packageEntity);
    assertThat(savedPackageDO, is(packageDO));
  }

  @Test
  public void updateOnRepositoryWorksProperly() {
    PackageEntity packageEntity = buildPackageEntity();
    PackageDO packageDO = buildPackageDO();
    when(this.h2PackageRepository.findById(packageEntity.getId())).thenReturn(Optional.of(packageEntity));
    when(this.h2PackageRepository.save(packageEntity)).thenReturn(packageEntity);

    PackageDO savedPackageDO = this.packageStorage.updatePackage(packageDO);

    verify(this.h2PackageRepository, times(1)).findById(packageEntity.getId());
    verify(this.h2PackageRepository, times(1)).save(packageEntity);
    assertThat(savedPackageDO, is(packageDO));
  }

  @Test
  public void whenUpdatePackageNotFoundThenRaiseException() {
    PackageEntity packageEntity = buildPackageEntity();
    PackageDO packageDO = buildPackageDO();
    when(this.h2PackageRepository.findById(packageEntity.getId())).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> this.packageStorage.updatePackage(packageDO));

    verify(this.h2PackageRepository, times(1)).findById(packageEntity.getId());
    verify(this.h2PackageRepository, never()).save(packageEntity);
  }

  @Test
  public void deleteOnRepositoryWorksProperly() {
    PackageDO packageDO = buildPackageDO();

    this.packageStorage.deletePackage(packageDO.getId());

    verify(this.h2PackageRepository, times(1)).deleteById(packageDO.getId());
  }

  public PackageDO buildPackageDO() {
    return PackageDO.builder().id(12345L).height(300L).weight(500L).lastUpdate(12345678L).build();
  }

  public PackageEntity buildPackageEntity() {
    final PackageEntity packageEntity = new PackageEntity();
    packageEntity.setId(12345L);
    packageEntity.setHeight(300L);
    packageEntity.setWeight(500L);
    packageEntity.setLastUpdate(12345678L);

    return packageEntity;
  }
}