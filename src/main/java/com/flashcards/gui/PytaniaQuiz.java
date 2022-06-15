package com.flashcards.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PytaniaQuiz {
    public static List<PytanieQuiz> ListaPytan;

    static {
        ListaPytan = new ArrayList<>(List.of(
                new PytanieQuiz("I'm very happy _____ in India. I really miss being there.",
                        new Odpowiedz("to live"),
                        new Odpowiedz("to have lived", true),
                        new Odpowiedz("to be lived"),
                        new Odpowiedz("to be living")),
                new PytanieQuiz("They didn't reach an agreement ______ their differences.",
                        new Odpowiedz("on account of", true),
                        new Odpowiedz("due"),
                        new Odpowiedz("because"),
                        new Odpowiedz("owing")),
                new PytanieQuiz("I wish I _____ those words. But now it's too late.",
                        new Odpowiedz("not having said"),
                        new Odpowiedz("have never said"),
                        new Odpowiedz("never said"),
                        new Odpowiedz("had never said", true)),
                new PytanieQuiz("The woman, who has been missing for 10 days, is believed _____.",
                        new Odpowiedz("to be abducted"),
                        new Odpowiedz("to be abducting"),
                        new Odpowiedz("to have been abducted", true),
                        new Odpowiedz("to have been abducting")),
                new PytanieQuiz("She was working on her computer with her baby next to _____.",
                        new Odpowiedz("herself"),
                        new Odpowiedz("her", true),
                        new Odpowiedz("her own"),
                        new Odpowiedz("hers")),
                new PytanieQuiz("_____ to offend anyone, she said both cakes were equally good.",
                        new Odpowiedz("Not wanting", true),
                        new Odpowiedz("As not wanting"),
                        new Odpowiedz("She didn't want"),
                        new Odpowiedz("Because not wanting")),
                new PytanieQuiz("_____ in trying to solve this problem. It's clearly unsolvable.",
                        new Odpowiedz("There's no point", true),
                        new Odpowiedz("It's no point"),
                        new Odpowiedz("There isn't point"),
                        new Odpowiedz("It's no need")),
                new PytanieQuiz("Last year, when I last met her, she told me she _____ a letter every day for the last two months.",
                        new Odpowiedz("had written"),
                        new Odpowiedz("has written"),
                        new Odpowiedz("had been writing", true),
                        new Odpowiedz("wrote")),
                new PytanieQuiz("He _____ robbed as he was walking out of the bank.",
                        new Odpowiedz("had"),
                        new Odpowiedz("did"),
                        new Odpowiedz("got", true),
                        new Odpowiedz("were")),
                new PytanieQuiz("_____ forced to do anything. He acted of his own free will.",
                        new Odpowiedz("In no way was he", true),
                        new Odpowiedz("No way he was"),
                        new Odpowiedz("In any way he was"),
                        new Odpowiedz("In any way was he")),
                new PytanieQuiz("It _____ the best idea to pay for those tickets by credit card. It was too risky.",
                        new Odpowiedz("may not have been", true),
                        new Odpowiedz("may not be"),
                        new Odpowiedz("might not be"),
                        new Odpowiedz("must not have been")),
                new PytanieQuiz("They _____ in the basement for three months.",
                        new Odpowiedz("were made sleeping"),
                        new Odpowiedz("were made sleep"),
                        new Odpowiedz("were made to sleep", true),
                        new Odpowiedz("made to sleep")),
                new PytanieQuiz("We'll never know what might have happened _____ the email earlier.",
                        new Odpowiedz("if he sent"),
                        new Odpowiedz("had he sent", true),
                        new Odpowiedz("if he has sent"),
                        new Odpowiedz("did he sent")),
                new PytanieQuiz("If success _____, we need to prepare ourselves for every possible scenario.",
                        new Odpowiedz("is to be achieved"),
                        new Odpowiedz("is achieved"),
                        new Odpowiedz("will be achieved"),
                        new Odpowiedz("is due to achieve", true)),
                new PytanieQuiz("______ gifts to the judges.",
                        new Odpowiedz("It's not allowed offering"),
                        new Odpowiedz("It's not permitted to offer", true),
                        new Odpowiedz("It's not permitted offering"),
                        new Odpowiedz("It's not allowed to offer"))
        ));
    }

    public static ArrayList<PytanieQuiz> losujPytania() {
        ArrayList<PytanieQuiz> WylosowanePytania = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            int losowaLiczba = rand.nextInt(ListaPytan.size());
            WylosowanePytania.add(ListaPytan.get(losowaLiczba));
        }
        return WylosowanePytania;
    }
}
