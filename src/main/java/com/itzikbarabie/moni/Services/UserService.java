package com.itzikbarabie.moni.Services;

import com.itzikbarabie.moni.Model.User;
import com.itzikbarabie.moni.Exceptions.CustomException;

import java.util.List;

public interface UserService {

    User addUser(User user) throws CustomException;

    User updateUser(User user) throws CustomException;

    void deleteUser(long userId) throws CustomException;

    List<User> getAllUsers();

    User findUserById(long userId) throws CustomException;

    User findUserByEmail(String email) throws CustomException;

}
