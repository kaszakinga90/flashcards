package com.flashcards.service;

import com.flashcards.domain.dto.QuizWynikDto;
import com.flashcards.domain.dto.UserDto;
import com.flashcards.mapper.QuizWynikMapper;
import com.flashcards.repository.QuizWynikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa będąca częścią warstwy logiki biznesowej
 */
@Service
public class QuizWynikService {

    @Autowired
    QuizWynikRepository quizWynikRepository;

    @Autowired
    QuizWynikMapper quizWynikMapper;

    public double getQuizWynikForUser(UserDto userDto) {
        List<QuizWynikDto> wyniki = quizWynikMapper.mapToQuizWynikDtoList(quizWynikRepository.findByUser(userDto));
        double sumaWynikow = 0;
        double sumaPytan = 0;
        for (QuizWynikDto w : wyniki) {
            sumaWynikow += w.getWynik();
            sumaPytan += w.getLiczbaPytan();
        }
        return sumaWynikow / sumaPytan;
    }

    public void saveQuizWynikForUser(QuizWynikDto quizWynikDto) {
        quizWynikRepository.save(quizWynikMapper.mapToQuizWynik(quizWynikDto));
    }
}
