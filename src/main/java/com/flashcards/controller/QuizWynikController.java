package com.flashcards.controller;

import com.flashcards.domain.dto.QuizWynikDto;
import com.flashcards.service.LoginService;
import com.flashcards.service.QuizWynikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Klasa będąca częścią warstwy prezentacji
 */
@Controller
public class QuizWynikController {

    @Autowired
    private QuizWynikService quizWynikService;

    @Autowired
    LoginService loginService;

    public double quizWynikForLoggedInUser() {
        return loginService.currentLoggedInUser()
                .map(quizWynikService::getQuizWynikForUser)
                .orElseThrow();
    }

    public void saveQuizWynik(double wynik, double liczbaPytan) {
        loginService.currentLoggedInUser().ifPresent(u ->
                quizWynikService.saveQuizWynikForUser(new QuizWynikDto(null, wynik, liczbaPytan, u)));
    }
}
