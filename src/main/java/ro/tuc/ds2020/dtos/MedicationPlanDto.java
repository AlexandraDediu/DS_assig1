package ro.tuc.ds2020.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.util.Date;

public class MedicationPlanDto {

    private Long id;

    private String medicationName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date intakeFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date intakeTo;

    private String administrationDayPeriod;

    private Long medicationId;

    private Long patientId;

    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Time lowerLimitInterval;

    // @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Time upperLimitInterval;

    public MedicationPlanDto() {
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

    public String getAdministrationDayPeriod() {
        return administrationDayPeriod;
    }

    public void setAdministrationDayPeriod(String administrationDayPeriod) {
        this.administrationDayPeriod = administrationDayPeriod;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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
}
