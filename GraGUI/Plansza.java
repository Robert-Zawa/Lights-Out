package graGUI;

public class Plansza {
    // Dwuwymiarowa tablica reprezentująca planszę do gry
    public int[][] planszaDoGry;

    // Konstruktor klasy Plansza, inicjalizujący planszę o zadanym rozmiarze
    public Plansza(int size) {
        // Inicjalizacja planszy o rozmiarze (size + 1) x (size + 1)
        planszaDoGry = new int[size + 1][size + 1];

        // Wypełnienie planszy wartościami początkowymi (0)
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < size + 1; j++) {
                planszaDoGry[i][j] = 0;
            }
        }

        // Ustawienie nagłówków kolumn od 1 do size
        for (int j = 1; j <= size; j++) {
            planszaDoGry[0][j] = j;
        }

        // Ustawienie nagłówków wierszy od 1 do size
        for (int i = 1; i <= size; i++) {
            planszaDoGry[i][0] = i;
        }
    }
}
