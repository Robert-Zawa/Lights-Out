package gra;

import java.util.Scanner;

public class Menu {
    public static final Scanner scanner = new Scanner(System.in); // Statyczny obiekt Scanner do odczytu danych wejściowych od użytkownika
    private Projekt projekt; // Prywatne pole przechowujące obiekt Projekt

    // Konstruktor klasy Menu inicjalizujący obiekt Projekt
    public Menu(Projekt projekt) {
        this.projekt = projekt;
    }

    // Metoda do czyszczenia terminala
    public void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Metoda wyświetlająca komunikat i oczekująca na naciśnięcie Enter przez użytkownika
    public void pressEnterToContinue() {
        System.out.println("Naciśnij Enter, aby kontynuować...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metoda wyświetlająca główne menu gry i obsługująca wybór opcji
    public void pokazMenu() {
        boolean exit = false; // Flaga do zakończenia pętli menu

        // Pętla głównego menu
        while (!exit) {
            clearTerminal();
            System.out.println("Menu Główne:");
            System.out.println("1. Zagraj");
            System.out.println("2. Zwiększająca się plansza");
            // System.out.println("3. Znajdź kształt");
            System.out.println("3. Ustaw rozmiar planszy (aktualny rozmiar: " + projekt.getRozmiarPlanszy() + "x" + projekt.getRozmiarPlanszy() + ")");
            System.out.println("4. Wyświetl wyniki");
            System.out.println("5. Wyjdź z gry");
            System.out.print("Wybierz opcję: ");

            try {
                int choice = Integer.parseInt(scanner.next()); // Odczytanie wyboru użytkownika

                switch (choice) {
                    case 1:
                        clearTerminal();
                        System.out.println("1. Zagraj");
                        new TrybStandardowy(projekt).rozgrywka(); // Rozpoczęcie standardowej rozgrywki
                        pressEnterToContinue();
                        break;
                    case 2:
                        clearTerminal();
                        System.out.println("2. Zwiększająca się plansza");
                        new TrybRosnacejPlanszy(projekt).rozgrywkaTryb2(); // Rozpoczęcie rozgrywki w trybie rosnącej planszy
                        pressEnterToContinue();
                        break;
                    // case 3:
                    //     clearTerminal();
                    //     System.out.println("Znajdź kształt.");
                    //     //
                    //     pressEnterToContinue();
                    //     break;
                    case 3:
                        clearTerminal();
                        System.out.println("Podaj rozmiar planszy (kwadrat, np. 5 dla 5x5 nie może być większy niż 9x9): ");
                        int newSize = Integer.parseInt(scanner.next()); // Odczytanie nowego rozmiaru planszy
                        projekt.ustawRozmiarPlanszy(newSize); // Ustawienie nowego rozmiaru planszy
                        break;
                    case 4:
                        clearTerminal();
                        wyswietlMenuWynikow(); // Wyświetlenie menu wyników
                        break;
                    case 5:
                        clearTerminal();
                        System.out.println("Wyjście z gry.");
                        exit = true; // Ustawienie flagi zakończenia pętli menu
                        break;
                    default:
                        clearTerminal();
                        System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
                        pressEnterToContinue();
                }
            } catch (NumberFormatException e) {
                clearTerminal();
                System.out.println("Nieprawidłowe dane wejściowe. Spróbuj ponownie.");
                pressEnterToContinue();
            }
        }
    }

    // Metoda wyświetlająca menu wyników i obsługująca wybór opcji
    private void wyswietlMenuWynikow() {
        while (true) {
            clearTerminal();
            System.out.println("Wyświetlanie wyników:");
            System.out.println("1. Dla konkretnego rozmiaru planszy");
            System.out.println("2. Dla wszystkich rozmiarów plansz");
            System.out.println("3. Powrót do menu głównego");
            System.out.print("Wybierz opcję: ");
            int wynikiChoice = scanner.nextInt(); // Odczytanie wyboru użytkownika
            switch (wynikiChoice) {
                case 1:
                    clearTerminal();
                    System.out.print("Podaj rozmiar planszy, dla którego chcesz wyświetlić wyniki: ");
                    int sizeToShow = scanner.nextInt(); // Odczytanie rozmiaru planszy dla wyników
                    clearTerminal();
                    projekt.pobierzTabliceWynikow().wyswietlWynik(sizeToShow); // Wyświetlenie wyników dla danego rozmiaru planszy
                    pressEnterToContinue();
                    break;
                case 2:
                    clearTerminal();
                    projekt.pobierzTabliceWynikow().wyswietlWynikWszystkie(); // Wyświetlenie wyników dla wszystkich rozmiarów plansz
                    pressEnterToContinue();
                    break;
                case 3:
                    return; // Powrót do menu głównego
                default:
                    clearTerminal();
                    System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
                    pressEnterToContinue();
            }
        }
    }
}
