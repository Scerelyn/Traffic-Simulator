package tstopsim.Roads;

import java.awt.Color;
import java.util.ArrayList;
import tstopsim.Visual.*;

public class StraightRoadTile extends RoadTile{

    public StraightRoadTile(double xPos, double yPos, Direction dir, boolean hasSidewalk) {
        super(xPos, yPos, dir, hasSidewalk);
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D mainPlate = new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION,ROAD_COLOR);
        components.add(mainPlate);
        switch(dir){
            case NORTH:
            case SOUTH:
                if(hasSidewalk) {
                    components.add(new ColoredRectangle2D(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION,SIDE_WALK_COLOR));
                    components.add(new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION,SIDE_WALK_COLOR));
                }
                components.add(new ColoredRectangle2D(xPos+0.475*RoadTile.ROAD_DIMENTION, yPos+0.1*RoadTile.ROAD_DIMENTION, 0.05*RoadTile.ROAD_DIMENTION,0.2*RoadTile.ROAD_DIMENTION,RoadTile.MIDLANE_PAINT)); //northern paint line
                components.add(new ColoredRectangle2D(xPos+0.475*RoadTile.ROAD_DIMENTION, yPos+0.6*RoadTile.ROAD_DIMENTION, 0.05*RoadTile.ROAD_DIMENTION,0.2*RoadTile.ROAD_DIMENTION,RoadTile.MIDLANE_PAINT)); //southern paint line
                break;
            case EAST:
            case WEST:
                if(hasSidewalk){
                    components.add(new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION,SIDE_WALK_COLOR));
                    components.add(new ColoredRectangle2D(xPos, yPos+0.9*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION,SIDE_WALK_COLOR));
                }
                components.add(new ColoredRectangle2D(xPos+0.1*RoadTile.ROAD_DIMENTION, yPos+0.475*RoadTile.ROAD_DIMENTION, 0.2*RoadTile.ROAD_DIMENTION,0.05*RoadTile.ROAD_DIMENTION,RoadTile.MIDLANE_PAINT)); //western paint line
                components.add(new ColoredRectangle2D(xPos+0.6*RoadTile.ROAD_DIMENTION, yPos+0.475*RoadTile.ROAD_DIMENTION, 0.2*RoadTile.ROAD_DIMENTION,0.05*RoadTile.ROAD_DIMENTION,RoadTile.MIDLANE_PAINT)); //eastern paint line
                break;
            default:
                System.out.println("Something broke");
        }
        return components;
    }
    
}
