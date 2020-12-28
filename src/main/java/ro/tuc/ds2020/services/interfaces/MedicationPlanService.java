package ro.tuc.ds2020.services.interfaces;

import java.util.List;
import ro.tuc.ds2020.dtos.MedicationPlanDto;

public interface MedicationPlanService {
    List<MedicationPlanDto> getAll();

    List<MedicationPlanDto> getAllByPatient(Long id);

    MedicationPlanDto save(MedicationPlanDto medicationPlanDto);

    MedicationPlanDto update(Long id, MedicationPlanDto medicationPlanDto);

    MedicationPlanDto getById(Long id);

    void deleteById(Long id);
}
