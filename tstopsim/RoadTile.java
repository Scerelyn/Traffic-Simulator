package tstopsim;

import java.awt.geom.*;
import java.util.*;

public abstract class RoadTile implements Visualizable{
    //road 80%, sidewalk 20%. sidewalk 10% road 80% sidewalk 10%
    public static final int ROAD_DIMENTION = 200; //pixels side, length and width of the tile. This is the only given constant, so everything else scales
    protected ArrayList<ColoredRectangle2D> parts;
    protected Vehicle[][] carSpots = new Vehicle[2][2]; 
    protected Light[] lights = new Light[4];
    protected double xPos,yPos;
    protected Direction dir;
    @Override
    public ArrayList<ColoredRectangle2D> getParts() {
        return this.parts;
    }
    
    public RoadTile(double x, double y, Direction dir){
        this.xPos = x;
        this.yPos = y;
        this.dir = dir;
        this.parts = assemble();
    }
    
    public void setBounds(double x, double y, Direction dir){
        this.xPos = x;
        this.yPos = y;
        this.dir = dir;
        this.parts = assemble();
    }
    
    @Override
    public int getPriorityInt() {
        return 0;
    }

    public Vehicle[][] getCarSpots() {
        return carSpots;
    }

    public void setCarSpots(Vehicle[][] carSpots) {
        this.carSpots = carSpots;
    }

    public Light[] getLights() {
        return lights;
    }

    public void setLights(Light[] lights) {
        this.lights = lights;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
    
    
}
