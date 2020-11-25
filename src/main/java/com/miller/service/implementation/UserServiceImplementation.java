package com.miller.service.implementation;

import com.miller.domain.entity.ApiUser;
import com.miller.domain.repository.ApiUserRepository;
import com.miller.exception.UserNameNotFoundException;
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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        ApiUser user = apiUserRepository.findByUserName(userName)
                .orElseThrow(() -> new UserNameNotFoundException("Username not found"));

        String[] roles = user.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(user.getUserName())
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
                .userName(apiUser.getUserName())
                .admin(apiUser.isAdmin())
                .build();
    }
}
