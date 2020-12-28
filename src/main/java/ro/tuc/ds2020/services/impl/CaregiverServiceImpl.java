package ro.tuc.ds2020.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tuc.ds2020.dtos.CaregiverDto;
import ro.tuc.ds2020.dtos.UserDto;
import ro.tuc.ds2020.entities.CaregiverEntity;
import ro.tuc.ds2020.entities.PatientEntity;
import ro.tuc.ds2020.errors.BadRequestException;
import ro.tuc.ds2020.errors.ErrorConstants;
import ro.tuc.ds2020.repositories.CaregiverRepository;
import ro.tuc.ds2020.repositories.PatientRepository;
import ro.tuc.ds2020.services.interfaces.CaregiverService;
import ro.tuc.ds2020.services.interfaces.UserService;
import ro.tuc.ds2020.utils.Role;

@Transactional
@Service("caregiverService")
public class CaregiverServiceImpl implements CaregiverService {

    private final CaregiverRepository caregiverRepository;
    private final PatientRepository patientRepository;
    private final UserService userService;

    public CaregiverServiceImpl(CaregiverRepository caregiverRepository,
                                PatientRepository patientRepository, UserService userService) {
        this.caregiverRepository = caregiverRepository;
        this.patientRepository = patientRepository;
        this.userService = userService;
    }


    @Override
    public List<CaregiverDto> getAll() {
        List<CaregiverDto> caregiverDtos = caregiverRepository.findAll()
                .stream()
                .map(CaregiverEntity::convertToDto)
                .collect(Collectors.toList());

        caregiverDtos.forEach(caregiverDto -> caregiverDto.setPatientDtos(
                patientRepository.findPatientEntitiesByCaregiverId(caregiverDto.getId())
                        .stream().map(PatientEntity::convertToDto).
                        collect(Collectors.toList())

        ));

        return caregiverDtos;

    }

    @Override
    public CaregiverDto save(CaregiverDto caregiverDto) {

        UserDto userDto = new UserDto();
        userDto.setUsername(caregiverDto.getName());
        userDto.setPassword("caregiver");
        userDto.setActive(true);
        userDto.setRole(Role.CAREGIVER);
        userDto = userService.save(userDto);


        CaregiverEntity caregiverEntity = caregiverDto.convertToEntity();
        caregiverEntity.setId(userDto.getId());
        caregiverEntity.setUser(userDto.convertToEntity());
        caregiverRepository.save(caregiverEntity);
        return caregiverEntity.convertToDto();
    }

    @Override
    public CaregiverDto update(Long id, CaregiverDto caregiverDto) {
        CaregiverEntity caregiverEntityToUpdate = caregiverRepository.findById(id)
                .orElseThrow(() -> {
                    throw new BadRequestException(ErrorConstants.ERR_INVALID_FIELDS);
                });

        caregiverEntityToUpdate.setBirthDate(caregiverDto.getBirthDate());
        caregiverEntityToUpdate.setGender(caregiverDto.getGender());
        caregiverEntityToUpdate.setName(caregiverDto.getName());
        caregiverEntityToUpdate.setAddress(caregiverDto.getAddress());
        caregiverRepository.save(caregiverEntityToUpdate);

        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setUsername(caregiverDto.getName());
        userDto.setPassword("caregiver");
        userDto.setActive(true);
        userDto.setRole(Role.CAREGIVER);
        userService.update(userDto);

        return caregiverEntityToUpdate.convertToDto();
    }

    @Override
    public CaregiverDto getById(Long id) {
        CaregiverDto caregiverDto = caregiverRepository.findById(id)
                .orElseThrow(() -> {
                    throw new BadRequestException(ErrorConstants.ERR_INVALID_FIELDS);
                })
                .convertToDto();

        caregiverDto.setPatientDtos(
                patientRepository.findPatientEntitiesByCaregiverId(caregiverDto.getId())
                        .stream().map(PatientEntity::convertToDto).
                        collect(Collectors.toList())
        );

        return caregiverDto;

    }

    @Override
    public void deleteById(Long id) {
        CaregiverEntity caregiverEntity = caregiverRepository.getById(id);
        caregiverRepository.delete(caregiverEntity);
    }


}
