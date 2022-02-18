package com.itzikbarabie.moni.Services;

import com.itzikbarabie.moni.Entity.BasicResponseModel;
import com.itzikbarabie.moni.Model.User;
import com.itzikbarabie.moni.Exceptions.CustomException;
import com.itzikbarabie.moni.Model.UserDto;
import com.itzikbarabie.moni.Repository.UserRepository;
import com.itzikbarabie.moni.Utils.Definitions;
import com.itzikbarabie.moni.Utils.ObjectValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Definitions definitions;
    @Autowired
    private ObjectValidator objectValidator;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDto addUser(UserDto userDto) throws CustomException {
        User user = this.convertToEntity(userDto);
        objectValidator.isValidUserObject(user);
        Iterable<User> users = this.userRepository.findAll();
        for (User item : users) {
            if(user.getUserId().equals(item.getUserId())){
                throw new CustomException(definitions.USER_ID_IS_ALREADY_REGISTERED);
            }
            if(user.getUsername().equals(item.getUsername())){
                throw new CustomException(definitions.USERNAME_IS_ALREADY_REGISTERED);
            }
            if (user.getEmail().equals(item.getEmail())) {
                throw new CustomException(definitions.EMAIL_IS_ALREADY_REGISTERED);
            }
        }
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return this.convertToDtoRestricted(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) throws CustomException {
        objectValidator.isValidObjectId(userDto.getObjectId());
        if(userDto.getRoles() != null) {
            objectValidator.isValidRole(userDto.getRoles());
        }
        if (!userRepository.existsByObjectId(userDto.getObjectId())) {
            throw new CustomException(definitions.USER_IS_NOT_EXISTS);
        } else {
            User userToSave = userRepository.findUserByObjectId(userDto.getObjectId());
            userToSave.setObject(this.convertToEntityRestricted(userDto));
            userRepository.save(userToSave);
            return this.convertToDtoRestricted(userToSave);
        }
    }

    @Override
    public BasicResponseModel activeUserByObjectId(long objectId, boolean active) throws CustomException {
        this.objectValidator.isValidObjectId(objectId);
        if (!userRepository.existsByObjectId(objectId)) {
            throw new CustomException(definitions.USER_IS_NOT_EXISTS);
        } else {
            User userToUpdate = userRepository.findUserByObjectId(objectId);
            userToUpdate.setActive(active);
            userRepository.save(userToUpdate);
            String responseMsg = (active) ? definitions.USER_ACTIVATED : definitions.USER_DEACTIVATED;
            return new BasicResponseModel(responseMsg);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> this.convertToDtoRestricted(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findUserByObjectId(long objectId) throws CustomException {
        this.objectValidator.isValidObjectId(objectId);
        if (!userRepository.existsByObjectId(objectId)) {
            throw new CustomException(definitions.USER_IS_NOT_EXISTS);
        } else {
            return this.convertToDtoRestricted(userRepository.findUserByObjectId(objectId));
        }
    }

    @Override
    public UserDto findUserByUsername(String username) throws CustomException {
        this.objectValidator.isValidString(username);
        if (!userRepository.existsByUsername(username)) {
            throw new CustomException(definitions.USER_IS_NOT_EXISTS);
        } else {
            return this.convertToDtoRestricted(userRepository.findUserByUsername(username));
        }
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    private User convertToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    private UserDto convertToDtoRestricted(User user) {
        user.setPassword(null);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    private User convertToEntityRestricted(UserDto userDto) {
        userDto.setEmail(null);  //unable to edit this field!
        userDto.setUserId(null); //unable to edit this field!
        userDto.setPassword(null); //unable to edit this field!
        User user = modelMapper.map(userDto, User.class);
        return user;
    }
}
