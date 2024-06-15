package gra;

public class ZarzadzaniePlansza {

    // Metoda wyświetlająca planszę do gry
    public static void wyswietlPlansze(Plansza plansza, int size) {
        System.out.println("\nAktualna Plansza Do Gry:");
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < size + 1; j++) {
                System.out.print(plansza.planszaDoGry[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Metoda wypełniająca planszę zgodnie z podanymi koordynatami
    public static void wypelnijPlansze(Plansza plansza, int wiersz, int kolumna, int size) {
        // Zmiana stanu pola (wiersz, kolumna) na planszy
        if (plansza.planszaDoGry[wiersz][kolumna] == 1) {
            plansza.planszaDoGry[wiersz][kolumna] = 0;
        } else {
            plansza.planszaDoGry[wiersz][kolumna] = 1;
        }
    
        // Zmiana stanu pola powyżej (wiersz - 1, kolumna)
        if (wiersz != 1) {
            if (plansza.planszaDoGry[wiersz - 1][kolumna] == 0) {
                plansza.planszaDoGry[wiersz - 1][kolumna] = 1;
            } else {
                plansza.planszaDoGry[wiersz - 1][kolumna] = 0;
            }
        }
    
        // Zmiana stanu pola po lewej stronie (wiersz, kolumna - 1)
        if (kolumna != 1) {
            if (plansza.planszaDoGry[wiersz][kolumna - 1] == 0) {
                plansza.planszaDoGry[wiersz][kolumna - 1] = 1;
            } else {
                plansza.planszaDoGry[wiersz][kolumna - 1] = 0;
            }
        }
    
        // Zmiana stanu pola poniżej (wiersz + 1, kolumna)
        if (wiersz != size) {
            if (plansza.planszaDoGry[wiersz + 1][kolumna] == 0) {
                plansza.planszaDoGry[wiersz + 1][kolumna] = 1;
            } else {
                plansza.planszaDoGry[wiersz + 1][kolumna] = 0;
            }
        }
    
        // Zmiana stanu pola po prawej stronie (wiersz, kolumna + 1)
        if (kolumna != size) {
            if (plansza.planszaDoGry[wiersz][kolumna + 1] == 0) {
                plansza.planszaDoGry[wiersz][kolumna + 1] = 1;
            } else {
                plansza.planszaDoGry[wiersz][kolumna + 1] = 0;
            }
        }
    }

    // Metoda zliczająca punkty na planszy
    public static int zliczPunkty(Plansza plansza, int size) {
        int liczbaPunktow = 0;
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                if (plansza.planszaDoGry[i][j] == 1) {
                    liczbaPunktow++;
                }
            }
        }
        return liczbaPunktow;
    }

    // Metoda wyświetlająca statystyki gry
    public static void wyswietlStatystyki(int liczbaPunktow, int liczbaRuchow, int size) {
        System.out.println("\nAktualne statystyki:");
        System.out.println("Liczba punktów: " + liczbaPunktow + " / " + (size * size));
        System.out.println("Liczba ruchów: " + liczbaRuchow);
    }
}
