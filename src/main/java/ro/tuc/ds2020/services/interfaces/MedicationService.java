package ro.tuc.ds2020.services.interfaces;

import java.util.List;
import ro.tuc.ds2020.dtos.MedicationDto;

public interface MedicationService {

    List<MedicationDto> getAll();

    MedicationDto save(MedicationDto medicationDto);

    MedicationDto update(Long id, MedicationDto medicationDto);

    MedicationDto getById(Long id);

    void deleteById(Long id);
}
