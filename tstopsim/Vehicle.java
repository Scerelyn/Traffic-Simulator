package tstopsim;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Vehicle implements Visualizable {
    public Vehicle(){
        xPos=0;
        yPos=0;
        this.parts = assemble();
    }
    
    public static final int SIDE_LENGTH = (int)(RoadTile.ROAD_DIMENTION*0.3);
    double xPos,yPos;
    private Direction dir;
    private ArrayList<Rectangle2D> parts;
    
    @Override
    public ArrayList<Rectangle2D> assemble() {
        ArrayList<Rectangle2D> components = new ArrayList<>();
        Rectangle2D chassis = new Rectangle2D.Double(0,0,SIDE_LENGTH,SIDE_LENGTH); 
        components.add(chassis);
        components.add(new Rectangle2D.Double(xPos, xPos, yPos + 0.2*SIDE_LENGTH, yPos + 0.2*SIDE_LENGTH)); //top left
        components.add(new Rectangle2D.Double(xPos + 0.8*SIDE_LENGTH, xPos, yPos + 0.2*SIDE_LENGTH, yPos + 0.2*SIDE_LENGTH)); // top right
        components.add(new Rectangle2D.Double(xPos, xPos + 0.8*SIDE_LENGTH, yPos + 0.2*SIDE_LENGTH, yPos + 0.2*SIDE_LENGTH)); //bottom right
        components.add(new Rectangle2D.Double(xPos + 0.8*SIDE_LENGTH, xPos + 0.8*SIDE_LENGTH, yPos + 0.2*SIDE_LENGTH, yPos + 0.2*SIDE_LENGTH)); //bottom left
        
        return components;
    }
    
    @Override
    public ArrayList<Rectangle2D> getParts(){
        return this.parts;
    }
    
    @Override
    public int getPriorityInt(){
        return 1;
    }
}
