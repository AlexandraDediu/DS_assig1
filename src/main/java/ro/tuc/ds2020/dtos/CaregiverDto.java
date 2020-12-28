package ro.tuc.ds2020.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import ro.tuc.ds2020.entities.CaregiverEntity;

public class CaregiverDto {

    private Long id;

    private String name;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;

    private String gender;

    private String address;

    List<PatientDto> patientDtos;

    public CaregiverDto() {
    }

    public CaregiverDto(Long id, String name, Date birthDate, String gender, String address) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
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

    public List<PatientDto> getPatientDtos() {
        return patientDtos;
    }

    public void setPatientDtos(List<PatientDto> patientDtos) {
        this.patientDtos = patientDtos;
    }

    public CaregiverEntity convertToEntity(){
        CaregiverEntity caregiverEntity = new CaregiverEntity();
        caregiverEntity.setId(this.id);
        caregiverEntity.setAddress(this.address);
        caregiverEntity.setName(this.name);
        caregiverEntity.setGender(this.gender);
        caregiverEntity.setBirthDate(this.birthDate);
        return caregiverEntity;
    }





}
