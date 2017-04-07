package tstopsim.Roads;

import java.awt.Color;
import java.util.ArrayList;
import static tstopsim.Roads.RoadTile.ROAD_COLOR;
import tstopsim.Visual.ColoredRectangle2D;
import tstopsim.Visual.Direction;

public class DecorativeEdgeRoadTile extends DecorativeCenterRoadTile{
    
    public DecorativeEdgeRoadTile(double x, double y, Direction dir, Color c) {
        super(x, y, dir,c);
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D mainPlate = new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION,specialColor);
        components.add(mainPlate);
        switch(dir){
            case NORTH:
                components.add(new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION,SIDE_WALK_COLOR));
                break;
            case SOUTH:
                components.add(new ColoredRectangle2D(xPos, yPos+0.9*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION,SIDE_WALK_COLOR));
                break;
            case EAST:
                components.add(new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION,SIDE_WALK_COLOR));
                break;
            case WEST:
                components.add(new ColoredRectangle2D(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION,SIDE_WALK_COLOR));
                break;
            default:
                System.out.println("Something broke");
        }
        return components;
    }
    
}
