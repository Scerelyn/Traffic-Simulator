package tstopsim.Roads;

import java.awt.Color;
import java.util.ArrayList;
import tstopsim.Visual.*;

public class NonDrivableRoadTile extends RoadTile{

    public NonDrivableRoadTile(double x, double y) {
        super(x, y, Direction.NORTH);
    }
    
    public NonDrivableRoadTile(double x, double y, Direction d) {
        super(x, y, d);
    }
    
    public NonDrivableRoadTile(double x, double y, Direction d, Color c1, Color c2) {
        super(x, y, d, c1, c2);
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        components.add( new ColoredRectangle2D(xPos,yPos,RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION,SIDE_WALK_COLOR) );
        return components;
    }

}
