package com.example.demo.infraestructure.rest.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.example.demo.domain.model.PackageDO;
import com.example.demo.infraestructure.rest.dto.PackageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackageDOToPackageDTOMapperTest {

  private PackageDOToPackageDTOMapper mapper;

  @BeforeEach
  public void setUp() {
    this.mapper = new PackageDOToPackageDTOMapper();
  }

  @Test
  public void whenMapToDTOWorksProperly() {
    final PackageDO packageDO = PackageDO.builder().id(1L).height(900L).weight(400L).lastUpdate(12345678L).build();

    final PackageDTO packageDTO = this.mapper.toDTO(packageDO);

    assertThat(packageDTO.getId(), is(packageDO.getId()));
    assertThat(packageDTO.getHeight(), is(packageDO.getHeight()));
    assertThat(packageDTO.getWeight(), is(packageDO.getWeight()));
    assertThat(packageDTO.getLastUpdate(), is(packageDO.getLastUpdate()));
  }

  @Test
  public void whenMapToDOWorksProperly() {
    final PackageDTO packageDTO = PackageDTO.builder().id(1L).height(900L).weight(400L).lastUpdate(12345678L).build();

    final PackageDO packageDO = this.mapper.toDO(packageDTO);

    assertThat(packageDO.getId(), is(packageDTO.getId()));
    assertThat(packageDO.getHeight(), is(packageDTO.getHeight()));
    assertThat(packageDO.getWeight(), is(packageDTO.getWeight()));
    assertThat(packageDO.getLastUpdate(), is(packageDTO.getLastUpdate()));
  }
}