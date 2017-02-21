package tstopsim;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.Color;

public class Vehicle implements Visualizable {
    public static final int SIDE_LENGTH = (int)(RoadTile.ROAD_DIMENTION*0.3);
    private double xPos,yPos;
    private Direction dir;
    private ArrayList<ColoredRectangle2D> parts;
    
    public Vehicle(){
        xPos=0;
        yPos=0;
        this.dir = Direction.NORTH;
        this.parts = assemble();
    }

    public Vehicle(double xPos, double yPos, Direction dir) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dir = dir;
        this.parts = assemble();
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D chassis = new ColoredRectangle2D(xPos,yPos,SIDE_LENGTH,SIDE_LENGTH,Color.CYAN); 
        components.add(chassis);
        
        switch(this.dir){
            case NORTH:
                components.add(new ColoredRectangle2D(xPos, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //top left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); // top right
                components.add(new ColoredRectangle2D(xPos, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); //bottom left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); //bottom right
                break;
            case EAST:
                components.add(new ColoredRectangle2D(xPos, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); //top left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); // top right
                components.add(new ColoredRectangle2D(xPos, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); //bottom left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //bottom right
                break;
            case WEST:
                components.add(new ColoredRectangle2D(xPos, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //top left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); // top right
                components.add(new ColoredRectangle2D(xPos, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //bottom left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); //bottom right
                break;
            case SOUTH:
                components.add(new ColoredRectangle2D(xPos, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); //top left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); // top right
                components.add(new ColoredRectangle2D(xPos, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //bottom left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //bottom right
                break;
            default:
                System.out.println("Invalid direction when assembling car " + this);
                components.add(new ColoredRectangle2D(xPos, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //top left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); // top right
                components.add(new ColoredRectangle2D(xPos, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.GREEN)); //bottom left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.MAGENTA)); //bottom right
        }
        
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
