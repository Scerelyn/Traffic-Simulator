package tstopsim;

import java.awt.Color;
import java.util.ArrayList;

public class StraightRoadTile extends RoadTile{
    
    public StraightRoadTile(Direction dir){
        this.xPos = 0;
        this.yPos = 0;
        this.dir = dir;
        this.parts = assemble();
    }

    public StraightRoadTile(double xPos, double yPos, Direction dir) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dir = dir;
        this.parts = assemble();
    }
    
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
                components.add(new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION,Color.LIGHT_GRAY));
                components.add(new ColoredRectangle2D(xPos, yPos+0.9*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION,Color.LIGHT_GRAY));
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
