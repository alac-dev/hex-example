package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PackageDO {

  private final long id;

  private final long weight;

  private final long height;

  private final long lastUpdate;

}
