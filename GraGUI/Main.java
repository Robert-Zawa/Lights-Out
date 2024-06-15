package graGUI;

import javax.swing.SwingUtilities;

public class Main {
    // Maksymalna wielkość tablicy
    public static final int SIZEMAX = 9;
    public static void main(String[] args) {
        // Uruchamia główne okno aplikacji w wątku Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Tworzy i wyświetla główne okno aplikacji
                new MainFrame();
            }
        });
    }
}
