package ro.tuc.ds2020.services.interfaces;

import java.util.List;
import ro.tuc.ds2020.dtos.PatientDto;

public interface PatientService {

    List<PatientDto> getAll();

    PatientDto save(PatientDto patientDto);

    PatientDto update(Long id, PatientDto patientDto);

    PatientDto getById(Long id);

    void deleteById(Long id);
}
