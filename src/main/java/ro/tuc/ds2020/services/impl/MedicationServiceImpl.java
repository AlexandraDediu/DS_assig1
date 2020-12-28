package ro.tuc.ds2020.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tuc.ds2020.dtos.MedicationDto;
import ro.tuc.ds2020.entities.MedicationEntity;
import ro.tuc.ds2020.errors.BadRequestException;
import ro.tuc.ds2020.errors.ErrorConstants;
import ro.tuc.ds2020.errors.ResourceNotFoundException;
import ro.tuc.ds2020.repositories.MedicationRepository;
import ro.tuc.ds2020.services.interfaces.MedicationService;

@Transactional
@Service("medicationService")
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;

    public MedicationServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<MedicationDto> getAll() {

        return medicationRepository.findAll()
                .stream()
                .map(MedicationEntity::convertToDto)
                .collect(Collectors.toList());

    }

    @Override
    public MedicationDto save(MedicationDto medicationDto) {
        return medicationRepository.save(medicationDto.convertToEntity()).convertToDto();
    }

    @Override
    public MedicationDto update(Long id, MedicationDto medicationDto) {
        MedicationEntity medicationEntityToUpdate = medicationRepository.findById(id)
                .orElseThrow(() -> {
                    throw new BadRequestException(ErrorConstants.ERR_INVALID_FIELDS);
                });
        medicationEntityToUpdate.setName(medicationDto.getName());
        medicationEntityToUpdate.setDosage(medicationDto.getDosage());
        medicationEntityToUpdate.setSideEffects(medicationDto.getSideEffects());
        medicationRepository.save(medicationEntityToUpdate);
        return medicationEntityToUpdate.convertToDto();
    }

    @Override
    public MedicationDto getById(Long id) {
        Optional<MedicationEntity> medicationEntity = medicationRepository.findById(id);
        if (medicationEntity.isEmpty()) {
            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND);
        }
        return medicationEntity.get().convertToDto();
    }


//    public void deleteById(Long id) {
//        MedicationEntity medicationEntity = medicationRepository.getById(id);
////        if (medicationEntity.isEmpty()) {
////            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND);
////        }
//        if (medicationEntity != null) {
//            medicationRepository.delete(medicationEntity);
//        } else {
//            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND);
//        }
//    }
     @Override
    public void deleteById(Long id){
        MedicationEntity medicationEntity = medicationRepository.getById(id);
        medicationRepository.delete(medicationEntity);
    }


}
