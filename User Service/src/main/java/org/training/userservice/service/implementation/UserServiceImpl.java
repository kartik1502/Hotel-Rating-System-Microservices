package org.training.userservice.service.implementation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.training.userservice.dto.Response;
import org.training.userservice.dto.UserDto;
import org.training.userservice.entity.User;
import org.training.userservice.exception.ResourceConflict;
import org.training.userservice.exception.ResourceNotFound;
import org.training.userservice.repository.UserRepository;
import org.training.userservice.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${spring.application.responseCode}")
    private String responseCode;

    @Override
    public Response addUser(@Valid UserDto userDto) {

        Optional<User> presentUser = userRepository.findUserByEmailIdOrContactNo(userDto.getEmailId(), userDto.getContactNo());
        if(presentUser.isPresent()){
            throw new ResourceConflict("User with email id or contact no already exists");
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setUserId(UUID.randomUUID().toString());
        userRepository.save(user);
        return new Response(responseCode, "User added successfully");
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<UserDto> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            users.add(userDto);
        });
        return users;
    }

    @Override
    public UserDto getUserById(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFound("User not found"));
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto, "userId");
        return userDto;
    }
}
