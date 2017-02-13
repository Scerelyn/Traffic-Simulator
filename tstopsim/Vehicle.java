package tstopsim;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Vehicle implements Visualizable {
    public Vehicle(){
        this.parts = assemble();
    }
    
    public static final int SIDE_LENGTH = (int)(RoadTile.ROAD_DIMENTION*0.3);
    private Direction dir;
    private ArrayList<Rectangle2D> parts;
    @Override
    public ArrayList<Rectangle2D> assemble() {
        ArrayList<Rectangle2D> parts = new ArrayList<>();
        Rectangle2D chassis = new Rectangle2D.Double(0,0,SIDE_LENGTH,SIDE_LENGTH); 
        parts.add(chassis);
        parts.add(new Rectangle2D.Double(0, 0, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH)); //top left
        parts.add(new Rectangle2D.Double(0.8*SIDE_LENGTH, 0, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH)); // top right
        parts.add(new Rectangle2D.Double(0, 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH)); //bottom right
        parts.add(new Rectangle2D.Double(0.8*SIDE_LENGTH, 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH)); //bottom left
        
        return parts;
    }
    
    @Override
    public ArrayList<Rectangle2D> getParts(){
        return this.parts;
    }
}
