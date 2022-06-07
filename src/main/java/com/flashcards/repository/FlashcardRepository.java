package com.flashcards.repository;

import com.flashcards.domain.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long>  {

    @Override
    List<Flashcard> findAll();

    @Override
    Optional<Flashcard> findById(Long id);

    @Override
    Flashcard save(Flashcard flashcard);

    @Override
    void deleteById(Long id);
}
