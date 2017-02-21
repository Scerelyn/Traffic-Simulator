package tstopsim;

import java.awt.Color;
import java.util.ArrayList;

public class NonDrivableRoadTile extends RoadTile{

    public NonDrivableRoadTile(double x, double y) {
        super(x, y, Direction.NORTH);
        this.carSpots = null;
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        components.add( new ColoredRectangle2D(xPos,yPos,RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION,Color.LIGHT_GRAY) );
        return components;
    }

}
