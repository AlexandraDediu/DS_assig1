package ro.tuc.ds2020.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tuc.ds2020.dtos.MedicationPlanDto;
import ro.tuc.ds2020.dtos.PatientDto;
import ro.tuc.ds2020.entities.MedicationEntity;
import ro.tuc.ds2020.entities.MedicationPlanEntity;
import ro.tuc.ds2020.entities.PatientEntity;
import ro.tuc.ds2020.errors.BadRequestException;
import ro.tuc.ds2020.errors.ErrorConstants;
import ro.tuc.ds2020.errors.ResourceNotFoundException;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;
import ro.tuc.ds2020.repositories.MedicationRepository;
import ro.tuc.ds2020.repositories.PatientRepository;
import ro.tuc.ds2020.repositories.UserRepository;
import ro.tuc.ds2020.services.interfaces.MedicationPlanService;
import ro.tuc.ds2020.services.interfaces.PatientService;

@Transactional
@Service("medicationPlanService")
public class MedicationPlanServiceImpl implements MedicationPlanService {

    private final MedicationPlanRepository medicationPlanRepository;
    private final MedicationRepository medicationRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PatientService patientService;

    public MedicationPlanServiceImpl(MedicationPlanRepository medicationPlanRepository,
                                     MedicationRepository medicationRepository,
                                     PatientRepository patientRepository, UserRepository userRepository, PatientService patientService) {
        this.medicationPlanRepository = medicationPlanRepository;
        this.medicationRepository = medicationRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.patientService = patientService;
    }

    @Override
    public List<MedicationPlanDto> getAll() {
        return medicationPlanRepository.findAll()
                .stream()
                .map(MedicationPlanEntity::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicationPlanDto> getAllByPatient(Long id) {
        PatientDto patientDto = patientService.getById(id);
        PatientEntity patientEntity = patientDto.convertToEntity();

        return medicationPlanRepository.getAllByPatient(patientEntity)
                .stream()
                .map(MedicationPlanEntity::convertToDto)
                .collect(Collectors.toList());

    }

    @Override
    public MedicationPlanDto save(MedicationPlanDto medicationPlanDto) {
        MedicationPlanEntity medicationPlanEntity = new MedicationPlanEntity();
        Optional<MedicationEntity> medicationEntity = medicationRepository
                .findById(medicationPlanDto.getMedicationId());
        if (medicationEntity.isEmpty()) {
            throw new BadRequestException(ErrorConstants.ERR_ENTITY_NOT_FOUND);
        }
        Optional<PatientEntity> patientEntity = patientRepository
                .findById(medicationPlanDto.getPatientId());
        if (patientEntity.isEmpty()) {
            throw new BadRequestException(ErrorConstants.ERR_ENTITY_NOT_FOUND);
        }
        medicationPlanEntity.setMedicationName(medicationPlanDto.getMedicationName());
        medicationPlanEntity.setAdministrationDayPeriod(medicationPlanDto.getAdministrationDayPeriod());
        medicationPlanEntity.setPatient(patientEntity.get());
        medicationPlanEntity.setMedication(medicationEntity.get());
        medicationPlanEntity.setIntakeFrom(medicationPlanDto.getIntakeFrom());
        medicationPlanEntity.setIntakeTo(medicationPlanDto.getIntakeTo());
        medicationPlanEntity.setLowerLimitInterval(medicationPlanDto.getLowerLimitInterval());
        medicationPlanEntity.setUpperLimitInterval(medicationPlanDto.getUpperLimitInterval());
        return medicationPlanRepository.save(medicationPlanEntity).convertToDto();
    }

    @Override
    public MedicationPlanDto update(Long id, MedicationPlanDto medicationPlanDto) {
        MedicationPlanEntity medicationPlanEntityToUpdate = medicationPlanRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new BadRequestException(ErrorConstants.ERR_INVALID_FIELDS);
                });
        Optional<MedicationEntity> medicationEntity = medicationRepository
                .findById(medicationPlanDto.getMedicationId());
        if (medicationEntity.isEmpty()) {
            throw new BadRequestException(ErrorConstants.ERR_ENTITY_NOT_FOUND);
        }
        Optional<PatientEntity> patientEntity = patientRepository
                .findById(medicationPlanDto.getPatientId());
        if (patientEntity.isEmpty()) {
            throw new BadRequestException(ErrorConstants.ERR_ENTITY_NOT_FOUND);
        }

        medicationPlanEntityToUpdate.setMedicationName(medicationPlanDto.getMedicationName());
        medicationPlanEntityToUpdate
                .setAdministrationDayPeriod(medicationPlanDto.getAdministrationDayPeriod());
        medicationPlanEntityToUpdate.setIntakeFrom(medicationPlanDto.getIntakeFrom());
        medicationPlanEntityToUpdate.setIntakeTo(medicationPlanDto.getIntakeTo());
        medicationPlanEntityToUpdate.setMedication(medicationEntity.get());
        medicationPlanEntityToUpdate.setPatient(patientEntity.get());
        medicationPlanRepository.save(medicationPlanEntityToUpdate);
        return medicationPlanEntityToUpdate.convertToDto();

    }

    @Override
    public MedicationPlanDto getById(Long id) {
        Optional<MedicationPlanEntity> medicationPlanEntity = medicationPlanRepository.findById(id);
        if (medicationPlanEntity.isEmpty()) {
            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND);
        }
        return medicationPlanEntity.get().convertToDto();
    }

    @Override
    public void deleteById(Long id) {
        Optional<MedicationPlanEntity> medicationPlanEntity = medicationPlanRepository.findById(id);
        if (medicationPlanEntity.isEmpty()) {
            throw new ResourceNotFoundException(ErrorConstants.ERR_ENTITY_NOT_FOUND);
        } else {
            medicationPlanRepository.delete(medicationPlanEntity.get());
        }
    }
}
