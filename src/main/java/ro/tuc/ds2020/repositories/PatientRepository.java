package ro.tuc.ds2020.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.PatientEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    PatientEntity getById(Long id);

    PatientEntity getByName(String name);

    List<PatientEntity> findPatientEntitiesByCaregiverId(Long id);

    PatientEntity findPatientEntityByCaregiverId( Long id);

    void delete(PatientEntity patientEntity);



}
