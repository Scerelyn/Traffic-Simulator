package tstopsim;

import java.awt.geom.*;
import java.util.*;

public abstract class RoadTile implements Visualizable{
    //road 80%, sidewalk 20%. sidewalk 10% road 80% sidewalk 10%
    public static final int ROAD_DIMENTION = 100; //pixels side, length and width of the tile. This is the only given constant, so everything else scales
    
    protected ArrayList<ColoredRectangle2D> parts;
    protected Vehicle[][] carSpots = new Vehicle[2][2]; //{ north{west, east}, south{west, east}}
    protected ArrayList<Light> lights = new ArrayList<>();
    protected double xPos,yPos;
    protected Direction dir;
    
    public RoadTile(double x, double y, Direction dir){
        setBounds(x,y,dir);
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> getParts() {
        return this.parts;
    }
    
    /**
     * Sets the bounds to a specified point and direction, which in turn
     * rebuilds all parts within the roadtile and lights if present
     *
     * @param x The x coordinate to move to
     * @param y The y coordinate to move to
     * @param dir The direction to face
     */
    public final void setBounds(double x, double y, Direction dir){
        this.xPos = x;
        this.yPos = y;
        this.dir = dir;
        this.parts = assemble();
        setupLights();
    }
    
    /**
     * Adds lights to the lights ArrayList. Left as empty but not needed to be
     * overridden since only some road tiles need lights
     */
    public void setupLights(){}
    
    @Override
    public int getPriorityInt() {
        return 0;
    }
    
    @Override
    public void rebuild(){
        this.parts = assemble();
    }
    
    public Vehicle[][] getCarSpots() {
        return carSpots;
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public Direction getDir() {
        return dir;
    }
    
    public Light getLightByDirection(Direction nDir){
        for(Light l : this.lights){
            if(l.getDir() == nDir){
                return l;
            }
        }
        return null;
    }
}
