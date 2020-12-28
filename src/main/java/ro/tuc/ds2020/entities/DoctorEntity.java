package ro.tuc.ds2020.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @MapsId
    private UserEntity userr;

    public DoctorEntity() {
    }


    public DoctorEntity(Long id, UserEntity user) {
        this.id = id;
        this.userr = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return userr;
    }

    public void setUser(UserEntity user) {
        this.userr = user;
    }
}
