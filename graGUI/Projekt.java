package graGUI;

import javax.swing.JOptionPane;

public class Projekt {
    // Klasa reprezentująca projekt gry, zawierająca planszę do gry, rozmiar planszy i tablicę wyników
    private Plansza plansza;
    private int size;
    private TablicaWynikow tablicaWynikow;

    // Metoda pobierająca tablicę wyników
    public TablicaWynikow pobierzTabliceWynikow() {
        return this.tablicaWynikow;
    }

    // Metoda resetująca planszę do gry
    public void resetPlanszy() {
        plansza = new Plansza(this.size);
    }

    // Domyślny konstruktor klasy Projekt, inicjalizujący planszę o rozmiarze 9x9 i tablicę wyników
    public Projekt() {
        this.size = Main.SIZEMAX;
        this.plansza = new Plansza(this.size);
        this.tablicaWynikow = new TablicaWynikow();
    }

    // Konstruktor klasy Projekt z parametrem tablicaWynikow, inicjalizujący domyślnie planszę o rozmiarze 9x9
    public Projekt(TablicaWynikow tablicaWynikow) {
        this();
        this.tablicaWynikow = tablicaWynikow;
    }

    public void setSize(int size){
        this.size = size;
    }

    // Metoda ustawiająca rozmiar planszy do gry
    public void ustawRozmiarPlanszy(int size) {
        if (size < 2 || size > 9) {
            // Jeśli podany rozmiar jest mniejszy niż 2, ustawiamy rozmiar planszy na 2x2
            JOptionPane.showMessageDialog(null, "Nie ustawić można takiego rozmiaru.");
            this.size = 2;
            this.plansza = new Plansza(2);
        } else {
            // Ustawiamy planszę na podany rozmiar
            this.size = size;
            this.plansza = new Plansza(size);
        }
    }

    // Metoda zwracająca aktualny rozmiar planszy
    public int getRozmiarPlanszy() {
        return this.size;
    }

    // Metoda zwracająca obiekt planszy
    public Plansza getPlansza() {
        return this.plansza;
    }
}
