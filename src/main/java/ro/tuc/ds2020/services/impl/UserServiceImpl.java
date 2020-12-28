package ro.tuc.ds2020.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.UserDto;
import ro.tuc.ds2020.entities.UserEntity;
import ro.tuc.ds2020.errors.BadRequestException;
import ro.tuc.ds2020.errors.ErrorConstants;
import ro.tuc.ds2020.repositories.UserRepository;
import ro.tuc.ds2020.services.interfaces.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto save(UserDto userDto) {
        return userRepository.save(userDto.convertToEntity()).convertToDto();
    }

    @Override
    public void update(UserDto userDto) {
        UserEntity userEntityToUpdate = userRepository.findById(userDto.getId())
                .orElseThrow(() -> {
                    throw new BadRequestException(ErrorConstants.ERR_INVALID_FIELDS);
                });
        userEntityToUpdate.setUsername(userDto.getUsername());
        userRepository.save(userEntityToUpdate);

    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userRepository.getByUsername(username).convertToDto();
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.getUserById(id).convertToDto();
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> allUsers = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            allUsers.add(user.convertToDto());
        });
        return allUsers;
    }

    @Override
    public void delete(Long id) {
       UserEntity userEntity = userRepository.getUserById(id);
       userRepository.delete(userEntity);
    }
}
