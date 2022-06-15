package com.flashcards.domain;

import com.flashcards.repository.FlashcardRepository;
import com.flashcards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Klasa ładująca przykładowe dane do bazy
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FlashcardRepository flashcardRepository;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {

        User user1 = new User(null, "johnWick", "123");
        User user2 = new User(null, "asia", "1234");

        if (userRepository.count() == 0) {
            userRepository.save(user1);
            userRepository.save(user2);
        }

        Flashcard f1 = new Flashcard("rower", "bicycle", user1);
        Flashcard f2 = new Flashcard("opona", "tyre", user1);
        Flashcard f3 = new Flashcard("widelec", "fork", user1);
        Flashcard f4 = new Flashcard("rama", "frame", user1);
        Flashcard f5 = new Flashcard("piasta", "hub", user1);
        Flashcard f6 = new Flashcard("kierownica", "handlebar", user2);
        Flashcard f7 = new Flashcard("szprycha", "spoke", user2);

        if (flashcardRepository.count() == 0) {
            flashcardRepository.save(f1);
            flashcardRepository.save(f2);
            flashcardRepository.save(f3);
            flashcardRepository.save(f4);
            flashcardRepository.save(f5);
            flashcardRepository.save(f6);
            flashcardRepository.save(f7);
        }

        System.out.println("User repository: " + userRepository.count());
        System.out.println("Flashcard repository: " + flashcardRepository.count());
    }
}
