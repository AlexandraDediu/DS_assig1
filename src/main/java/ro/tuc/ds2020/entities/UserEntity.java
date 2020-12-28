package ro.tuc.ds2020.entities;

import javax.persistence.*;
import ro.tuc.ds2020.dtos.UserDto;
import ro.tuc.ds2020.utils.Role;

@Entity
@Table(name = "userr")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

    @OneToOne(mappedBy = "userr", cascade = CascadeType.ALL)
    private DoctorEntity doctor;

    @OneToOne(mappedBy = "userr", cascade = CascadeType.ALL)
    private PatientEntity patient;

    @OneToOne(mappedBy = "userr", cascade = CascadeType.ALL)
    private CaregiverEntity caregiver;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public CaregiverEntity getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(CaregiverEntity caregiver) {
        this.caregiver = caregiver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UserDto convertToDto() {
        return new UserDto(id,username, password, role, active);
    }
}
