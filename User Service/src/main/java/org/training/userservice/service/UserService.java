package org.training.userservice.service;

import org.training.userservice.dto.Response;
import org.training.userservice.dto.UserDto;

import javax.validation.Valid;
import java.util.List;

public interface UserService {
    Response addUser(@Valid UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(String userId);
}
