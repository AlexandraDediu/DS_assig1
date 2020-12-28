package ro.tuc.ds2020.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import ro.tuc.ds2020.dtos.CaregiverDto;

@Entity
@Table(name = "caregiver")
public class CaregiverEntity {

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

    @OneToOne
    @MapsId
    private UserEntity userr;

    @OneToMany(mappedBy = "caregiver",fetch = FetchType.LAZY)
    private List<PatientEntity> patientEntities;

    public CaregiverEntity() {
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

    public List<PatientEntity> getPatientEntities() {
        return patientEntities;
    }

    public void setPatientEntities(List<PatientEntity> patientEntities) {
        this.patientEntities = patientEntities;
    }

    public UserEntity getUser() {
        return userr;
    }

    public void setUser(UserEntity user) {
        this.userr = user;
    }

    public CaregiverDto convertToDto() {
        CaregiverDto caregiverDto = new CaregiverDto();
        caregiverDto.setId(this.getId());
        caregiverDto.setName(this.getName());
        caregiverDto.setAddress(this.getAddress());
        caregiverDto.setBirthDate(this.getBirthDate());
        caregiverDto.setGender(this.getGender());
//        caregiverDto.setPatientDtos(this.patientEntities
//                .stream()
//                .map(PatientEntity::convertToDto)
//                .collect(Collectors.toList()));
        return caregiverDto;
    }

    @Override
    public String toString() {
        return "CaregiverEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", patientEntities=" + patientEntities +
                '}';
    }
}
