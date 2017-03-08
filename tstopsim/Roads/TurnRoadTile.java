package tstopsim.Roads;

import tstopsim.Visual.*;
import java.util.ArrayList;

public class TurnRoadTile extends RoadTile{

    public TurnRoadTile(double x, double y, Direction dir) {
        super(x, y, dir);
    }

    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D mainPlate = new ColoredRectangle2D(xPos,yPos,RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION,ROAD_COLOR);
        components.add(mainPlate);
        switch(dir){
            case NORTH:
                components.add( new ColoredRectangle2D(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR) );
                components.add( new ColoredRectangle2D(xPos, yPos+0.9*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR) );
                components.add( new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR) );
                break;
            case EAST:
                components.add( new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR) );
                components.add( new ColoredRectangle2D(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR) );
                components.add( new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos+0.9*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR) );
                break;
            case SOUTH:
                components.add( new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR) );
                components.add( new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR) );
                components.add( new ColoredRectangle2D(xPos, yPos+0.9*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR) );
                break;
            case WEST:
                components.add( new ColoredRectangle2D(xPos, yPos+0.9*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR) );
                components.add( new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR) );
                components.add( new ColoredRectangle2D(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR) );
                break;
            default:
                System.out.println("Invalid Direction " + dir + " when assembling tile " + this);
        }
        
        
        return components;
    }
    
}
