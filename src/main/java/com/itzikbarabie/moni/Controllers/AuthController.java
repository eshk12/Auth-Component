package com.itzikbarabie.moni.Controllers;

import com.itzikbarabie.moni.Entity.JwtRequest;
import com.itzikbarabie.moni.Entity.JwtResponse;
import com.itzikbarabie.moni.Exceptions.CustomException;
import com.itzikbarabie.moni.Exceptions.UnauthorizedException;
import com.itzikbarabie.moni.Model.User;
import com.itzikbarabie.moni.Services.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) throws UnauthorizedException{
        try{
            authService.authenticate(jwtRequest);
            return ResponseEntity.ok()
                    .body(new JwtResponse("bla"));
        } catch (UnauthorizedException e){
            throw new UnauthorizedException(e.getMessage());
        }
    }
}
