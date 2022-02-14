package com.itzikbarabie.moni.Services;

import com.itzikbarabie.moni.Model.User;
import com.itzikbarabie.moni.Exceptions.CustomException;
import com.itzikbarabie.moni.Repository.UserRepository;
import com.itzikbarabie.moni.Utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ErrorMessages errorMessages;

    public void isValidObject(User user) throws CustomException {
        if(user.getEmail() != null && user.getPassword() != null) {
            if(user.getEmail().isEmpty() || user.getPassword().isEmpty())
                throw new CustomException(errorMessages.MISSING_FIELDS);
        }else{
            throw new CustomException(errorMessages.MISSING_FIELDS);
        }
    }

    @Override
    public User addUser(User user) throws CustomException {
        this.isValidObject(user);
        Iterable<User> users = this.userRepository.findAll();
        for (User item : users) {
            if (user.getEmail().equals(item.getEmail())) {
                throw new CustomException(errorMessages.EMAIL_IS_ALREADY_REGISTERED);
            }
        }
        userRepository.save(user);
        return userRepository.findUserByEmailAndAndPassword(user.getEmail(), user.getPassword());
    }

    @Override
    public User updateUser(User user) throws CustomException {
        if(!userRepository.existsByUserId(user.getUserId())){
            throw new CustomException(errorMessages.USER_IS_NOT_EXISTS);
        }else{
            userRepository.saveAndFlush(user);
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
