package gra;

public class TrybRosnacejPlanszy {
    private Projekt projekt; // Prywatne pole przechowujące obiekt Projekt

    // Konstruktor klasy TrybRosnacejPlanszy inicjalizujący obiekt Projekt
    public TrybRosnacejPlanszy(Projekt projekt) {
        this.projekt = projekt;
    }

    // Metoda rozgrywkaTryb2 zarządzająca przebiegiem gry w trybie rosnącej planszy
    public void rozgrywkaTryb2() {
        int currentSize = 2; // Inicjalizacja początkowego rozmiaru planszy
        int liczbaRuchow = 0; // Inicjalizacja licznika ruchów
        
        // Pętla zwiększająca rozmiar planszy od 2 do 9
        while (currentSize <= 9) {
            projekt.ustawRozmiarPlanszy(currentSize); // Ustawienie aktualnego rozmiaru planszy
            Plansza plansza = projekt.getPlansza(); // Pobranie obiektu Plansza
            int liczbaPunktow = 0; // Inicjalizacja licznika punktów dla aktualnego rozmiaru
        
            // Pętla gry trwa, dopóki liczba punktów jest mniejsza niż maksymalna liczba możliwych punktów dla danego rozmiaru planszy
            while (liczbaPunktow < currentSize * currentSize) {
                ZarzadzaniePlansza.wyswietlPlansze(plansza, currentSize); // Wyświetlenie planszy
                System.out.print("Podaj wiersz oraz kolumnę (1-" + currentSize + ") lub 'q' aby wyjść: ");
                String input = Menu.scanner.next(); // Odczytanie wejścia użytkownika
        
                // Sprawdzenie, czy użytkownik chce zakończyć grę
                if (input.equalsIgnoreCase("q")) {
                    System.out.println("Zakończono grę.");
                    return;
                }
        
                int wiersz, kolumna; // Deklaracja zmiennych dla wiersza i kolumny
                
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
                if (wiersz < 1 || wiersz > currentSize || kolumna < 1 || kolumna > currentSize) {
                    System.out.println("Podano złe koordynaty. Spróbuj ponownie.");
                    continue;
                }
        
                // Wypełnienie planszy na podstawie podanych współrzędnych
                ZarzadzaniePlansza.wypelnijPlansze(plansza, wiersz, kolumna, currentSize);
                liczbaPunktow = ZarzadzaniePlansza.zliczPunkty(plansza, currentSize); // Zliczenie punktów
                liczbaRuchow++; // Inkrementacja liczby ruchów
                ZarzadzaniePlansza.wyswietlStatystyki(liczbaPunktow, liczbaRuchow, currentSize); // Wyświetlenie statystyk
        
                // Sprawdzenie, czy plansza jest całkowicie wypełniona
                if (liczbaPunktow == currentSize * currentSize) {
                    System.out.println("Brawo zapelniles cala plansze o rozmiarze " + currentSize + "x" + currentSize + "!");
                    System.out.print("Podaj swoje imię: ");
                    String playerName = Menu.scanner.next(); // Odczytanie imienia gracza
                    projekt.pobierzTabliceWynikow().dodajWynik(playerName, currentSize, liczbaRuchow); // Dodanie wyniku do tablicy wyników
                    currentSize++; // Zwiększenie rozmiaru planszy
                    break; // Zakończenie pętli gry dla aktualnego rozmiaru
                }
            }
        }
        System.out.println("Osiągnięto maksymalny rozmiar planszy 9x9."); // Informacja o osiągnięciu maksymalnego rozmiaru planszy
    }
}
