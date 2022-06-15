package com.flashcards.controller;

import com.flashcards.gui.Rejestracja;
import com.flashcards.gui.SocketKlienta;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.flashcards"})
@EnableJpaRepositories({"com.flashcards.repository"})
@EntityScan({"com.flashcards"})
public class Start {
    public static void main(String[] args) {

        ApplicationContext context = new SpringApplicationBuilder(Start.class)
                .headless(false)
                .run(args);

        context.getBean(Rejestracja.class).pokazOkno();

        SocketKlienta socketKlienta = new SocketKlienta();
        socketKlienta.inicjalizuj();
    }
}
