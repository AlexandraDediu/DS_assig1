package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.DoctorEntity;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
}
