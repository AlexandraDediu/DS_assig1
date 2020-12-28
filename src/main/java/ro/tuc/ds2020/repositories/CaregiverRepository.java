package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.CaregiverEntity;

@Repository
public interface CaregiverRepository extends JpaRepository<CaregiverEntity, Long> {

    CaregiverEntity getById(Long name);

    CaregiverEntity getByName(String name);

    void delete (CaregiverEntity caregiverEntity);

}
