package com.flashcards.controller;

import com.flashcards.domain.Flashcard;
import com.flashcards.domain.dto.FlashcardDto;
import com.flashcards.domain.dto.UserDto;
import com.flashcards.service.DbFlashcardService;
import com.flashcards.service.FlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FlashcardController {

    @Autowired
    private FlashcardService flashcardService;

    public List<FlashcardDto> flashcardsForUser(UserDto userDto){
        return flashcardService.getFlashcardsForUser(userDto);
    }



}
