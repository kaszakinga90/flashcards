package com.flashcards.controller;

import com.flashcards.domain.dto.FiszkiTestWynikDto;
import com.flashcards.service.FiszkiTestWynikService;
import com.flashcards.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Klasa będąca częścią warstwy prezentacji
 */
@Controller
public class FiszkiTestWynikController {

    @Autowired
    private FiszkiTestWynikService fiszkiTestWynikService;

    @Autowired
    LoginService loginService;

    public double fiszkiTestWynikForLoggedInUser() {
        return loginService.currentLoggedInUser()
                .map(fiszkiTestWynikService::getFiszkiTestWynikForUser)
                .orElseThrow();
    }

    public void saveFiskiTestWynik(double wynik, double liczbaFiszek) {
        loginService.currentLoggedInUser().ifPresent(u ->
                fiszkiTestWynikService.saveFiszkiTestWynikForUser(new FiszkiTestWynikDto(null, wynik, liczbaFiszek, u)));
    }
}
