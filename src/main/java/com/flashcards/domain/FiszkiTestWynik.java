package com.flashcards.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private int wynik;

    @Column(name = "liczba_fiszek")
    private int liczbaFiszek;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public FiszkiTestWynik(int wynik, int liczbaFiszek, User user){
        this.wynik = wynik;
        this.liczbaFiszek = liczbaFiszek;
        this.user = user;
    }
}