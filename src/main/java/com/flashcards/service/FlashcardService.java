package com.flashcards.service;

import com.flashcards.domain.dto.FlashcardDto;
import com.flashcards.domain.dto.UserDto;
import com.flashcards.mapper.FlashcardMapper;
import com.flashcards.repository.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardService {

    @Autowired
    FlashcardRepository flashcardRepository;

    @Autowired
    FlashcardMapper flashcardMapper;

    public List<FlashcardDto> getFlashcardsForUser(UserDto userDto){
        return flashcardMapper.mapToFlashcardDtoList(flashcardRepository.findByUser(userDto));
    }

    public void saveFlashcardForUser(FlashcardDto flashcardDto){
        flashcardRepository.save(flashcardMapper.mapToFlashcard(flashcardDto));
    }
}
