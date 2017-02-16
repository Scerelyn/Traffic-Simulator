package tstopsim;

import java.awt.Color;
import java.awt.geom.Rectangle2D;


public class ColoredRectangle2D{
    Rectangle2D rect;
    Color color;

    public ColoredRectangle2D(double x, double y, double width, double height, Color c){
        this.rect = new Rectangle2D.Double(x, y, width, height);
        this.color = c;
    }
    
    public Rectangle2D getRect() {
        return rect;
    }

    public void setRect(Rectangle2D rect) {
        this.rect = rect;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
}
