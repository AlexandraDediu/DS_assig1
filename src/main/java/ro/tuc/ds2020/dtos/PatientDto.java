package ro.tuc.ds2020.dtos;

import java.util.Date;
import java.util.List;
import ro.tuc.ds2020.entities.PatientEntity;

public class PatientDto {

    private Long id;

    private String name;

    private Date birthDate;

    private String gender;

    private String address;

    private String medicalRecord;

    private Long caregiverId;

    private List<MedicationPlanDto> medicationPlanDtoList;

    public PatientDto() {
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

    public Long getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(Long caregiverId) {
        this.caregiverId = caregiverId;
    }

    public List<MedicationPlanDto> getMedicationPlanDtoList() {
        return medicationPlanDtoList;
    }

    public void setMedicationPlanDtoList(
        List<MedicationPlanDto> medicationPlanDtoList) {
        this.medicationPlanDtoList = medicationPlanDtoList;
    }

    public PatientEntity convertToEntity(){
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(this.getId());
        patientEntity.setName(this.getName());
        patientEntity.setAddress(this.getAddress());
        patientEntity.setBirthDate(this.getBirthDate());
        patientEntity.setGender(this.getGender());
        patientEntity.setMedicalRecord(this.getMedicalRecord());
        return patientEntity;
    }

}