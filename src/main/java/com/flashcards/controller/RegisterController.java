package com.flashcards.controller;

import com.flashcards.domain.dto.UserDto;
import com.flashcards.service.DbUserService;
import com.flashcards.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterController {

    @Autowired
    DbUserService userService;

    @Autowired
    LoginService loginService;

    public boolean register(String email, String password){

        boolean success = userService.registerUser(new UserDto(null, email, password));
        if(success){
            loginService.zaloguj(email, password);
        }
        return success;
    }


}
