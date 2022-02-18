package com.itzikbarabie.moni.Services;

import com.itzikbarabie.moni.Model.MyUserDetails;
import com.itzikbarabie.moni.Model.User;
import com.itzikbarabie.moni.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticateServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            if(log.isInfoEnabled()) {
                log.info("~Authenticate: User Not Found!");
            }
            throw new UsernameNotFoundException("User Not Found");
        }
        return new MyUserDetails(user);
    }
}
