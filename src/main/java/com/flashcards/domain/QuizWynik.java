package com.flashcards.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Klasa reprezentująca encję z bazy danych
 */
@Entity
@Table(name = "quiz_wynik")
@Data
@NoArgsConstructor
public class QuizWynik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private Long id;

    @Column(name = "wynik")
    private double wynik;

    @Column(name = "liczba_pytan")
    private double liczbaPytan;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public QuizWynik(double wynik, double liczbaPytan, User user) {
        this.wynik = wynik;
        this.liczbaPytan = liczbaPytan;
        this.user = user;
    }
}
