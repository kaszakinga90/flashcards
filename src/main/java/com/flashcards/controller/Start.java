package com.flashcards.controller;

import com.flashcards.domain.dto.UserDto;
import com.flashcards.gui.OknoGlowne;
import com.flashcards.gui.Rejestracja;
import com.flashcards.service.DbUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Start {
    static DbUserService dbUserService;

    public static void pokazOknoGlowne(){
        OknoGlowne og = new OknoGlowne();
        og.pokazOkno();
    }

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);



        Rejestracja r = new Rejestracja();
        r.addZarejestrujButtonListener(e -> {
            UserDto newUser = r.aktualnyUser();


            if(dbUserService.registerUser(newUser)){
                r.zamknijOkno();
                pokazOknoGlowne();
            } else {
                r.showErrorMessage();
            }

        });


    }
}
