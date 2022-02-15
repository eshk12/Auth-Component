package com.itzikbarabie.moni.Controllers;

import com.itzikbarabie.moni.Model.User;
import com.itzikbarabie.moni.Exceptions.CustomException;
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
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setPermission(0);
        user.setTokens(null);
        user.setDeleted(false);
        try{
            return ResponseEntity.ok()
                    .body(userService.addUser(user));
        } catch (CustomException e){
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user){
        return ResponseEntity.ok()
                .body(userService.updateUser(user));
    }
}
