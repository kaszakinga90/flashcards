package com.flashcards.controller;

import com.flashcards.domain.Flashcard;
import com.flashcards.domain.dto.FlashcardDto;
import com.flashcards.domain.dto.UserDto;
import com.flashcards.service.DbFlashcardService;
import com.flashcards.service.DbUserService;
import com.flashcards.service.FlashcardService;
import com.flashcards.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FlashcardController {


    @Autowired
    private FlashcardService flashcardService;

    @Autowired
    LoginService loginService;

    public List<FlashcardDto> flashcardsForLoggedInUser(){
        return loginService.currentLoggedInUser()
                .map(flashcardService::getFlashcardsForUser)
                .orElse(List.of());
    }

    public boolean saveFlashCard(String slowoPl, String slowoEn){
        return loginService.currentLoggedInUser()
                .map(u -> flashcardService.saveFlashcardForUser(new FlashcardDto(null, slowoPl,slowoEn, u)))
                .orElse(false);
    }



}
