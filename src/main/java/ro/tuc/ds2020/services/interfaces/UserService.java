package ro.tuc.ds2020.services.interfaces;

import java.util.List;
import ro.tuc.ds2020.dtos.UserDto;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto getUserByUsername(String username);

    UserDto getUserById(Long id);

    void update(UserDto userDto);

    List<UserDto> getAll();

    void delete(Long id);


}
