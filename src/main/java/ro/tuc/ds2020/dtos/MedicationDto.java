package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.MedicationEntity;

public class MedicationDto {

    private Long id;

    private String name;

    private String sideEffects;

    private String dosage;

    public MedicationDto(Long id, String name, String sideEffects, String dosage) {
        this.id = id;
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
    }

    public MedicationDto() {
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

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public MedicationEntity convertToEntity(){
        MedicationEntity medicationEntity = new MedicationEntity();
        medicationEntity.setId(this.getId());
        medicationEntity.setName(this.getName());
        medicationEntity.setDosage(this.getDosage());
        medicationEntity.setSideEffects(this.getSideEffects());
        return medicationEntity;
    }



}