package gra;

public class Plansza {
    public int[][] planszaDoGry;

    public Plansza(int size) {
        // Inicjalizacja tablicy dwuwymiarowej o podanym rozmiarze + 1 dla numeracji kolumn
        planszaDoGry = new int[size + 1][size + 1];

        // Wypełnienie tablicy zerami
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < size + 1; j++) {
                // Wypełnienie zerami   
                planszaDoGry[i][j] = 0;
            }
        }

        // Wypełnienie pierwszego wiersza numeracją kolumn
        for (int j = 1; j <= size; j++) {
            planszaDoGry[0][j] = j;
        }

        // Wypełnienie pierwszej kolumny numeracją wierszy
        for (int i = 1; i <= size; i++) {
            planszaDoGry[i][0] = i;
        }
    }
}
