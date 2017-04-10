package tstopsim.Roads;

import java.awt.Color;
import java.util.ArrayList;
import tstopsim.Visual.ColoredRectangle2D;
import tstopsim.Visual.Direction;


public class ParkingLotRoadTile extends NonDrivableRoadTile{
    
    private double car1Prob = Math.random(), car2Prob = Math.random();
    private Color car1Color = new Color( (int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256) );
    private Color car2Color = new Color( (int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256) );
    
    public ParkingLotRoadTile(double x, double y, Direction dir) {
        super(x, y, dir);
    }

    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D mainPlate = new ColoredRectangle2D(xPos,yPos,RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION,ROAD_COLOR);
        components.add(mainPlate);
       switch(this.dir){
           case NORTH:
           case SOUTH:
           case EAST:
           case WEST:
       } 
        
        
        return components;
    }

    

}
