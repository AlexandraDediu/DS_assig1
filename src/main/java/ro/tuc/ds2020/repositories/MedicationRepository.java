package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.MedicationEntity;

@Repository
public interface MedicationRepository extends JpaRepository<MedicationEntity, Long> {

  MedicationEntity getByName(String name);

  MedicationEntity getById(Long id);

  void delete (MedicationEntity medicationEntity);

}
