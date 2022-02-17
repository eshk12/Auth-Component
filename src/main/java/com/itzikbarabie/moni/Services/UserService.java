package com.itzikbarabie.moni.Services;

import com.itzikbarabie.moni.Entity.BasicResponseModel;
import com.itzikbarabie.moni.Model.User;
import com.itzikbarabie.moni.Exceptions.CustomException;
import com.itzikbarabie.moni.Model.UserDto;

import java.util.List;

public interface UserService {

    UserDto addUser(UserDto UserDto) throws CustomException;

    UserDto updateUser(UserDto userDto) throws CustomException;

    BasicResponseModel deleteUserByObjectId(long objectId, boolean deleted) throws CustomException;

    List<UserDto> getAllUsers();

    UserDto findUserByObjectId(long objectId) throws CustomException;

    UserDto findUserByUsername(String username) throws CustomException;

}
