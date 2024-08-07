package graGUI;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

public class Przycisk extends JButton{
    public Przycisk(String label){
    super(label);
    Dimension size = getPreferredSize();
    size.width = size.height = Math.max(size.width, size.height);
    setPreferredSize(size);
    setContentAreaFilled(false);
    }
    int n=5;
    int x[]= new int[n];
    int y[]= new int[n];
    double angle = 2*Math.PI/n;

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(Color.GREEN);
        }
        int xPoints[] = {0, 0, getWidth()};
        int yPoints[] = {0, getHeight(), getHeight()/2};
        g.fillPolygon(xPoints, yPoints, xPoints.length);
        super.paintComponent(g);
    }
     
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        int xPoints[] = {0, 0, getWidth()};
        int yPoints[] = {0, getHeight(), getHeight()/2};
        g.drawPolygon(xPoints, yPoints, xPoints.length);
    }
     
    Polygon polygon;
    public boolean contains(int x, int y) {
        if (polygon == null ||
                !polygon.getBounds().equals(getBounds())) {
            int xPoints[] = {0, 0, getWidth()};
            int yPoints[] = {0, getHeight(), getHeight()/2};
            polygon = new Polygon(xPoints,yPoints,xPoints.length);
        }
        return polygon.contains(x, y);
    }
}
