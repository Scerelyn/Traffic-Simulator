package tstopsim;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class StraightRoadTile extends RoadTile{
    public StraightRoadTile(){
        this.xPos = 0;
        this.yPos = 0;
        this.dir = Direction.NORTH;
        this.parts = assemble();
    }
    
    double xPos,yPos;
    Direction dir;
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D mainPlate = new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION,Color.DARK_GRAY);
        components.add(mainPlate);
        switch(dir){
            case NORTH:
            case SOUTH:
                components.add(new ColoredRectangle2D(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION,Color.LIGHT_GRAY));
                components.add(new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION,Color.LIGHT_GRAY));
                break;
            case EAST:
            case WEST:
                components.add(new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION,Color.DARK_GRAY));
                components.add(new ColoredRectangle2D(xPos, yPos+0.9*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION,Color.DARK_GRAY));
                break;
            default:
                System.out.println("Something broke");
        }
        return components;
    }

    @Override
    public int getPriorityInt() {
        return 0;
    }

}
