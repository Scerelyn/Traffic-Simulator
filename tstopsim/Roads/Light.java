package tstopsim.Roads;

import java.util.ArrayList;
import java.awt.Color;
import tstopsim.Visual.*;

public class Light implements Visualizable{
    private ArrayList<ColoredRectangle2D> parts;
    private double xPos,yPos;
    private Direction dir; //this refers to which way the light faces, the opposite direction is the traffic flow this controls
    private int lightState = 0; //0-4 are green, 5-6 is yellow, 7-11 is red
    public final static int GREEN_LOWER = 0, GREEN_UPPER = 3, YELLOW_LOWER = 4, YELLOW_UPPER = 5, RED_LOWER = 6, RED_UPPER = 11;
    
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
    
    public Light(double xPos, double yPos, Direction dir, int lightState){
        this.xPos = xPos;
        this.yPos = yPos;
        this.dir = dir;
        this.lightState = lightState;
        this.parts = assemble();
    }
    
    //color of on/off
    //(lightState >= 0 && lightState <= 4) ? Color.RED : Color.RED.darker().darker()
    //(lightState >= 5 && lightState <= 6) ? Color.RED : Color.RED.darker().darker()
    //(lightState >= 7 && lightState <= 11) ? Color.RED : Color.RED.darker().darker()
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D box;
        ColoredRectangle2D green;
        ColoredRectangle2D yellow;
        ColoredRectangle2D red;
        switch(this.dir){
            
            //color of on/off. these are in the color of the colered rectangle
            //(lightState >= GREEN_LOWER && lightState <= GREEN_UPPER) ? Color.GREEN : Color.GREEN.darker().darker()
            //(lightState >= YELLOW_LOWER && lightState <= YELLOW_UPPER) ? Color.YELLOW : Color.YELLOW.darker().darker()
            //(lightState >= RED_LOWER && lightState <= RED_UPPER) ? Color.RED : Color.RED.darker().darker()
            case SOUTH:
                box = new ColoredRectangle2D(xPos,yPos,0.3*RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION,Color.BLACK);
                green = new ColoredRectangle2D( 0.1*box.getRect().getWidth()+xPos,  yPos+box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(), (lightState >= GREEN_LOWER && lightState <= GREEN_UPPER) ? Color.GREEN : Color.GREEN.darker().darker());
                yellow = new ColoredRectangle2D(0.4*box.getRect().getWidth()+xPos,  yPos+box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(), (lightState >= YELLOW_LOWER && lightState <= YELLOW_UPPER) ? Color.YELLOW : Color.YELLOW.darker().darker());
                red = new ColoredRectangle2D(   0.7*box.getRect().getWidth()+xPos,  yPos+box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(), (lightState >= RED_LOWER && lightState <= RED_UPPER) ? Color.RED : Color.RED.darker().darker());
                break;
            case NORTH:
                box = new ColoredRectangle2D(xPos,yPos,0.3*RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION,Color.BLACK);
                green = new ColoredRectangle2D( 0.1*box.getRect().getWidth()+xPos,  yPos-0.2*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(), (lightState >= GREEN_LOWER && lightState <= GREEN_UPPER) ? Color.GREEN : Color.GREEN.darker().darker());
                yellow = new ColoredRectangle2D(0.4*box.getRect().getWidth()+xPos,  yPos-0.2*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(), (lightState >= YELLOW_LOWER && lightState <= YELLOW_UPPER) ? Color.YELLOW : Color.YELLOW.darker().darker());;
                red = new ColoredRectangle2D(   0.7*box.getRect().getWidth()+xPos,  yPos-0.2*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(), (lightState >= RED_LOWER && lightState <= RED_UPPER) ? Color.RED : Color.RED.darker().darker());
                break;
            case EAST:
                box = new ColoredRectangle2D(xPos,yPos,0.1*RoadTile.ROAD_DIMENTION,0.3*RoadTile.ROAD_DIMENTION,Color.BLACK);
                green = new ColoredRectangle2D( box.getRect().getWidth()+xPos,  yPos+0.1*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(), (lightState >= GREEN_LOWER && lightState <= GREEN_UPPER) ? Color.GREEN : Color.GREEN.darker().darker());
                yellow = new ColoredRectangle2D(box.getRect().getWidth()+xPos,  yPos+0.4*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(), (lightState >= YELLOW_LOWER && lightState <= YELLOW_UPPER) ? Color.YELLOW : Color.YELLOW.darker().darker());
                red = new ColoredRectangle2D(   box.getRect().getWidth()+xPos,  yPos+0.7*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(), (lightState >= RED_LOWER && lightState <= RED_UPPER) ? Color.RED : Color.RED.darker().darker());
                break;
            case WEST:
                box = new ColoredRectangle2D(xPos,yPos,0.1*RoadTile.ROAD_DIMENTION,0.3*RoadTile.ROAD_DIMENTION,Color.BLACK);
                green = new ColoredRectangle2D( -0.2*box.getRect().getWidth()+xPos,  yPos+0.1*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(), (lightState >= GREEN_LOWER && lightState <= GREEN_UPPER) ? Color.GREEN : Color.GREEN.darker().darker());
                yellow = new ColoredRectangle2D(-0.2*box.getRect().getWidth()+xPos,  yPos+0.4*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(), (lightState >= YELLOW_LOWER && lightState <= YELLOW_UPPER) ? Color.YELLOW : Color.YELLOW.darker().darker());
                red = new ColoredRectangle2D(   -0.2*box.getRect().getWidth()+xPos,  yPos+0.7*box.getRect().getHeight(), 0.2*box.getRect().getWidth(),   0.2*box.getRect().getHeight(), (lightState >= RED_LOWER && lightState <= RED_UPPER) ? Color.RED : Color.RED.darker().darker());
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
    
    @Override
    public void rebuild(){
        this.parts = assemble();
    }
    
    /**
     * Changes the state of the light by incrementing lightState by one. This
     * method should be called by a TimerTask or any method that repeats
     */
    public void changeLightState(){
        this.lightState++;
        if(lightState > 11){
            this.lightState = 0;
        }
        rebuild();
    }
    
    public int getLightState(){
        return this.lightState;
    }

    public Direction getDir() {
        return dir;
    }
}
