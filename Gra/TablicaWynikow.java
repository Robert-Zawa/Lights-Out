package gra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Interfejs Zapisywalny zawierający metodę do zapisywania wyników
interface Zapisywalny {
    void zapiszWyniki();
}

public class TablicaWynikow implements Zapisywalny {
    // Mapa przechowująca wyniki gry dla różnych rozmiarów plansz
    private Map<Integer, List<Wynik>> wynikiMap;

    // Konstruktor klasy TablicaWynikow
    public TablicaWynikow() {
        wynikiMap = new HashMap<>();
        // Wczytujemy wyniki z pliku przy tworzeniu nowej instancji klasy
        wczytajWyniki();
    }

    // Metoda dodająca nowy wynik do listy dla danego rozmiaru planszy
    public void dodajWynik(String nazwaGracz, int rozmiarPlanszy, int ruchy) {
        List<Wynik> wyniki = wynikiMap.getOrDefault(rozmiarPlanszy, new ArrayList<>());
        boolean found = false;

        // Szukamy wyniku dla tego gracza i tego rozmiaru planszy
        for (Wynik wynik : wyniki) {
            if (wynik.nazwaGracz.equals(nazwaGracz)) {
                found = true;
                // Jeżeli liczba ruchów jest mniejsza, aktualizujemy wynik
                if (ruchy < wynik.ruchy) {
                    wynik.ruchy = ruchy;
                }
                break;
            }
        }

        // Jeżeli nie znaleziono wyniku dla tego gracza i tego rozmiaru planszy, dodajemy nowy wynik
        if (!found) {
            wyniki.add(new Wynik(nazwaGracz, ruchy));
        }

        wynikiMap.put(rozmiarPlanszy, wyniki);
        zapiszWyniki(); // Zapisujemy wyniki po dodaniu nowego wyniku
    }

    // Metoda wyświetlająca wyniki dla danego rozmiaru planszy
    public void wyswietlWynik(int rozmiarPlanszy) {
        List<Wynik> wyniki = wynikiMap.get(rozmiarPlanszy);
        if (wyniki == null || wyniki.isEmpty()) {
            System.out.println("Brak zapisanych wyników dla rozmiaru " + rozmiarPlanszy + "x" + rozmiarPlanszy + ".");
            return;
        }

        System.out.println("Tablica Wyników dla planszy " + rozmiarPlanszy + "x" + rozmiarPlanszy + ":");
        for (Wynik score : wyniki) {
            System.out.println(score);
        }
    }

    // Metoda wyświetlająca wszystkie wyniki dla wszystkich rozmiarów plansz
    public void wyswietlWynikWszystkie() {
        if (wynikiMap.isEmpty()) {
            System.out.println("Brak zapisanych wyników.");
            return;
        }

        System.out.println("Tablica Wyników:");
        for (Map.Entry<Integer, List<Wynik>> entry : wynikiMap.entrySet()) {
            int rozmiarPlanszy = entry.getKey();
            List<Wynik> wyniki = entry.getValue();
            System.out.println("Wyniki dla planszy " + rozmiarPlanszy + "x" + rozmiarPlanszy + ":");
            for (Wynik score : wyniki) {
                System.out.println(score);
            }
            System.out.println();
        }   
    }

    // Klasa wewnętrzna reprezentująca pojedynczy wynik
    private static class Wynik {
        private String nazwaGracz;
        private int ruchy;

        public Wynik(String nazwaGracz, int ruchy) {
            this.nazwaGracz = nazwaGracz;
            this.ruchy = ruchy;
        }

        @Override
        public String toString() {
            return "Gracz: " + nazwaGracz + ", Liczba ruchów: " + ruchy;
        }
    }

    // Metoda do zapisywania wyników do pliku TXT
    @Override
    public void zapiszWyniki() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("wyniki.txt"))) {
            for (Map.Entry<Integer, List<Wynik>> entry : wynikiMap.entrySet()) {
                int rozmiarPlanszy = entry.getKey();
                List<Wynik> wyniki = entry.getValue();
                for (Wynik score : wyniki) {
                    String record = rozmiarPlanszy + "," + score.nazwaGracz + "," + score.ruchy;
                    writer.write(record);
                    writer.newLine();
                }
            }
            System.out.println("Wyniki zostały zapisane do pliku wyniki.txt.");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisywania wyników.");
            e.printStackTrace();
        }
    }

    // Metoda do wczytywania wyników z pliku TXT
    private void wczytajWyniki() {
        try (BufferedReader reader = new BufferedReader(new FileReader("wyniki.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int rozmiarPlanszy = Integer.parseInt(parts[0]);
                    String nazwaGracz = parts[1];
                    int ruchy = Integer.parseInt(parts[2]);
                    // Zamiast używać dodajWynik, bezpośrednio dodajemy do mapy
                    List<Wynik> wyniki = wynikiMap.getOrDefault(rozmiarPlanszy, new ArrayList<>());
                    wyniki.add(new Wynik(nazwaGracz, ruchy));
                    wynikiMap.put(rozmiarPlanszy, wyniki);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku wyniki.txt. Tworzę nowy plik.");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas wczytywania wyników.");
            e.printStackTrace();
        }
    }
}
