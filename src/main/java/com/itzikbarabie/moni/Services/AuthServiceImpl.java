package com.itzikbarabie.moni.Services;

import com.itzikbarabie.moni.Entity.JwtRequest;
import com.itzikbarabie.moni.Exceptions.UnauthorizedException;
import com.itzikbarabie.moni.Repository.AuthRepository;
import com.itzikbarabie.moni.Utils.Definitions;
import com.itzikbarabie.moni.Utils.ObjectValidator;
import com.itzikbarabie.moni.Utils.PasswordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private Definitions definitions;
    @Autowired
    private PasswordManager passwordManager;
    @Autowired
    private ObjectValidator objectValidator;

    @Override
    public boolean authenticate(JwtRequest jwtRequest) throws UnauthorizedException {
        objectValidator.isValidJwtRequestObject(jwtRequest);
        if (authRepository.existsByEmailAndPassword(jwtRequest.getEmail(), passwordManager.hashPassword(jwtRequest.getPassword()))) {
            return true;
        } else {
            throw new UnauthorizedException(definitions.INVALID_CREDENTIAL);
        }
    }
}
