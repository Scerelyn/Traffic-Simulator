package tstopsim;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.JComponent;

public class Map extends JComponent {

    private ArrayList<Visualizable> components;

    public Map(ArrayList<Visualizable> arr) {
        this.components = arr;
        components.sort((v1, v2) -> {
            return Integer.compare(v1.getPriorityInt(), v2.getPriorityInt());
        });
    }
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        for(Visualizable v : components){
            for(Rectangle2D rect : v.getParts()){
                g2.setPaint( new Color( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) ) );
                g2.fill(rect);
            }
        }
    }
}
