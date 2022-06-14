package com.flashcards.repository;

import com.flashcards.domain.FiszkiTestWynik;
import com.flashcards.domain.Flashcard;
import com.flashcards.domain.User;
import com.flashcards.domain.dto.UserDto;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface FiszkiTestWynikRepository extends JpaRepository<FiszkiTestWynik, Long>  {

    @Override
    List<FiszkiTestWynik> findAll();

    default List<FiszkiTestWynik> findByUser(UserDto userDto){
        FiszkiTestWynik example = new FiszkiTestWynik();
        example.setUser(new User(userDto.getId(), null,null));
        return findAll(Example.of(example));
    };

    @Override
    Optional<FiszkiTestWynik> findById(Long id);

    @Override
    FiszkiTestWynik save(FiszkiTestWynik fiszkiTestWynik);

    @Override
    void deleteById(Long id);

    @Query(nativeQuery = true, value = "SELECT SUM(wynik) as wszystkieWyniki FROM fiszki_wynik")
    double getWyniki();

    @Query(nativeQuery = true, value = "SELECT SUM(liczba_fiszek) as wszystkieFiszki FROM fiszki_wynik")
    double getLiczbaFiszek();
}
