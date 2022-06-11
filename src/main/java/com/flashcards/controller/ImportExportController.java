package com.flashcards.controller;

import com.flashcards.domain.dto.FlashcardDto;
import com.flashcards.service.FlashcardService;
import com.flashcards.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ImportExportController {


    @Autowired
    private FlashcardService flashcardService;
    @Autowired
    private LoginService loginService;

    public boolean zapiszDoPliku(File plik) {
        try (FileWriter fw = new FileWriter(plik)) {
            for (FlashcardDto f : flashcardService.getFlashcardsForUser(loginService.currentLoggedInUser().get())) {
                fw.write(f.getSlowoPolskie() + ";" + f.getSlowoAngielskie() + "\n");
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean wczytajZpliku(File plik) {
        try (Stream<String> lines = Files.lines(plik.toPath())) {
            Set<FlashcardDto> existingFlashcards = new HashSet<>(flashcardService.getFlashcardsForUser(loginService.currentLoggedInUser().get()));
            System.out.println(existingFlashcards);
            lines
                    .filter(l -> l.contains(";"))

                    .map(l -> l.split(";"))
                    .map(arr -> new FlashcardDto(null, arr[0], arr[1], loginService.currentLoggedInUser().get()))
                    .filter(fl -> !existingFlashcards.contains(fl))
                    .peek(System.out::println)
                    .forEach(flashcardService::saveFlashcardForUser);
            return true;

        } catch (IOException e) {
            return false;
        }
    }
}
