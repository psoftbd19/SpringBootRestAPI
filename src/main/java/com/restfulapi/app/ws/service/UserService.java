package com.restfulapi.app.ws.service;

import com.restfulapi.app.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {


    UserDto createUser(UserDto user);

}
