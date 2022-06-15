package com.flashcards.service;

import com.flashcards.domain.dto.FiszkiTestWynikDto;
import com.flashcards.domain.dto.UserDto;
import com.flashcards.mapper.FiszkiTestWynikMapper;
import com.flashcards.repository.FiszkiTestWynikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Klasa będąca częścią warstwy logiki biznesowej
 */
@Service
public class FiszkiTestWynikService {

    @Autowired
    FiszkiTestWynikRepository fiszkiTestWynikRepository;

    @Autowired
    FiszkiTestWynikMapper fiszkiTestWynikMapper;

    public double getFiszkiTestWynikForUser(UserDto userDto) {
        List<FiszkiTestWynikDto> wyniki = fiszkiTestWynikMapper.mapToFiszkiTestWynikDtoList(fiszkiTestWynikRepository.findByUser(userDto));
        double sumaWynikow = 0;
        double sumaFiszek = 0;
        for (FiszkiTestWynikDto w : wyniki) {
            sumaWynikow += w.getWynik();
            sumaFiszek += w.getLiczbaFiszek();
        }
        return sumaWynikow / sumaFiszek;
    }

    public void saveFiszkiTestWynikForUser(FiszkiTestWynikDto fiszkiTestWynikDto) {
        fiszkiTestWynikRepository.save(fiszkiTestWynikMapper.mapToFiszkiTestWynik(fiszkiTestWynikDto));
    }
}
