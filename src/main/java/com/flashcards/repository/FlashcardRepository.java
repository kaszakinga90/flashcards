package com.flashcards.repository;

import com.flashcards.domain.Flashcard;
import com.flashcards.domain.User;
import com.flashcards.domain.dto.UserDto;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Klasa będąca częścią warstwy utrwalania danych
 */
@Transactional
@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

    @Override
    List<Flashcard> findAll();

    default List<Flashcard> findByUser(UserDto userDto) {
        Flashcard example = new Flashcard();
        example.setUser(new User(userDto.getId(), userDto.getEmail(), userDto.getPassword()));
        return findAll(Example.of(example));
    }

    @Override
    Optional<Flashcard> findById(Long id);

    @Override
    Flashcard save(Flashcard flashcard);

    @Override
    void deleteById(Long id);
}
