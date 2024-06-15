package graGUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TablicaWynikow {
     // Mapa przechowująca listy wyników dla poszczególnych rozmiarów planszy
    private Map<Integer, List<Wynik>> wynikiMap;

    public TablicaWynikow() {
        // Konstruktor klasy, inicjalizuje mapę wyników i wczytuje wyniki z pliku
        wynikiMap = new HashMap<>();
        wczytajWyniki();
    }

    // Dodaje wynik do odpowiedniej listy wyników dla danego rozmiaru planszy
    public void dodajWynik(String nazwaGracz, int rozmiarPlanszy, int ruchy) {
        if (rozmiarPlanszy == 0) {
            // Specjalna obsługa dla trybu rosnącej planszy
            List<Wynik> wyniki = wynikiMap.getOrDefault(rozmiarPlanszy, new ArrayList<>());
            
            wyniki.add(new Wynik(nazwaGracz, ruchy));
            wynikiMap.put(rozmiarPlanszy, wyniki);
        } else {
            List<Wynik> wyniki = wynikiMap.getOrDefault(rozmiarPlanszy, new ArrayList<>());
            boolean found = false;
            for (Wynik wynik : wyniki) {
                if (wynik.nazwaGracz.equals(nazwaGracz)) {
                    found = true;
                    if (ruchy < wynik.ruchy) {
                        wynik.ruchy = ruchy;
                    }
                    break;
                }
            }
            if (!found) {
                wyniki.add(new Wynik(nazwaGracz, ruchy));
            }
            wynikiMap.put(rozmiarPlanszy, wyniki);
        }
        zapiszWyniki();
    }

    // Wyświetla wyniki dla danego rozmiaru planszy
    public void wyswietlWynik(int rozmiarPlanszy) {
        List<Wynik> wyniki = wynikiMap.get(rozmiarPlanszy);
        if (wyniki == null || wyniki.isEmpty()) {
            String message = rozmiarPlanszy == 0 ?
                    "Brak zapisanych wyników dla trybu rosnącej planszy." :
                    "Brak zapisanych wyników dla rozmiaru " + rozmiarPlanszy + "x" + rozmiarPlanszy + ".";
            JOptionPane.showMessageDialog(null, message);
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (rozmiarPlanszy == 0) {
            sb.append("Tablica Wyników dla trybu rosnącej planszy:\n");
        } else {
            sb.append("Tablica Wyników dla planszy ").append(rozmiarPlanszy).append("x").append(rozmiarPlanszy).append(":\n");
        }
        for (Wynik score : wyniki) {
            sb.append(score).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Wyniki", JOptionPane.INFORMATION_MESSAGE);
    }

    // Wyświetla wszystkie wyniki dla wszystkich rozmiarów planszy
    public void wyswietlWynikWszystkie() {
        if (wynikiMap.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Brak zapisanych wyników.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Tablica Wyników:\n");
        for (Map.Entry<Integer, List<Wynik>> entry : wynikiMap.entrySet()) {
            int rozmiarPlanszy = entry.getKey();
            List<Wynik> wyniki = entry.getValue();
            if (rozmiarPlanszy == 0) {
                sb.append("Wyniki dla trybu rosnącej planszy:\n");
            } else {
                sb.append("Wyniki dla planszy ").append(rozmiarPlanszy).append("x").append(rozmiarPlanszy).append(":\n");
            }
            for (Wynik score : wyniki) {
                sb.append(score).append("\n");
            }
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Wyniki", JOptionPane.INFORMATION_MESSAGE);
    }

    // Klasa wewnętrzna reprezentująca wynik
    private static class Wynik {
        private String nazwaGracz;
        private int ruchy;

         // Konstruktor klasy Wynik
        public Wynik(String nazwaGracz, int ruchy) {
            this.nazwaGracz = nazwaGracz;
            this.ruchy = ruchy;
        }

        @Override
        public String toString() {
            return "Gracz: " + nazwaGracz + ", Liczba ruchów: " + ruchy;
        }
    }

    // Zapisuje wyniki do pliku
    public void zapiszWyniki() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("wyniki.txt"))) {
            for (Map.Entry<Integer, List<Wynik>> entry : wynikiMap.entrySet()) {
                int rozmiarPlanszy = entry.getKey();
                List<Wynik> wyniki = entry.getValue();
                // Zapisuje każdy wynik do pliku
                for (Wynik score : wyniki) {
                    String record = rozmiarPlanszy + "," + score.nazwaGracz + "," + score.ruchy;
                    writer.write(record);
                    writer.newLine();
                }
            }
            JOptionPane.showMessageDialog(null, "Wyniki zostały zapisane do pliku wyniki.txt.");
        } catch (IOException e) {
            // Obsługuje błędy wejścia/wyjścia
            JOptionPane.showMessageDialog(null,"Wystąpił błąd podczas zapisywania wyników.");
            System.out.println("Wystąpił błąd podczas zapisywania wyników.");
            e.printStackTrace();
        }
    }

    // Wczytuje wyniki z pliku
    private void wczytajWyniki() {
        try (BufferedReader reader = new BufferedReader(new FileReader("wyniki.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Sprawdza poprawność formatu linii
                if (parts.length == 3) {
                    try {
                        int rozmiarPlanszy = Integer.parseInt(parts[0]);
                        String nazwaGracz = parts[1];
                        int ruchy = Integer.parseInt(parts[2]);
                        List<Wynik> wyniki = wynikiMap.getOrDefault(rozmiarPlanszy, new ArrayList<>());
                        wyniki.add(new Wynik(nazwaGracz, ruchy));
                        wynikiMap.put(rozmiarPlanszy, wyniki);
                    } catch (NumberFormatException e) {
                        // Obsługuje błędy formatu danych
                        System.err.println("Invalid format in line: " + line);
                    }
                } else {
                    System.err.println("Incorrect number of parts in line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            // Obsługuje przypadek braku pliku wyników
            System.out.println("Nie znaleziono pliku wyniki.txt");
        } catch (IOException e) {
            // Obsługuje błędy wejścia/wyjścia
            JOptionPane.showMessageDialog(null, "Wystąpił błąd podczas wczytywania wyników.");
            System.out.println("Wystąpił błąd podczas wczytywania wyników.");
            e.printStackTrace();
        }
    }
}
