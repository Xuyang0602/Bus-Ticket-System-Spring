package com.example.demo.service;

import com.example.demo.dto.mapper.UserMapper;
import com.example.demo.dto.model.user.UserDto;
import com.example.demo.exception.BRSException;
import com.example.demo.exception.ExceptionType;
import com.example.demo.exception.EntityType;
import com.example.demo.model.user.Role;
import com.example.demo.model.user.User;
import com.example.demo.model.user.UserRoles;
import com.example.demo.repository.user.RoleRepository;
import com.example.demo.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static com.example.demo.exception.EntityType.USER;
import static com.example.demo.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.example.demo.exception.ExceptionType.ENTITY_NOT_FOUND;


@Component
public class UserServiceImplementation implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusReservationService busReservationService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto signUp(UserDto userDto) {
        Role userRole;
        User user = userRepository.findByEmail(userDto.getEmail());

        if (user == null) {
            if (userDto.isAdmin()) userRole = roleRepository.findByRole(UserRoles.ADMIN.name());
            else userRole = roleRepository.findByRole(UserRoles.PASSENGER.name());

            user = new User()
                    .setEmail(userDto.getEmail())
                    .setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()))
                    .setRoles(new HashSet<>(Arrays.asList(userRole)))
                    .setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setMobileNumber(userDto.getMobileNumber());
            return UserMapper.toUserDto(userRepository.save(user));
        }
        throw exception(USER, DUPLICATE_ENTITY, userDto.getEmail());
    }


    // Search an existing user
    @Override
    public UserDto findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) return modelMapper.map(user.get(), UserDto.class);
        throw exception(USER, ENTITY_NOT_FOUND, email);
    }


    // Update User Profile
    @Override
    public UserDto updateProfile(UserDto userDto) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setMobileNumber(userDto.getMobileNumber());
            return UserMapper.toUserDto(userRepository.save(userModel));
        }
        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    }

    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setPassword(bCryptPasswordEncoder.encode(newPassword));
            return UserMapper.toUserDto(userRepository.save(userModel));
        }
        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    }

    // Return a new RuntimeException
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return BRSException.throwException(entityType, exceptionType, args);
    }
}
