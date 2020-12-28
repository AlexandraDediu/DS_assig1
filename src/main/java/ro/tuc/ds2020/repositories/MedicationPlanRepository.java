package ro.tuc.ds2020.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.MedicationEntity;
import ro.tuc.ds2020.entities.MedicationPlanEntity;
import ro.tuc.ds2020.entities.PatientEntity;

@Repository
public interface MedicationPlanRepository extends JpaRepository<MedicationPlanEntity, Long> {

  MedicationPlanEntity getById(Long id);

  List<MedicationPlanEntity> getAllByPatient(PatientEntity patientEntity);

  List<MedicationPlanEntity> findMedicationPlanEntitiesByPatientId(Long id);

  void deleteMedicationPlanEntitiesByPatient(PatientEntity patientEntity);

}
