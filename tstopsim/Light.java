package tstopsim;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

public class Light implements Visualizable{
    private ArrayList<ColoredRectangle2D> parts;
    private double xPos,yPos;
    private Direction dir; //this refers to which way the light faces, the opposite direction is the traffic flow this controls
    
    public Light(Direction dir){
        xPos=0;
        yPos=0;
        this.dir = dir;
        parts = assemble();
    }

    public Light(double xPos, double yPos, Direction dir) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dir = dir;
        this.parts = assemble();
    }
    
    
    
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D box;
        ColoredRectangle2D green;
        ColoredRectangle2D yellow;
        ColoredRectangle2D red;
        switch(this.dir){
            case SOUTH:
                box = new ColoredRectangle2D(xPos,yPos,0.3*RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION,Color.BLACK);
                green = new ColoredRectangle2D( 0.1*box.getRect().getWidth()+xPos,  yPos+box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(),Color.GREEN);
                yellow = new ColoredRectangle2D(0.4*box.getRect().getWidth()+xPos,  yPos+box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(),Color.YELLOW);
                red = new ColoredRectangle2D(   0.7*box.getRect().getWidth()+xPos,  yPos+box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(),Color.RED);
                break;
            case NORTH:
                box = new ColoredRectangle2D(xPos,yPos,0.3*RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION,Color.BLACK);
                green = new ColoredRectangle2D( 0.1*box.getRect().getWidth()+xPos,  yPos-0.2*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(),Color.GREEN);
                yellow = new ColoredRectangle2D(0.4*box.getRect().getWidth()+xPos,  yPos-0.2*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(),Color.YELLOW);
                red = new ColoredRectangle2D(   0.7*box.getRect().getWidth()+xPos,  yPos-0.2*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(),Color.RED);
                break;
            case EAST:
                box = new ColoredRectangle2D(xPos,yPos,0.1*RoadTile.ROAD_DIMENTION,0.3*RoadTile.ROAD_DIMENTION,Color.BLACK);
                green = new ColoredRectangle2D( box.getRect().getWidth()+xPos,  yPos+0.1*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(),Color.GREEN);
                yellow = new ColoredRectangle2D(box.getRect().getWidth()+xPos,  yPos+0.4*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(),Color.YELLOW);
                red = new ColoredRectangle2D(   box.getRect().getWidth()+xPos,  yPos+0.7*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(),Color.RED);
                break;
            case WEST:
                box = new ColoredRectangle2D(xPos,yPos,0.1*RoadTile.ROAD_DIMENTION,0.3*RoadTile.ROAD_DIMENTION,Color.BLACK);
                green = new ColoredRectangle2D( -0.2*box.getRect().getWidth()+xPos,  yPos+0.1*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(),Color.GREEN);
                yellow = new ColoredRectangle2D(-0.2*box.getRect().getWidth()+xPos,  yPos+0.4*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(),Color.YELLOW);
                red = new ColoredRectangle2D(   -0.2*box.getRect().getWidth()+xPos,  yPos+0.7*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(),Color.RED);
                break;
            default:
                throw new RuntimeException("Invalid Direction in assembly of: " + this);
        }
        components.add(box);
        components.add(green);
        components.add(red);
        components.add(yellow);
        return components;
    }

    @Override
    public ArrayList<ColoredRectangle2D> getParts() {
        return this.parts;
    }

    @Override
    public int getPriorityInt() {
        return 2;
    }
    
    

}
