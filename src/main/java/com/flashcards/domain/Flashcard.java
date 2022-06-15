package com.flashcards.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Klasa reprezentująca encję z bazy danych
 */
@Entity
@Table(name = "flashcards")
@Data
@NoArgsConstructor
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "flashcard_id")
    private Long id;
    @Column(name = "slowo_polskie")
    private String slowoPolskie;
    @Column(name = "slowo_angielskie")
    private String slowoAngielskie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Flashcard(String slowoPolskie, String slowoAngielskie, User user) {
        this.slowoPolskie = slowoPolskie;
        this.slowoAngielskie = slowoAngielskie;
        this.user = user;
    }
}
