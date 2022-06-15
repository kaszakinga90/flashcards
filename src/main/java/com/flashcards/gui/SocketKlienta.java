package com.flashcards.gui;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

@Component
public class SocketKlienta {

    public static final String KONIEC_POLACZENIA = "KONIEC POLACZENIA";
    public static final int SERWER_PORT = 2002;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner scanner;

    public Scanner getScanner() {
        return scanner;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Socket getSocket() {        return socket;    }
    public BufferedReader getIn() {        return in;    }
    public PrintWriter getOut() {        return out;    }

    public SocketKlienta()
    {
    }

    public void inicjalizuj() {
        try {
            // Tworzymy połączenie z serwerem.
            socket = new Socket("localhost", SERWER_PORT);
            //socket = new Socket("167.235.227.37", SERWER_PORT);

            //obiekt do otrzymywania danych od klienta
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            //tu mozemy pisac do klienta
            out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream())),true);
            scanner = new Scanner(System.in);


            while (true) {
                String line = in.readLine();
                System.out.println(line);

                String text = scanner.nextLine();
                out.println(text);

            }


            // Wątek nasłuchujący wiadomości od serwera.
//            Thread thread = new NasluchiwaczThread(in, output);
//            thread.start();

        } catch (IOException ex) {
            System.out.println("Błąd przy tworzeniu połączenia");
        }
    }
}
