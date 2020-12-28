package ro.tuc.ds2020.controllers.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.tuc.ds2020.dtos.UserDto;
import ro.tuc.ds2020.errors.BadRequestException;
import ro.tuc.ds2020.errors.ErrorConstants;
import ro.tuc.ds2020.errors.ResourceNotFoundException;
import ro.tuc.ds2020.services.interfaces.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity saveUser(@RequestBody UserDto userDto) {
        try {
            return new ResponseEntity<>(userService.save(userDto), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(ErrorConstants.ERR_INVALID_FIELDS, HttpStatus.CONFLICT);
        }
    }


    @GetMapping
    public ResponseEntity getAllUsers(@RequestParam(value = "name", defaultValue = "") String name) {

        if(name.isBlank()) {
            return ResponseEntity.ok(userService.getAll());
        }else{
            return ResponseEntity.ok(userService.getUserByUsername(name));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }
}
