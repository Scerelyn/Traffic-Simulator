package tstopsim;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.Color;

public class Light implements Visualizable{
    private ArrayList<ColoredRectangle2D> parts;
    private double xPos,yPos;
    private Direction dir;
    
    public Light(){
        xPos=0;
        yPos=0;
        dir = Direction.NORTH;
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
        ColoredRectangle2D box = new ColoredRectangle2D(xPos,yPos,0.3*RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION,Color.BLACK);
        ColoredRectangle2D green = new ColoredRectangle2D(0.1*box.getRect().getWidth()+xPos,yPos+box.getRect().getHeight(),0.2*box.getRect().getWidth(),0.2*box.getRect().getHeight(),Color.GREEN);
        ColoredRectangle2D yellow = new ColoredRectangle2D(0.4*box.getRect().getWidth()+xPos,yPos+box.getRect().getHeight(),0.2*box.getRect().getWidth(),0.2*box.getRect().getHeight(),Color.YELLOW);
        ColoredRectangle2D red = new ColoredRectangle2D(0.7*box.getRect().getWidth()+xPos,yPos+box.getRect().getHeight(),0.2*box.getRect().getWidth(),0.2*box.getRect().getHeight(),Color.RED);
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
