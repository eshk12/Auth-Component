package com.itzikbarabie.moni.Services;

import com.itzikbarabie.moni.Model.User;
import com.itzikbarabie.moni.Exceptions.CustomException;
import com.itzikbarabie.moni.Repository.UserRepository;
import com.itzikbarabie.moni.Utils.ErrorMessages;
import com.itzikbarabie.moni.Utils.PasswordManager;
import com.itzikbarabie.moni.Utils.Validator;
import com.itzikbarabie.moni.Utils.objectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ErrorMessages errorMessages;
    @Autowired
    private PasswordManager passwordManager;
    @Autowired
    private objectValidator objectValidator;

    @Override
    public User addUser(User user) throws CustomException {
        objectValidator.isValidUserObject(user);
        Iterable<User> users = this.userRepository.findAll();
        for (User item : users) {
            if (user.getEmail().equals(item.getEmail())) {
                throw new CustomException(errorMessages.EMAIL_IS_ALREADY_REGISTERED);
            }
        }
        user.setPassword(passwordManager.hashPassword(user.getPassword()));
        userRepository.save(user);
        return userRepository.findUserByEmailAndAndPassword(user.getEmail(), user.getPassword());
    }

    @Override
    public User updateUser(User user) throws CustomException {
        if(!userRepository.existsByUserId(user.getUserId())){
            throw new CustomException(errorMessages.USER_IS_NOT_EXISTS);
        }else{
            User userToSave = userRepository.findUserByUserId(user.getUserId());
            userToSave.setObject(user);
            userRepository.save(userToSave);
        }
        return userRepository.findUserByUserId(user.getUserId());
    }

    @Override
    public void deleteUser(long userId) throws CustomException {

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long userId) throws CustomException {
        return null;
    }

    @Override
    public User findUserByEmail(String email) throws CustomException {
        return null;
    }
}
