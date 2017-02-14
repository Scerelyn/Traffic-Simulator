package tstopsim;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Light implements Visualizable{
    private ArrayList<Rectangle2D> parts;
    
    
    @Override
    public ArrayList<Rectangle2D> assemble() {
        ArrayList<Rectangle2D> components = new ArrayList<>();
        
        return components;
    }

    @Override
    public ArrayList<Rectangle2D> getParts() {
        return this.parts;
    }

    @Override
    public int getPriorityInt() {
        return 2;
    }
    
    

}
