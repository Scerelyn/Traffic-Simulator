package tstopsim;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.Color;

public class Vehicle implements Visualizable {
    public Vehicle(){
        xPos=0;
        yPos=0;
        this.parts = assemble();
    }
    
    public static final int SIDE_LENGTH = (int)(RoadTile.ROAD_DIMENTION*0.3);
    private double xPos,yPos;
    private Direction dir;
    private ArrayList<ColoredRectangle2D> parts;
    
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D chassis = new ColoredRectangle2D(0,0,SIDE_LENGTH,SIDE_LENGTH,Color.CYAN); 
        components.add(chassis);
        components.add(new ColoredRectangle2D(xPos, xPos, yPos + 0.2*SIDE_LENGTH, yPos + 0.2*SIDE_LENGTH,Color.WHITE)); //top left
        components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, xPos, yPos + 0.2*SIDE_LENGTH, yPos + 0.2*SIDE_LENGTH,Color.WHITE)); // top right
        components.add(new ColoredRectangle2D(xPos, xPos + 0.8*SIDE_LENGTH, yPos + 0.2*SIDE_LENGTH, yPos + 0.2*SIDE_LENGTH,Color.WHITE)); //bottom right
        components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, xPos + 0.8*SIDE_LENGTH, yPos + 0.2*SIDE_LENGTH, yPos + 0.2*SIDE_LENGTH,Color.WHITE)); //bottom left
        return components;
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> getParts(){
        return this.parts;
    }
    
    @Override
    public int getPriorityInt(){
        return 1;
    }
}
