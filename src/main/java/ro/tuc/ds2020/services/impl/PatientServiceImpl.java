package ro.tuc.ds2020.services.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tuc.ds2020.dtos.PatientDto;
import ro.tuc.ds2020.dtos.UserDto;
import ro.tuc.ds2020.entities.CaregiverEntity;
import ro.tuc.ds2020.entities.MedicationPlanEntity;
import ro.tuc.ds2020.entities.PatientEntity;
import ro.tuc.ds2020.errors.BadRequestException;
import ro.tuc.ds2020.errors.ErrorConstants;
import ro.tuc.ds2020.repositories.CaregiverRepository;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;
import ro.tuc.ds2020.repositories.PatientRepository;
import ro.tuc.ds2020.services.interfaces.PatientService;
import ro.tuc.ds2020.services.interfaces.UserService;
import ro.tuc.ds2020.utils.Role;

@Transactional
@Service("patientService")
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final CaregiverRepository caregiverRepository;
    private final MedicationPlanRepository medicationPlanRepository;
    private final UserService userService;

    public PatientServiceImpl(PatientRepository patientRepository,
        CaregiverRepository caregiverRepository,
        MedicationPlanRepository medicationPlanRepository,
        UserService userService) {
        this.patientRepository = patientRepository;
        this.caregiverRepository = caregiverRepository;
        this.medicationPlanRepository = medicationPlanRepository;
        this.userService = userService;
    }

    @Override
    public List<PatientDto> getAll() {
        return patientRepository.findAll()
                .stream()
                .map(PatientEntity::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto save(PatientDto patientDto) {

        UserDto userDto = new UserDto();
        userDto.setUsername(patientDto.getName());
        userDto.setPassword("patient");
        userDto.setActive(true);
        userDto.setRole(Role.PATIENT);
        userDto = userService.save(userDto);

        PatientEntity patientEntity = patientDto.convertToEntity();
        Optional<CaregiverEntity> caregiverEntity = caregiverRepository
                .findById(patientDto.getCaregiverId());
        if (caregiverEntity.isEmpty()) {
            throw new BadRequestException(ErrorConstants.ERR_ENTITY_NOT_FOUND);
        }
        patientEntity.setCaregiver(caregiverEntity.get());
        patientEntity.setId(userDto.getId());
        patientEntity.setUser(userDto.convertToEntity());

        patientRepository.save(patientEntity);
        return patientEntity.convertToDto();
    }

    @Override
    public PatientDto update(Long id, PatientDto patientDto) {
        PatientEntity patientEntityToUpdate = patientRepository.findById(id)
                .orElseThrow(() -> {
                    throw new BadRequestException(ErrorConstants.ERR_INVALID_FIELDS);
                });
        Optional<CaregiverEntity> caregiverEntity = caregiverRepository
                .findById(patientDto.getCaregiverId());
        if (caregiverEntity.isEmpty()) {
            throw new BadRequestException(ErrorConstants.ERR_ENTITY_NOT_FOUND);
        }

        patientEntityToUpdate.setMedicalRecord(patientDto.getMedicalRecord());
        patientEntityToUpdate.setName(patientDto.getName());
        patientEntityToUpdate.setMedicalRecord(patientDto.getMedicalRecord());
        patientEntityToUpdate.setGender(patientDto.getGender());
        patientEntityToUpdate.setBirthDate(patientDto.getBirthDate());
        patientEntityToUpdate.setAddress(patientDto.getAddress());
        patientEntityToUpdate.setCaregiver(caregiverEntity.get());
        patientRepository.save(patientEntityToUpdate);

        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setUsername(patientDto.getName());
        userDto.setPassword("patient");
        userDto.setActive(true);
        userDto.setRole(Role.PATIENT);
        userService.update(userDto);

        return patientEntityToUpdate.convertToDto();
    }

    @Override
    public PatientDto getById(Long id) {
        PatientDto patientDto = patientRepository.findById(id)
            .orElseThrow(() -> {
                throw new BadRequestException(ErrorConstants.ERR_INVALID_FIELDS);
            })
            .convertToDto();

        patientDto.setMedicationPlanDtoList(
            medicationPlanRepository.findMedicationPlanEntitiesByPatientId(patientDto.getId())
                    .stream().map(MedicationPlanEntity::convertToDto)
                    .collect(Collectors.toList())
        );

        return patientDto;

    }


    @Override
    public void deleteById(Long id) {
        PatientEntity patientEntity = patientRepository.getById(id);
        medicationPlanRepository.deleteMedicationPlanEntitiesByPatient(patientEntity);
        patientRepository.delete(patientEntity);
        System.out.println(patientEntity);
    }
}
