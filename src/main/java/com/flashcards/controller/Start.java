package com.flashcards.controller;

import com.flashcards.gui.Rejestracja;
import com.flashcards.gui.SocketKlienta;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages = {"com.flashcards"})
@EnableJpaRepositories({"com.flashcards.repository"})
@EntityScan({"com.flashcards"})
public class Start {

    public static final int SERWER_PORT = 2002;
    public static final String HOST = "localhost";
    public static final String KONIEC_POLACZENIA = "KONIEC";


    public static void main(String[] args) {

        ApplicationContext context = new SpringApplicationBuilder(Start.class)
                .headless(false)
                .run(args);

        context.getBean(Rejestracja.class).pokazOkno();


//        SocketKlienta socketKlienta = new SocketKlienta();
//        socketKlienta.inicjalizuj();






//        Socket socket = null;
//        BufferedReader in = null;
//        PrintWriter out = null;
//        Scanner scanner = null;
//
//        try {
//            socket = new Socket(HOST, SERWER_PORT);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            out = new PrintWriter(
//                    new BufferedWriter(
//                            new OutputStreamWriter(socket.getOutputStream())), true);
//            scanner = new Scanner(System.in);
//
//            while (true) {
//                String line = in.readLine();
//                System.out.println(line);
//
//                String text = scanner.nextLine();
//                out.println(text);
//
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        if (in.equals(KONIEC_POLACZENIA)) {
//            try {
//                System.out.println("Zamykanie połączenia");
//                in.close();
//                System.out.println("Zamknięto in");
//                out.close();
//                System.out.println("Zamknięto out");
//                socket.close();
//                System.out.println("Zamknięto socket");
//                scanner.close();
//            } catch (IOException ex) {
//                System.out.println("Błąd przy zamykaniu połączenia");
//            }
//        }


    }
}
