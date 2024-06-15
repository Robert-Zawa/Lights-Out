package graGUI;

import javax.swing.*;

public class TrybRosnacejPlanszy extends TrybStandardowy {
    // Konstruktor przyjmujący obiekt Projekt i przekazujący go do konstruktora klasy bazowej
    public TrybRosnacejPlanszy(Projekt projekt) {
        super(projekt);
    }

    @Override
    public boolean planszaZapeliona(Plansza plansza, int size, JFrame frame) {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (plansza.planszaDoGry[i][j] == 0) {
                    return false; // Zwraca false, jeśli jakiekolwiek pole jest puste
                }
            }
        }
        if (size != Main.SIZEMAX) {
            frame.dispose();
            this.projekt.setSize(size+1);
            rozgrywka(size + 1);
            return false;
        }
        // Zapisanie wyniku po ukończeniu trybu rosnącej planszy
       
        frame.dispose();
        return true; // Zwraca true, jeśli wszystkie pola są wypełnione i plansza ma rozmiar 9x9
    }

    @Override
    public void zapiszWynik(JFrame frame, int size){
        String playerName = JOptionPane.showInputDialog(frame, "Gratulacje, ukończyłeś tryb rosnącej planszy! Podaj swoje imię:");
        if (playerName != null && !playerName.trim().isEmpty() && playerName != ",") {
            projekt.pobierzTabliceWynikow().dodajWynik(playerName, 0, liczbaRuchow); // Używamy 0 jako identyfikatora trybu rosnącej planszy
            JOptionPane.showMessageDialog(frame, "Wynik został zapisany!", "Koniec Gry", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
