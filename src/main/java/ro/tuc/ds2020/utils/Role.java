package ro.tuc.ds2020.utils;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    DOCTOR,
    CAREGIVER,
    PATIENT;

    public String getAuthority() {
        return name();
    }

}