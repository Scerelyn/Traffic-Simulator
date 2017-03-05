package tstopsim.Roads;

import tstopsim.Visual.*;
import java.awt.Color;
import java.util.ArrayList;

public class TIntersectionRoadTile extends RoadTile{

    public TIntersectionRoadTile(double x, double y, Direction dir) {
        super(x, y, dir);
    }

    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D mainPlate = new ColoredRectangle2D(xPos,yPos,RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION,Color.DARK_GRAY);
        components.add(mainPlate);
        switch(dir){
            case NORTH:
                components.add( new ColoredRectangle2D(xPos, yPos+0.9*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                break;
            case SOUTH:
                components.add( new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos, yPos+0.9*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos+0.9*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                break;
            case EAST:
                components.add( new ColoredRectangle2D(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos+0.9*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                break;
            case WEST:
                components.add( new ColoredRectangle2D(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                components.add( new ColoredRectangle2D(xPos, yPos+0.9*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY) );
                break;
            default:
                System.out.println("Invalid direction: " + dir + " entered when creating tile " + this);
        }
        return components;
    }
    
    @Override
    public void setupLights(){
        lights.clear();
        int state1 = (int)(Math.random()*6);
        int state2 = state1+6;
        switch(dir){
            case NORTH:
                this.lights.add( new Light(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos+0.1*RoadTile.ROAD_DIMENTION ,Direction.WEST, state1) ); //top right
                this.lights.add( new Light(xPos, yPos+0.6*RoadTile.ROAD_DIMENTION ,Direction.EAST, state1) );//bottom left
                this.lights.add( new Light(xPos+0.6*RoadTile.ROAD_DIMENTION, yPos+0.9*RoadTile.ROAD_DIMENTION, Direction.NORTH, state2) ); //bottom right
                break;
            case SOUTH:
                this.lights.add(new Light(xPos + 0.1 * RoadTile.ROAD_DIMENTION, yPos, Direction.SOUTH, state2)); //top left
                this.lights.add(new Light(xPos + 0.9 * RoadTile.ROAD_DIMENTION, yPos + 0.1 * RoadTile.ROAD_DIMENTION, Direction.WEST, state1)); //top right
                this.lights.add(new Light(xPos, yPos + 0.6 * RoadTile.ROAD_DIMENTION, Direction.EAST, state1));//bottom left
                break;
            case EAST:
                this.lights.add( new Light(xPos+0.1*RoadTile.ROAD_DIMENTION, yPos, Direction.SOUTH, state1) ); //top left
                this.lights.add( new Light(xPos, yPos+0.6*RoadTile.ROAD_DIMENTION ,Direction.EAST, state2) );//bottom left
                this.lights.add( new Light(xPos+0.6*RoadTile.ROAD_DIMENTION, yPos+0.9*RoadTile.ROAD_DIMENTION, Direction.NORTH, state1) ); //bottom right
                break;
            case WEST:
                this.lights.add( new Light(xPos+0.1*RoadTile.ROAD_DIMENTION, yPos, Direction.SOUTH, state1) ); //top left
                this.lights.add( new Light(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos+0.1*RoadTile.ROAD_DIMENTION ,Direction.WEST, state2) ); //top right
                this.lights.add( new Light(xPos+0.6*RoadTile.ROAD_DIMENTION, yPos+0.9*RoadTile.ROAD_DIMENTION, Direction.NORTH, state1) ); //bottom right
                break;
            default:
                System.out.println("Invalid direction " + dir + " when setting up the lights of " + this);
        }
    }
}
