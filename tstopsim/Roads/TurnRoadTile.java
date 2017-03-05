package tstopsim.Roads;

import tstopsim.Visual.*;
import java.awt.Color;
import java.util.ArrayList;

public class TurnRoadTile extends RoadTile{

    public TurnRoadTile(double x, double y, Direction dir) {
        super(x, y, dir);
    }

    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D mainPlate = new ColoredRectangle2D(xPos,yPos,RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION,Color.DARK_GRAY);
        components.add(mainPlate);
        switch(dir){
            case NORTH:
                components.add( new ColoredRectangle2D(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos, yPos+0.9*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                break;
            case EAST:
                components.add( new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos+0.9*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                break;
            case SOUTH:
                components.add( new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos, yPos+0.9*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                break;
            case WEST:
                components.add( new ColoredRectangle2D(xPos, yPos+0.9*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                break;
            default:
                System.out.println("Invalid Direction " + dir + " when assembling tile " + this);
        }
        
        
        return components;
    }
    
}
