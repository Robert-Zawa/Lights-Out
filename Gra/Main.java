package gra;

public class Main {
    // Metoda main - punkt wejścia do programu
    public static void main(String[] args) {
        // Tworzenie obiektu Projekt
        Projekt projekt = new Projekt();
        // Tworzenie obiektu Menu i przekazanie do niego obiektu Projekt
        Menu menu = new Menu(projekt);
        // Wywołanie metody pokazMenu(), która wyświetla menu gry
        menu.pokazMenu();
    }
}
