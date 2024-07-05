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
        setSize(800, 800); // Ustawia rozmiar okna
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ustawia działanie przy zamknięciu okna
        setLocationRelativeTo(null); // Ustawia okno na środku ekranu

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(50, 30, 50, 30); // Ustawienie marginesów pomiędzy przyciskami

        // Tworzenie przycisku "Zagraj"
        Przycisk btnZagraj = new Przycisk("");
        btnZagraj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TrybStandardowy(projekt).rozgrywka(0);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.weightx = 1.0; // Ustawienie wagi w osi x dla responsywności
        gbc.weighty = 1.0; // Ustawienie wagi w osi y dla responsywności
        panel.add(btnZagraj, gbc);

        // Tworzenie przycisku "Zwiększająca się plansza"
        JButton btnZwiekszajacaPlansza = new JButton("Zwiększająca się plansza");
        btnZwiekszajacaPlansza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TrybRosnacejPlanszy(projekt).rozgrywka(2);
            }
        });
        gbc.gridy = 1;
        panel.add(btnZwiekszajacaPlansza, gbc);

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
        gbc.gridy = 2;
        panel.add(btnUstawRozmiar, gbc);

        // Tworzenie przycisku "Wyświetl wyniki"
        JButton btnWyswietlWyniki = new JButton("Wyświetl wyniki");
        btnWyswietlWyniki.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                projekt.pobierzTabliceWynikow().wyswietlWynikWszystkie();
            }
        });
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        panel.add(btnWyswietlWyniki, gbc);

        // Tworzenie przycisku "Wyjście z gry"
        JButton btnWyjscie = new JButton("Wyjście z gry");
        btnWyjscie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Zamyka aplikację
                System.exit(0);
            }
        });
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        panel.add(btnWyjscie, gbc);

        add(panel);
        setVisible(true); // Ustawienie okna jako widoczne
    }

    public static void main(String[] args) {
        new MainFrame(); // Uruchomienie głównego okna aplikacji
    }
}
