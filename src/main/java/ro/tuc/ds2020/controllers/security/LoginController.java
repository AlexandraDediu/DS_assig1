package ro.tuc.ds2020.controllers.security;

import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.tuc.ds2020.jwt.JwtUtil;
import ro.tuc.ds2020.login.LoginRequest;
import ro.tuc.ds2020.login.LoginResponse;
import ro.tuc.ds2020.services.security.CustomUserDetailsService;
import ro.tuc.ds2020.utils.Role;

@RestController
@RequestMapping("/user/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    public LoginController(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());

        String jwt = jwtUtil.generateToken(userDetails);

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Role role = Role.valueOf(authorities.iterator().next().toString());
        LoginResponse response = new LoginResponse();
        response.setJwt(jwt);
        response.setRole(role);
        response.setUsername(userDetails.getUsername());
        response.setExpirationDate(jwtUtil.getExpirationDate());
        return ResponseEntity.ok(response);
    }
}