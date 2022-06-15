package com.flashcards.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Klasa reprezentująca encję z bazy danych
 */
@Entity
@Table(name = "fiszki_wynik")
@Data
@NoArgsConstructor
public class FiszkiTestWynik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private Long id;

    @Column(name = "wynik")
    private double wynik;

    @Column(name = "liczba_fiszek")
    private double liczbaFiszek;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public FiszkiTestWynik(double wynik, double liczbaFiszek, User user) {
        this.wynik = wynik;
        this.liczbaFiszek = liczbaFiszek;
        this.user = user;
    }
}
