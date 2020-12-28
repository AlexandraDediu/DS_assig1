package ro.tuc.ds2020.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import ro.tuc.ds2020.dtos.PatientDto;

@Entity
@Table(name = "patient")
public class PatientEntity {

    @Id
    @Column(name = "user_id")
    private Long id;

    @Column
    private String name;


    @Column
    private Date birthDate;


    @Column
    private String gender;


    @Column
    private String address;

    @Column
    private String medicalRecord;

    @OneToOne
    @MapsId
    private UserEntity userr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caregiver_id", nullable = false)
    private CaregiverEntity caregiver;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<MedicationPlanEntity> medicationPlanEntities = new HashSet<>();

    public PatientEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public CaregiverEntity getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(CaregiverEntity caregiver) {
        this.caregiver = caregiver;
    }

    public Set<MedicationPlanEntity> getMedicationPlanEntities() {
        return medicationPlanEntities;
    }

    public void setMedicationPlanEntities(
            Set<MedicationPlanEntity> medicationPlanEntities) {
        this.medicationPlanEntities = medicationPlanEntities;
    }

    public UserEntity getUser() {
        return userr;
    }

    public void setUser(UserEntity user) {
        this.userr = user;
    }

    public PatientDto convertToDto() {
        PatientDto patientDto = new PatientDto();
        patientDto.setId(this.getId());
        patientDto.setName(this.getName());
        patientDto.setMedicalRecord(this.getMedicalRecord());
        patientDto.setGender(this.getGender());
        patientDto.setBirthDate(this.getBirthDate());
        patientDto.setCaregiverId(this.getCaregiver().getId());
        patientDto.setAddress(this.getAddress());
        return patientDto;
    }
}
