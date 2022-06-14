package com.flashcards.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_username")
    private String email;

    @Column(name = "user_password")
    private String password;

    @OneToMany(
            targetEntity = Flashcard.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Flashcard> flashcards = new ArrayList<>();

    @OneToMany(
            targetEntity = FiszkiTestWynik.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<FiszkiTestWynik> fiszkiTestWynik = new ArrayList<>();

    @OneToMany(
            targetEntity = QuizWynik.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<QuizWynik> quizWynik = new ArrayList<>();

    public User(Long id, String email, String password){
        this.email = email;
        this.password = password;
    }


}
