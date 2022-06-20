package com.example.demo.infraestructure.db.h2;

import com.example.demo.infraestructure.db.entities.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2PackageRepository extends JpaRepository<PackageEntity, Long> {

}
