package com.flashcards.repository;

import com.flashcards.domain.FiszkiTestWynik;
import com.flashcards.domain.QuizWynik;
import com.flashcards.domain.User;
import com.flashcards.domain.dto.UserDto;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Klasa będąca częścią warstwy utrwalania danych
 */
@Transactional
@Repository
public interface QuizWynikRepository extends JpaRepository<QuizWynik, Long> {

    @Override
    List<QuizWynik> findAll();

    default List<QuizWynik> findByUser(UserDto userDto) {
        QuizWynik example = new QuizWynik();
        example.setUser(new User(userDto.getId(), null, null));
        return findAll(Example.of(example));
    }

    @Override
    Optional<QuizWynik> findById(Long id);

    @Override
    QuizWynik save(QuizWynik quizWynik);

    @Override
    void deleteById(Long id);

    @Query(nativeQuery = true, value = "SELECT SUM(wynik) as wszystkieWyniki FROM quiz_wynik")
    double getWyniki();

    @Query(nativeQuery = true, value = "SELECT SUM(liczba_pytan) as wszystkiePytania FROM quiz_wynik")
    double getLiczbaPytan();
}
