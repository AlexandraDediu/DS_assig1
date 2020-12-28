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
import ro.tuc.ds2020.dtos.CaregiverDto;
import ro.tuc.ds2020.errors.BadRequestException;
import ro.tuc.ds2020.errors.ErrorConstants;
import ro.tuc.ds2020.errors.ResourceNotFoundException;
import ro.tuc.ds2020.repositories.PatientRepository;
import ro.tuc.ds2020.services.interfaces.CaregiverService;
import ro.tuc.ds2020.services.interfaces.PatientService;
import ro.tuc.ds2020.services.interfaces.UserService;

@RequestMapping("/api/caregivers")
@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class CaregiverController {

    private final CaregiverService caregiverService;
    private final UserService userService;
    private final PatientService patientService;
    private final PatientRepository patientRepository;

    public CaregiverController(CaregiverService caregiverService, UserService userService,
        PatientService patientService,
        PatientRepository patientRepository) {
        this.caregiverService = caregiverService;
        this.userService = userService;
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }

    @PreAuthorize("hasAuthority('DOCTOR')or hasAuthority('CAREGIVER')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(caregiverService.getById(id));
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping
    public ResponseEntity getAll() {
        try {
            return new ResponseEntity<>(caregiverService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping
    public ResponseEntity add(@RequestBody CaregiverDto caregiverDto) {
        try {
            return new ResponseEntity<>(caregiverService.save(caregiverDto), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(ErrorConstants.ERR_INVALID_FIELDS, HttpStatus.CONFLICT);
        }
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody CaregiverDto caregiverDto) {
        try {
            CaregiverDto returnedCaregiverDto = caregiverService.update(id, caregiverDto);
            return new ResponseEntity<>(returnedCaregiverDto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {

            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            caregiverService.deleteById(id);
            userService.delete(id);
            return new ResponseEntity<>("The caregiver has been deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }


}
