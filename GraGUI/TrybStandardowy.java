package graGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.BevelBorder;

public class TrybStandardowy {
    protected Projekt projekt;
    protected int liczbaRuchow;

    // Konstruktor trybu standardowego, inicjalizuje projekt i liczbę ruchów
    public TrybStandardowy(Projekt projekt) {
        this.projekt = projekt;
        this.liczbaRuchow = 0;
    }

    // Metoda rozpoczynająca rozgrywkę
    public void rozgrywka(int boardSize) {
        projekt.resetPlanszy(); // Resetuje planszę gry
        int size; // Pobiera rozmiar planszy
        if (boardSize >= 2){
            size = boardSize;
        } else {
            size = projekt.getRozmiarPlanszy(); // Pobiera rozmiar planszy
        }
        Plansza plansza = projekt.getPlansza(); // Pobiera obiekt planszy

        JFrame frame = new JFrame("Tryb Standardowy");
        frame.setSize(600, 600); // Ustawia rozmiar okna gry
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ustawia działanie przy zamknięciu okna

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(size, size)); // Ustawienie siatki o rozmiarze planszy

        JButton[][] buttons = new JButton[size][size]; // Tablica przycisków reprezentujących pola planszy

        // Tworzenie przycisków dla każdego pola planszy
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.BLACK); // Ustawienie koloru tła przycisku
                buttons[i][j].setOpaque(true); // Ustawienie przycisku jako nieprzezroczystego
                buttons[i][j].setBorderPainted(true); // Ustawienie malowania krawędzi przycisku
                buttons[i][j].setFocusPainted(false); // Wyłączenie malowania obramowania po fokusu
                buttons[i][j].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); // Ustawienie wypukłego obramowania

                panel.add(buttons[i][j]); // Dodanie przycisku do panelu
                // Dostosowanie indeksowania opartego na 1 w planszaDoGry
                int finalI = i + 1; 
                int finalJ = j + 1; 

                // Dodanie akcji dla przycisku
                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ZarzadzaniePlansza.wypelnijPlansze(plansza, finalI, finalJ, size); // Wypełnia planszę w miejscu kliknięcia
                        liczbaRuchow++; // Zwiększa liczbę ruchów
                        // Aktualizacja kolorów przycisków w zależności od stanu planszy
                        for (int x = 1; x <= size; x++) {
                            for (int y = 1; y <= size; y++) {
                                if (plansza.planszaDoGry[x][y] == 1) {
                                    buttons[x - 1][y - 1].setBackground(Color.YELLOW); // Zmienia kolor na żółty, jeśli pole jest wypełnione
                                    buttons[x - 1][y - 1].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED)); // Ustawienie wklęsłego obramowania
                                } else {
                                    buttons[x - 1][y - 1].setBackground(Color.BLACK); // Zmienia kolor na czarny, jeśli pole jest puste
                                    buttons[x - 1][y - 1].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); // Ustawienie wypukłego obramowania
                                }
                            }
                        }
                        // Sprawdzenie, czy cała plansza jest zapełniona
                        if (planszaZapeliona(plansza, size, frame)) {
                            zapiszWynik(frame, size);
                            frame.dispose(); // Zamknięcie okna gry
                        }
                    }
                });
            }
        }

        frame.add(panel); // Dodanie panelu do ramki
        frame.setVisible(true); // Ustawienie okna jako widoczne
    }

    public void zapiszWynik(JFrame frame, int size){
        String playerName = JOptionPane.showInputDialog(frame, "Gratulacje, zapełniłeś całą planszę! Podaj swoje imię:");
        if (playerName != null && !playerName.trim().isEmpty() && playerName != ",") {
            projekt.pobierzTabliceWynikow().dodajWynik(playerName, size, liczbaRuchow);
            JOptionPane.showMessageDialog(frame, "Wynik został zapisany!", "Koniec Gry", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Metoda sprawdzająca, czy plansza jest w całości zapełniona
    public boolean planszaZapeliona(Plansza plansza, int size, JFrame frame) {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (plansza.planszaDoGry[i][j] == 0) {
                    return false; // Zwraca false, jeśli jakiekolwiek pole jest puste
                }
            }
        }
        return true; // Zwraca true, jeśli wszystkie pola są wypełnione
    }
}
