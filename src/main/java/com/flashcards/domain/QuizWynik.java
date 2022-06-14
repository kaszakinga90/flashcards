package com.flashcards.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "quiz_wynik")
@Data
public class QuizWynik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private Long id;

    @Column(name = "wynik")
    private double wynik;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
