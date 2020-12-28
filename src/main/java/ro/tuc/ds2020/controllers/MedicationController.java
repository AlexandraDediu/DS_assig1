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
import ro.tuc.ds2020.dtos.MedicationDto;
import ro.tuc.ds2020.errors.BadRequestException;
import ro.tuc.ds2020.errors.ErrorConstants;
import ro.tuc.ds2020.errors.ResourceNotFoundException;
import ro.tuc.ds2020.services.interfaces.MedicationService;

@RequestMapping("/api/medications")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasAuthority('DOCTOR')")
public class MedicationController {

    private final MedicationService medicationService;


    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(medicationService.getById(id));
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return new ResponseEntity<>(medicationService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity add(@RequestBody MedicationDto medicationDto) {
        try {
            return new ResponseEntity<>(medicationService.save(medicationDto), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(ErrorConstants.ERR_INVALID_FIELDS, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody MedicationDto medicationDto) {
        try {
            MedicationDto returnedMedicationDto = medicationService.update(id, medicationDto);
            return new ResponseEntity<>(returnedMedicationDto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {

            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            medicationService.deleteById(id);
            return new ResponseEntity<>("The medication has been deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }


}
