package com.itzikbarabie.moni.Controllers;

import com.itzikbarabie.moni.Exceptions.UnauthorizedException;
import com.itzikbarabie.moni.Entity.AuthenticationRequest;
import com.itzikbarabie.moni.Entity.AuthenticationResponse;
import com.itzikbarabie.moni.Services.AuthenticateService;
import com.itzikbarabie.moni.Utils.Definitions;
import com.itzikbarabie.moni.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticateService authenticateService;

    @Autowired
    private Definitions definitions;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws UnauthorizedException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException(definitions.INVALID_CREDENTIAL);
        }
        final UserDetails userDetails = authenticateService
                .loadUserByUsername(authenticationRequest.getUsername());
        return ResponseEntity.ok()
                .body(new AuthenticationResponse(jwtUtil.generateToken(userDetails)));
    }
}




        /*try{
            authService.authenticate(authenticationRequest);
            return ResponseEntity.ok()
                    .body(new AuthenticationResponse("bla"));
        } catch (UnauthorizedException e){
            throw new UnauthorizedException(e.getMessage());
        }*/