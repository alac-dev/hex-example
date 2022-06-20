package com.example.demo.infraestructure.db.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.example.demo.domain.model.PackageDO;
import com.example.demo.infraestructure.db.entities.PackageEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackageToPackageEntityMapperTest {

  private PackageDOToPackageEntityMapper mapper;

  @BeforeEach
  public void setUp() {
    this.mapper = new PackageDOToPackageEntityMapper();
  }

  @Test
  public void whenMapToEntityWorksProperly() {
    final PackageDO packageDO = PackageDO.builder().id(1L).height(900L).weight(400L).lastUpdate(12345678L).build();

    final PackageEntity packageEntity = this.mapper.toEntity(packageDO);

    assertThat(packageEntity.getId(), is(packageDO.getId()));
    assertThat(packageEntity.getHeight(), is(packageDO.getHeight()));
    assertThat(packageEntity.getWeight(), is(packageDO.getWeight()));
    assertThat(packageEntity.getLastUpdate(), is(packageDO.getLastUpdate()));
  }

  @Test
  public void whenMapToDOWorksProperly() {
    final PackageEntity packageEntity = new PackageEntity();
    packageEntity.setId(1L);
    packageEntity.setHeight(300L);
    packageEntity.setWeight(500L);
    packageEntity.setLastUpdate(12345678L);

    final PackageDO packageDO = this.mapper.toDO(packageEntity);

    assertThat(packageDO.getId(), is(packageEntity.getId()));
    assertThat(packageDO.getHeight(), is(packageEntity.getHeight()));
    assertThat(packageDO.getWeight(), is(packageEntity.getWeight()));
    assertThat(packageDO.getLastUpdate(), is(packageEntity.getLastUpdate()));
  }
}