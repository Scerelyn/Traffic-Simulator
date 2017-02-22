package tstopsim;

import java.awt.geom.*;
import java.util.*;

public abstract class RoadTile implements Visualizable{
    //road 80%, sidewalk 20%. sidewalk 10% road 80% sidewalk 10%
    public static final int ROAD_DIMENTION = 100; //pixels side, length and width of the tile. This is the only given constant, so everything else scales
    protected ArrayList<ColoredRectangle2D> parts;
    protected Vehicle[][] carSpots = new Vehicle[2][2]; 
    protected ArrayList<Light> lights = new ArrayList<>();
    protected double xPos,yPos;
    protected Direction dir;
    @Override
    public ArrayList<ColoredRectangle2D> getParts() {
        return this.parts;
    }
    
    public RoadTile(double x, double y, Direction dir){
        setBounds(x,y,dir);
    }
    
    public void setBounds(double x, double y, Direction dir){
        this.xPos = x;
        this.yPos = y;
        this.dir = dir;
        this.parts = assemble();
        setupLights();
    }
    
    public void setupLights(){}
    
    @Override
    public int getPriorityInt() {
        return 0;
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
    
}
