package com.miller.service.implementation;

import com.miller.domain.entity.ApiUser;
import com.miller.domain.repository.ApiUserRepository;
import com.miller.exceptions.InvalidPasswordException;
import com.miller.exceptions.UserNameNotFoundException;
import com.miller.rest.dto.ApiUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImplementation implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final ApiUserRepository apiUserRepository;

    public UserServiceImplementation(
            @Autowired PasswordEncoder passwordEncoder,
            @Autowired ApiUserRepository apiUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.apiUserRepository = apiUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApiUser user = apiUserRepository.findByUsername(username)
                .orElseThrow(() -> new UserNameNotFoundException("Username not found"));

        String[] roles = user.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    @Transactional
    public ApiUserDTO save(ApiUser apiUser) {
        String encryptedPassword = passwordEncoder.encode(apiUser.getPassword());

        apiUser.setPassword(encryptedPassword);

        apiUser = apiUserRepository.save(apiUser);

        return ApiUserDTO
                .builder()
                .id(apiUser.getId())
                .username(apiUser.getUsername())
                .admin(apiUser.isAdmin())
                .build();
    }

    public  UserDetails authentication(ApiUser apiUser) {
        UserDetails user = loadUserByUsername(apiUser.getUsername());

        boolean isCorrectPassword = passwordEncoder.matches(apiUser.getPassword(), user.getPassword());

        if(isCorrectPassword){
            return user;
        }

        throw new InvalidPasswordException();
    }
}
