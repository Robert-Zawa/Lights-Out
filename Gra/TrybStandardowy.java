package gra;

public class TrybStandardowy {
    private Projekt projekt; // Prywatne pole przechowujące obiekt Projekt

    // Konstruktor klasy TrybStandardowy inicjalizujący obiekt Projekt
    public TrybStandardowy(Projekt projekt) {
        this.projekt = projekt;
    }

    // Metoda rozgrywka zarządzająca przebiegiem gry
    public void rozgrywka() {
        int wiersz, kolumna, liczbaRuchow = 0, liczbaPunktow = 0;
        projekt.resetPlanszy(); // Resetowanie planszy do stanu początkowego
        int size = projekt.getRozmiarPlanszy(); // Pobranie rozmiaru planszy
        Plansza plansza = projekt.getPlansza(); // Pobranie obiektu Plansza
        
        // Pętla gry trwa, dopóki liczba punktów jest mniejsza niż maksymalna liczba możliwych punktów
        while (liczbaPunktow < size * size) {
            ZarzadzaniePlansza.wyswietlPlansze(plansza, size); // Wyświetlenie planszy
            System.out.print("Podaj wiersz oraz kolumnę (1-" + size + ") lub 'q' aby wyjść: ");
            String input = Menu.scanner.next(); // Odczytanie wejścia użytkownika
    
            // Sprawdzenie, czy użytkownik chce zakończyć grę
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Zakończono grę.");
                return;
            }
    
            try {
                wiersz = Integer.parseInt(input); // Przekształcenie pierwszej części wejścia na liczbę całkowitą
                kolumna = Menu.scanner.nextInt(); // Odczytanie drugiej części wejścia jako liczby całkowitej
            } catch (NumberFormatException e) {
                // Obsługa błędu w przypadku nieprawidłowych danych wejściowych
                System.out.println("Nieprawidłowe dane wejściowe. Spróbuj ponownie.");
                Menu.scanner.nextLine(); // Oczyszczenie bufora skanera
                continue;
            }
    
            // Sprawdzenie, czy podane współrzędne mieszczą się w zakresie planszy
            if (wiersz < 1 || wiersz > size || kolumna < 1 || kolumna > size) {
                System.out.println("Podano złe koordynaty. Spróbuj ponownie.");
                continue;
            }
    
            // Wypełnienie planszy na podstawie podanych współrzędnych
            ZarzadzaniePlansza.wypelnijPlansze(plansza, wiersz, kolumna, size);
            liczbaPunktow = ZarzadzaniePlansza.zliczPunkty(plansza, size); // Zliczenie punktów
            liczbaRuchow++; // Inkrementacja liczby ruchów
            ZarzadzaniePlansza.wyswietlStatystyki(liczbaPunktow, liczbaRuchow, size); // Wyświetlenie statystyk
    
            // Sprawdzenie, czy plansza jest całkowicie wypełniona
            if (liczbaPunktow == size * size) {
                System.out.println("Brawo zapelniles cala plansze!\nZrobiłeś to w: " + liczbaRuchow + " ruchach.");
                System.out.print("Podaj swoje imię: ");
                String playerName = Menu.scanner.next(); // Odczytanie imienia gracza
                projekt.pobierzTabliceWynikow().dodajWynik(playerName, size, liczbaRuchow); // Dodanie wyniku do tablicy wyników
                break; // Zakończenie pętli gry
            }
        }
    }
}
