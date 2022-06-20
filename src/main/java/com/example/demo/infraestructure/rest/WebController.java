package com.example.demo.infraestructure.rest;

import java.util.Objects;

import com.example.demo.application.usecase.PackageUseCase;
import com.example.demo.domain.model.PackageDO;
import com.example.demo.infraestructure.rest.dto.PackageDTO;
import com.example.demo.infraestructure.rest.mapper.PackageDOToPackageDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/package")
public class WebController {

  private final PackageUseCase packageUseCase;

  private final PackageDOToPackageDTOMapper mapper;

  public WebController(PackageUseCase packageUseCase, PackageDOToPackageDTOMapper mapper) {
    this.packageUseCase = packageUseCase;
    this.mapper = mapper;
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<PackageDTO> getById(@PathVariable(value = "id") final long id) {
    PackageDO packageById = this.packageUseCase.findPackageById(id);

    if (Objects.isNull(packageById)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(this.mapper.toDTO(packageById), HttpStatus.OK);
  }

  @GetMapping("/last")
  @ResponseBody
  public ResponseEntity<PackageDTO> getLastUpdated() {
    PackageDO lastUpdatedPackage = this.packageUseCase.getLastUpdatedPackage();

    if (Objects.isNull(lastUpdatedPackage)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(this.mapper.toDTO(lastUpdatedPackage), HttpStatus.OK);
  }

  @PostMapping("/add")
  @ResponseBody
  public ResponseEntity<PackageDTO> savePackage(@RequestBody final PackageDTO packageDTO) {
    PackageDO packageDO = this.packageUseCase.savePackage(this.mapper.toDO(packageDTO));
    return new ResponseEntity<>(this.mapper.toDTO(packageDO), HttpStatus.OK);
  }

  @PostMapping("/update")
  @ResponseBody
  public ResponseEntity<PackageDTO> updatePackage(@RequestBody final PackageDTO packageDTO) {
    PackageDO packageDO = this.packageUseCase.updatePackage(this.mapper.toDO(packageDTO));
    return new ResponseEntity<>(this.mapper.toDTO(packageDO), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public void deletePackage(@PathVariable(value = "id") final long id) {
    this.packageUseCase.deletePackage(id);
  }

}
