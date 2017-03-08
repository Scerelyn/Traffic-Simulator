package tstopsim.Roads;

import java.util.ArrayList;
import tstopsim.Visual.*;

public class NonDrivableRoadTile extends RoadTile{

    public NonDrivableRoadTile(double x, double y) {
        super(x, y, Direction.NORTH);
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        components.add( new ColoredRectangle2D(xPos,yPos,RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION,SIDE_WALK_COLOR) );
        return components;
    }

}
