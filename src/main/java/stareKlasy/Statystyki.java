package stareKlasy;

import java.util.ArrayList;
import java.util.List;

public class Statystyki {
    public static List<Integer> WynikFiszek = new ArrayList<>();
    public static List<Integer> RozegraneFiszki = new ArrayList<>();
    public static List<Integer> WynikQuizu = new ArrayList<>();
    public static List<Integer> RozegraneQuizy = new ArrayList<>();

    public static int CalkowityWynik(List<Integer> lista)
    {
        int calkowityWynik = 0;
        for(int wynik : lista)
        {
            calkowityWynik += wynik;
        }

        return calkowityWynik;
    }
}

