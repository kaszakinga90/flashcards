package com.flashcards.service;

import com.flashcards.domain.Flashcard;
import com.flashcards.domain.exceptions.FlashcardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.flashcards.repository.FlashcardRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbFlashcardService {

    private final FlashcardRepository flashcardRepository;

    public void createFlashcard(Flashcard flashcard) {
        flashcardRepository.save(flashcard);
    }

    public List<Flashcard> getAllFlashcards() {
        return flashcardRepository.findAll();
    }

    public Optional<Flashcard> getFlashcard(final Long id) {
        return flashcardRepository.findById(id);
    }

    public void deleteFlashcard(final Long id) {
        if (flashcardRepository.existsById(id)) {
            flashcardRepository.deleteById(id);
        }
    }

    public Flashcard updateFlashcard(Long flashcardId, Flashcard flashcard) throws FlashcardNotFoundException {
        Flashcard toUpdate = flashcardRepository.findById(flashcardId).orElseThrow(FlashcardNotFoundException::new);
        toUpdate.setSlowoPolskie(flashcard.getSlowoPolskie());
        toUpdate.setSlowoAngielskie(flashcard.getSlowoAngielskie());
        toUpdate.setUser(flashcard.getUser());
        return flashcardRepository.save(toUpdate);
    }
}
