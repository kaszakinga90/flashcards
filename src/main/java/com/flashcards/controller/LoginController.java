package com.flashcards.controller;

import com.flashcards.domain.dto.UserDto;
import com.flashcards.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    public Optional<UserDto> loggedInUser(){
         return loginService.currentLoggedInUser();
    }
}
