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
import ro.tuc.ds2020.dtos.PatientDto;
import ro.tuc.ds2020.errors.BadRequestException;
import ro.tuc.ds2020.errors.ErrorConstants;
import ro.tuc.ds2020.errors.ResourceNotFoundException;
import ro.tuc.ds2020.services.interfaces.PatientService;
import ro.tuc.ds2020.services.interfaces.UserService;

@RequestMapping("/api/patients")
@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class PatientController {

    private final PatientService patientService;
    private final UserService userService;

    public PatientController(PatientService patientService, UserService userService) {
        this.patientService = patientService;
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('DOCTOR') or hasAuthority('PATIENT')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(patientService.getById(id));
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping
    public ResponseEntity getAll() {
        try {
            return new ResponseEntity<>(patientService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND);
        }
    }



    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping
    public ResponseEntity add(@RequestBody PatientDto patientDto) {
        try {
            return new ResponseEntity<>(patientService.save(patientDto), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(ErrorConstants.ERR_INVALID_FIELDS, HttpStatus.CONFLICT);
        }
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PatientDto patientDto) {
        try {
            PatientDto returnedPatientDto = patientService.update(id, patientDto);
            return new ResponseEntity<>(returnedPatientDto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            patientService.deleteById(id);
            userService.delete(id);
            return new ResponseEntity<>("The patient has been deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND);
        }
    }


}
