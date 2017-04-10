package tstopsim.Roads;

import java.awt.Color;
import java.util.ArrayList;
import tstopsim.Visual.ColoredRectangle2D;
import tstopsim.Visual.Direction;

public class DecorativeCenterRoadTile extends NonDrivableRoadTile {
    
    public DecorativeCenterRoadTile(double x, double y, Direction dir, Color c) {
        super(x, y, dir, c);
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        components.add( new ColoredRectangle2D(xPos,yPos,RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION,specialColor) );
        return components;
    }
}
