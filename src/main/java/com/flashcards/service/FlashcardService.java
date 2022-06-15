package com.flashcards.service;

import com.flashcards.domain.dto.FlashcardDto;
import com.flashcards.domain.dto.UserDto;
import com.flashcards.mapper.FlashcardMapper;
import com.flashcards.repository.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Klasa będąca częścią warstwy logiki biznesowej
 */
@Service
public class FlashcardService {

    @Autowired
    FlashcardRepository flashcardRepository;

    @Autowired
    FlashcardMapper flashcardMapper;

    public List<FlashcardDto> getFlashcardsForUser(UserDto userDto){
        return flashcardMapper.mapToFlashcardDtoList(flashcardRepository.findByUser(userDto));
    }

    public boolean saveFlashcardForUser(FlashcardDto flashcardDto){
        if(flashcardDto.getSlowoPolskie().isBlank() || flashcardDto.getSlowoAngielskie().isBlank()){
            return false;
        }
        flashcardRepository.save(flashcardMapper.mapToFlashcard(flashcardDto));
        return true;
    }
}
