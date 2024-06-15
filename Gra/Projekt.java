package gra;

public class Projekt {
    private Plansza plansza; // Prywatne pole przechowujące obiekt Plansza
    private int size; // Prywatne pole przechowujące rozmiar planszy
    private TablicaWynikow TablicaWynikow; // Prywatne pole przechowujące obiekt TablicaWynikow

    // Metoda zwracająca obiekt TablicaWynikow
    public TablicaWynikow pobierzTabliceWynikow() {
        return this.TablicaWynikow;
    }

    // Metoda resetująca planszę do nowej instancji z bieżącym rozmiarem
    public void resetPlanszy() {
        plansza = new Plansza(this.size);
    }

    // Konstruktor domyślny inicjalizujący pola domyślnymi wartościami
    public Projekt() {
        this.size = 5; // Domyślny rozmiar planszy to 5
        this.plansza = new Plansza(this.size); // Inicjalizacja planszy o rozmiarze 5
        this.TablicaWynikow = new TablicaWynikow(); // Inicjalizacja nowej TablicaWynikow
    }

    // Konstruktor przyjmujący TablicaWynikow jako parametr i inicjalizujący pola domyślnymi wartościami
    public Projekt(TablicaWynikow TablicaWynikow) {
        this(); // Wywołanie konstruktora domyślnego
        this.TablicaWynikow = TablicaWynikow; // Ustawienie przekazanego TablicaWynikow
    }

    // Metoda ustawiająca rozmiar planszy, sprawdzająca czy rozmiar jest nie mniejszy niż 2x2
    public void ustawRozmiarPlanszy(int size) {
        if (size < 2) {
            // Wiadomość informująca, że minimalny rozmiar planszy to 2x2
            System.out.println("Nie można ustawić rozmiaru mniejszego niż 2x2.");
            System.out.println("Rozmiar planszy zostanie automatycznie ustawiony na 2x2.");
            this.size = 2; // Ustawienie rozmiaru planszy na minimalny dozwolony rozmiar 2x2
            this.plansza = new Plansza(2); // Inicjalizacja nowej planszy o rozmiarze 2x2
        } else {
            this.size = size; // Ustawienie nowego rozmiaru planszy
            this.plansza = new Plansza(size); // Inicjalizacja nowej planszy o zadanym rozmiarze
        }
    }

    // Metoda zwracająca aktualny rozmiar planszy
    public int getRozmiarPlanszy() {
        return this.size;
    }
    
    // Metoda zwracająca obiekt Plansza
    public Plansza getPlansza() {
        return this.plansza;
    }
}
