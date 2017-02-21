package tstopsim;

import java.awt.geom.*;
import java.util.*;

public abstract class RoadTile implements Visualizable{
    //road 80%, sidewalk 20%. sidewalk 10% road 80% sidewalk 10%
    public static final int ROAD_DIMENTION = 200; //pixels side, length and width of the tile. This is the only given constant, so everything else scales
    protected ArrayList<ColoredRectangle2D> parts;
    protected Vehicle[][] carSpots = new Vehicle[2][2]; 
    protected double xPos,yPos;
    protected Direction dir;
    @Override
    public ArrayList<ColoredRectangle2D> getParts() {
        return this.parts;
    }
    
    public void setBounds(double x, double y){
        this.xPos = x;
        this.yPos = y;
        this.parts = assemble();
    }
}
