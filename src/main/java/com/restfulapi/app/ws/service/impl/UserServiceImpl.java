package com.restfulapi.app.ws.service.impl;

import com.restfulapi.app.ws.io.entity.UserEntity;
import com.restfulapi.app.ws.io.repositories.UserRepository;
import com.restfulapi.app.ws.service.UserService;
import com.restfulapi.app.ws.shared.Utils;
import com.restfulapi.app.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;


    @Override
    public UserDto createUser(UserDto user) {

        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(user, userEntity);
        String userId = utils.generateUserId(10);
        userEntity.setUserId(userId);
        userEntity.setEncryptedPassword("TestPassword");
        UserEntity storeData = userRepository.save(userEntity);
       // 'TULL05LkLiEiAEPKUbpTLH8OO7075J'
        UserDto returnValue = new UserDto();

        BeanUtils.copyProperties(storeData, returnValue);
        return returnValue;
    }
}
