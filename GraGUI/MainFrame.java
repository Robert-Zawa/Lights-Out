package graGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private Projekt projekt;

    // Konstruktor głównego okna aplikacji
    public MainFrame() {
        projekt = new Projekt(); // Tworzy nowy obiekt projektu
        setTitle("Gra"); // Ustawia tytuł okna
        setSize(400, 300); // Ustawia rozmiar okna
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ustawia działanie przy zamknięciu okna
        setLocationRelativeTo(null); // Ustawia okno na środku ekranu

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1)); // Ustawia układ siatki z 5 wierszami

        // Tworzenie przycisku "Zagraj"
        JButton btnZagraj = new JButton("Zagraj");
        btnZagraj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TrybStandardowy(projekt).rozgrywka(0);
            }
        });

        // Tworzenie przycisku "Zwiększająca się plansza"
        JButton btnZwiekszajacaPlansza = new JButton("Zwiększająca się plansza");
        btnZwiekszajacaPlansza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TrybRosnacejPlanszy(projekt).rozgrywka(2);;
            }
        });

        // Tworzenie przycisku "Ustaw rozmiar planszy"
        JButton btnUstawRozmiar = new JButton("Ustaw rozmiar planszy");
        btnUstawRozmiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Pobiera nowy rozmiar planszy od użytkownika
                String input = JOptionPane.showInputDialog("Podaj rozmiar planszy (kwadrat, np. 5 dla 5x5):");
                if (input != null) {
                    try {
                        int newSize = Integer.parseInt(input);
                        projekt.ustawRozmiarPlanszy(newSize);
                    } catch (NumberFormatException ex) {
                        // Wyświetla błąd, jeśli rozmiar jest nieprawidłowy
                        JOptionPane.showMessageDialog(null, "Nieprawidłowy rozmiar.");
                    }
                }
            }
        });

        // Tworzenie przycisku "Wyświetl wyniki"
        JButton btnWyswietlWyniki = new JButton("Wyświetl wyniki");
        btnWyswietlWyniki.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                projekt.pobierzTabliceWynikow().wyswietlWynikWszystkie();
            }
        });

        // Tworzenie przycisku "Wyjście z gry"
        JButton btnWyjscie = new JButton("Wyjście z gry");
        btnWyjscie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Zamyka aplikację
                System.exit(0);
            }
        });

        // Dodanie przycisków do panelu
        panel.add(btnZagraj);
        panel.add(btnZwiekszajacaPlansza);
        panel.add(btnUstawRozmiar);
        panel.add(btnWyswietlWyniki);
        panel.add(btnWyjscie);

        add(panel);
        setVisible(true); // Ustawienie okna jako widoczne
    }
}
