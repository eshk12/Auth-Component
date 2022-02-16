package com.itzikbarabie.moni.Services;

import com.itzikbarabie.moni.Entity.BasicResponseModel;
import com.itzikbarabie.moni.Model.User;
import com.itzikbarabie.moni.Exceptions.CustomException;
import com.itzikbarabie.moni.Model.UserDto;
import com.itzikbarabie.moni.Repository.UserRepository;
import com.itzikbarabie.moni.Utils.Definitions;
import com.itzikbarabie.moni.Utils.PasswordManager;
import com.itzikbarabie.moni.Utils.ObjectValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PasswordManager passwordManager;
    @Autowired
    private ObjectValidator objectValidator;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto addUser(UserDto userDto) throws CustomException {
        User user = this.convertToEntity(userDto);
        objectValidator.isValidUserObject(user);
        Iterable<User> users = this.userRepository.findAll();
        for (User item : users) {
            if(user.getUserId().equals(item.getUserId())){
                throw new CustomException(definitions.USER_ID_IS_ALREADY_REGISTERED);
            }
            if (user.getEmail().equals(item.getEmail())) {
                throw new CustomException(definitions.EMAIL_IS_ALREADY_REGISTERED);
            }
        }
        user.setPassword(passwordManager.hashPassword(user.getPassword()));
        userRepository.save(user);
        return this.convertToDtoRestricted(userRepository.findUserByEmailAndAndPassword(user.getEmail(), user.getPassword()));
    }

    @Override
    public UserDto updateUser(UserDto userDto) throws CustomException {
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
    public BasicResponseModel deleteUserByObjectId(long objectId, boolean deleted) throws CustomException {
        if (!userRepository.existsByObjectId(objectId)) {
            throw new CustomException(definitions.USER_IS_NOT_EXISTS);
        } else {
            User userToUpdate = userRepository.findUserByObjectId(objectId);
            userToUpdate.setDeleted(deleted);
            userRepository.save(userToUpdate);
            String responseMsg = (deleted) ? definitions.USER_DELETED : definitions.USER_RESTORED;
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
        if (!userRepository.existsByObjectId(objectId)) {
            throw new CustomException(definitions.USER_IS_NOT_EXISTS);
        } else {
            return this.convertToDtoRestricted(userRepository.findUserByObjectId(objectId));
        }
    }

    @Override
    public UserDto findUserByEmail(String email) throws CustomException {
        if (!userRepository.existsByEmail(email)) {
            throw new CustomException(definitions.USER_IS_NOT_EXISTS);
        } else {
            return this.convertToDtoRestricted(userRepository.findUserByEmail(email));
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
