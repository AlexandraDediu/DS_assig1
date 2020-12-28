package ro.tuc.ds2020.entities;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ro.tuc.ds2020.dtos.MedicationDto;

@Entity
@Table(name = "medication")
public class MedicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String sideEffects;

    @Column
    private String dosage;

    @OneToMany(mappedBy = "medication",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicationPlanEntity> medicationPlanEntities = new HashSet<>();

    public MedicationEntity() {
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

    public Set<MedicationPlanEntity> getMedicationPlanEntities() {
        return medicationPlanEntities;
    }

    public void setMedicationPlanEntities(Set<MedicationPlanEntity> medicationPlanEntities) {
        this.medicationPlanEntities = medicationPlanEntities;
    }

    public MedicationDto convertToDto() {
        MedicationDto medicationDto = new MedicationDto();
        medicationDto.setId(this.getId());
        medicationDto.setName(this.getName());
        medicationDto.setDosage(this.getDosage());
        medicationDto.setSideEffects(this.getSideEffects());
        return medicationDto;
    }

    @Override
    public String toString() {
        return "MedicationEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sideEffects='" + sideEffects + '\'' +
                ", dosage=" + dosage +
                ", medicationPlanEntities=" + medicationPlanEntities +
                '}';
    }
}
