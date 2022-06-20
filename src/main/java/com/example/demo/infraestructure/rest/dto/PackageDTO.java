package com.example.demo.infraestructure.rest.dto;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PackageDTO {

  private final long id;

  private final long weight;

  private final long height;

  @Default
  private final long lastUpdate = 0L;

}
