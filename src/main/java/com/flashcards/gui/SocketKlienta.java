package com.flashcards.gui;

import com.flashcards.controller.LoginController;
import com.flashcards.service.LoginService;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

@Getter
@Setter
@Component
public class SocketKlienta {

    public static final String KONIEC_POLACZENIA = "KONIEC POLACZENIA";
    public static final int SERWER_PORT = 2002;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner scanner;

    @Autowired
    LoginController loginController;

    public SocketKlienta() {
    }

    public void inicjalizuj() {
        try {
            socket = new Socket("localhost", SERWER_PORT);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream())), true);
            scanner = new Scanner(System.in);

            while (true) {
                String line = in.readLine();
                System.out.println(line);

                if (line.equals("KONIEC POLACZENIA")) {
                    try {
                        System.out.println("Zamykanie połączenia");
                        in.close();
                        System.out.println("Zamknięto in");
                        out.close();
                        System.out.println("Zamknięto out");
                        socket.close();
                        System.out.println("Zamknięto socket");
                        scanner.close();
                        System.out.println("Zamknięto scanner");
                        break;
                    } catch (IOException ex) {
                        System.out.println("Błąd przy zamykaniu połączenia");
                    }
                }
                String text = scanner.nextLine();
                out.println(text);
            }
        } catch (IOException ex) {
            System.out.println("Błąd przy tworzeniu połączenia");
        }
    }
}
