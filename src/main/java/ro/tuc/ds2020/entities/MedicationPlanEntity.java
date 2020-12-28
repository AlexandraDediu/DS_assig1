package ro.tuc.ds2020.entities;

import ro.tuc.ds2020.dtos.MedicationPlanDto;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "medication_plan")
public class MedicationPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String medicationName;

    @Column
    private Date intakeFrom;

    @Column
    private Date intakeTo;

    @Column
    private String administrationDayPeriod;


    @Column
    private Time lowerLimitInterval;


    @Column
    private Time upperLimitInterval;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "medication_id", nullable = false)
    private MedicationEntity medication;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    public MedicationPlanEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public Date getIntakeFrom() {
        return intakeFrom;
    }

    public void setIntakeFrom(Date intakeFrom) {
        this.intakeFrom = intakeFrom;
    }

    public Date getIntakeTo() {
        return intakeTo;
    }

    public void setIntakeTo(Date intakeTo) {
        this.intakeTo = intakeTo;
    }

    public MedicationEntity getMedication() {
        return medication;
    }

    public void setMedication(MedicationEntity medication) {
        this.medication = medication;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public String getAdministrationDayPeriod() {
        return administrationDayPeriod;
    }

    public void setAdministrationDayPeriod(String administrationMethod) {
        this.administrationDayPeriod = administrationMethod;
    }

    public Time getLowerLimitInterval() {
        return lowerLimitInterval;
    }

    public void setLowerLimitInterval(Time lowerLimitInterval) {
        this.lowerLimitInterval = lowerLimitInterval;
    }

    public Time getUpperLimitInterval() {
        return upperLimitInterval;
    }

    public void setUpperLimitInterval(Time upperLimitInterval) {
        this.upperLimitInterval = upperLimitInterval;
    }

    public MedicationPlanDto convertToDto() {
        MedicationPlanDto medicationPlanDto = new MedicationPlanDto();
        medicationPlanDto.setId(this.getId());
        medicationPlanDto.setAdministrationDayPeriod(this.getAdministrationDayPeriod());
        medicationPlanDto.setIntakeFrom(this.getIntakeFrom());
        medicationPlanDto.setIntakeTo(this.getIntakeTo());
        medicationPlanDto.setMedicationName(this.getMedicationName());
        medicationPlanDto.setMedicationId(this.getMedication().getId());
        medicationPlanDto.setPatientId(this.getPatient().getId());
        medicationPlanDto.setLowerLimitInterval(this.getLowerLimitInterval());
        medicationPlanDto.setUpperLimitInterval(this.getUpperLimitInterval());
        return medicationPlanDto;
    }
}