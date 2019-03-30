package com.restfulapi.app.ws.ui.controller;

import com.restfulapi.app.ws.service.UserService;
import com.restfulapi.app.ws.shared.dto.UserDto;
import com.restfulapi.app.ws.ui.model.request.UserDetailsRequestModel;
import com.restfulapi.app.ws.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")//http://localhost:8080/users
public class UserController {


    @Autowired
    UserService service;

    @GetMapping
    public String getUser() {
        return "get user called";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {


        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails, userDto);  // 1.  data pass to userDto

        UserDto user = service.createUser(userDto);  // 2. goto service layer


        BeanUtils.copyProperties(user, returnValue);

        return returnValue;
    }


    @PutMapping
    public String updateUser() {

        return "update user called";
    }

    @DeleteMapping
    public String deleteUser() {

        return "delete user called";
    }
}
