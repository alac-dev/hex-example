package com.example.demo.infraestructure.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "packages")
@Data
public class PackageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "weight")
  private Long weight;

  @Column(name = "height")
  private Long height;

  @Column(name = "lastUpdate")
  private Long lastUpdate;
}
