package com.restfulapi.app.ws.service.impl;

import com.restfulapi.app.ws.io.entity.UserEntity;
import com.restfulapi.app.ws.io.repositories.UserRepository;
import com.restfulapi.app.ws.service.UserService;
import com.restfulapi.app.ws.shared.Utils;
import com.restfulapi.app.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record already exists");
        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(user, userEntity);
        String userId = utils.generateUserId(30);
        userEntity.setUserId(userId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserEntity storeData = userRepository.save(userEntity);
        // 'TULL05LkLiEiAEPKUbpTLH8OO7075J'
        UserDto returnValue = new UserDto();

        BeanUtils.copyProperties(storeData, returnValue);
        return returnValue;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
