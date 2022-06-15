package com.flashcards.repository;

import com.flashcards.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Klasa będąca częścią warstwy utrwalania danych
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    default Optional<User> findByEmail(String email) {
        User example = new User();
        example.setEmail(email);
        return findOne(Example.of(example));
    }

    @Override
    User save(User user);

    @Override
    void deleteById(Long id);
}
