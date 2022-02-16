package com.itzikbarabie.moni.Controllers;

import com.itzikbarabie.moni.Model.User;
import com.itzikbarabie.moni.Exceptions.CustomException;
import com.itzikbarabie.moni.Model.UserDto;
import com.itzikbarabie.moni.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        try{
            return ResponseEntity.ok()
                    .body(userService.addUser(userDto));
        } catch (CustomException e){
            throw new CustomException(e.getMessage());
        }
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto){
        try {
            return ResponseEntity.ok()
                    .body(userService.updateUser(userDto));
        }catch (CustomException e){
            throw new CustomException(e.getMessage());
        }
    }

    @PostMapping("/deleteUser")
    //public ResponseEntity<?> deleteUser(@RequestParam Map<String, Long> objectId, @RequestParam Map<String, Boolean> deleted){
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        try{
            return ResponseEntity.ok()
                    .body(userService.deleteUserByObjectId(user.getObjectId(), user.isDeleted()));
        }catch (CustomException e){
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/getAllUsers")
    public List<?> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getUserByObjectId")
    public ResponseEntity<?> getUserByObjectId(@RequestBody User user){
        try{
            return ResponseEntity.ok()
                    .body(userService.findUserByObjectId(user.getObjectId()));
        }catch (CustomException e){
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/getUserByEmail")
    public ResponseEntity<?> getUserByEmail(@RequestBody User user){
        try{
            return ResponseEntity.ok()
                    .body(userService.findUserByEmail(user.getEmail()));
        }catch (CustomException e){
            throw new CustomException(e.getMessage());
        }
    }
}
