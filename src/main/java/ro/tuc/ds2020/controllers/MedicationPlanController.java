package ro.tuc.ds2020.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.tuc.ds2020.dtos.MedicationPlanDto;
import ro.tuc.ds2020.errors.BadRequestException;
import ro.tuc.ds2020.errors.ErrorConstants;
import ro.tuc.ds2020.errors.ResourceNotFoundException;
import ro.tuc.ds2020.services.interfaces.MedicationPlanService;

@RequestMapping("/api/medication-plans")
@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class MedicationPlanController  {

  private final MedicationPlanService medicationPlanService;

  public MedicationPlanController(
      MedicationPlanService medicationPlanService) {
    this.medicationPlanService = medicationPlanService;
  }

//  @PreAuthorize("hasAuthority('DOCTOR')")
//  @GetMapping("/{id}")
//  public ResponseEntity getById(@PathVariable Long id) {
//    try {
//      return ResponseEntity.ok(medicationPlanService.getAllByPatient(id));
//    } catch (ResourceNotFoundException ex) {
//      throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
//    }
//  }

  @PreAuthorize("hasAuthority('DOCTOR') or hasAuthority('PATIENT') or hasAuthority('CAREGIVER') ")
  @GetMapping("/{id}")
  public ResponseEntity getAllByPatient(@PathVariable Long id) {
    try {
      return new ResponseEntity<>(medicationPlanService.getAllByPatient(id), HttpStatus.OK);
    } catch (Exception e) {
      throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
  }

  @PreAuthorize("hasAuthority('DOCTOR')")
  @PostMapping
  public ResponseEntity add(@RequestBody MedicationPlanDto medicationPlanDto) {
    try {
      return new ResponseEntity<>(medicationPlanService.save(medicationPlanDto), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      throw new BadRequestException(ErrorConstants.ERR_INVALID_FIELDS, HttpStatus.CONFLICT);
    }
  }

  @PreAuthorize("hasAuthority('DOCTOR')")
  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable Long id, @RequestBody MedicationPlanDto medicationPlanDto) {
    try {
      MedicationPlanDto returnedMedicationPlanDto = medicationPlanService.update(id, medicationPlanDto);
      return new ResponseEntity<>(returnedMedicationPlanDto, HttpStatus.OK);
    } catch (IllegalArgumentException e) {

      throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
  }

  @PreAuthorize("hasAuthority('DOCTOR')")
  @DeleteMapping("/{id}")
  public ResponseEntity deleteById(@PathVariable Long id) {
    try {
      medicationPlanService.deleteById(id);
      return new ResponseEntity<>("The medication plan has been deleted!", HttpStatus.OK);
    } catch (Exception e) {
      throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
  }
}
