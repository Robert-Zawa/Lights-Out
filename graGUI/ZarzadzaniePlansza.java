package graGUI;

public class ZarzadzaniePlansza {

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
}
