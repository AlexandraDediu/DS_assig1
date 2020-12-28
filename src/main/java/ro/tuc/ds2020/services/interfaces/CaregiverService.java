package ro.tuc.ds2020.services.interfaces;

import java.util.List;
import ro.tuc.ds2020.dtos.CaregiverDto;

public interface CaregiverService {

    List<CaregiverDto> getAll();

    CaregiverDto save(CaregiverDto caregiverDto);

    CaregiverDto update(Long id, CaregiverDto caregiverDto);

    CaregiverDto getById(Long id);

    void deleteById(Long id);
}
