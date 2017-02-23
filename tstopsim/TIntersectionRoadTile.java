package tstopsim;

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
        switch(dir){
            case NORTH:
                this.lights.add( new Light(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos+0.1*RoadTile.ROAD_DIMENTION ,Direction.WEST) ); //top right
                this.lights.add( new Light(xPos, yPos+0.6*RoadTile.ROAD_DIMENTION ,Direction.EAST) );//bottom left
                this.lights.add( new Light(xPos+0.6*RoadTile.ROAD_DIMENTION, yPos+0.9*RoadTile.ROAD_DIMENTION, Direction.NORTH,7) ); //bottom right
                break;
            case SOUTH:
                this.lights.add(new Light(xPos + 0.1 * RoadTile.ROAD_DIMENTION, yPos, Direction.SOUTH,7)); //top left
                this.lights.add(new Light(xPos + 0.9 * RoadTile.ROAD_DIMENTION, yPos + 0.1 * RoadTile.ROAD_DIMENTION, Direction.WEST)); //top right
                this.lights.add(new Light(xPos, yPos + 0.6 * RoadTile.ROAD_DIMENTION, Direction.EAST));//bottom left
                break;
            case EAST:
                this.lights.add( new Light(xPos+0.1*RoadTile.ROAD_DIMENTION, yPos, Direction.SOUTH) ); //top left
                this.lights.add( new Light(xPos, yPos+0.6*RoadTile.ROAD_DIMENTION ,Direction.EAST,7) );//bottom left
                this.lights.add( new Light(xPos+0.6*RoadTile.ROAD_DIMENTION, yPos+0.9*RoadTile.ROAD_DIMENTION, Direction.NORTH) ); //bottom right
                break;
            case WEST:
                this.lights.add( new Light(xPos+0.1*RoadTile.ROAD_DIMENTION, yPos, Direction.SOUTH) ); //top left
                this.lights.add( new Light(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos+0.1*RoadTile.ROAD_DIMENTION ,Direction.WEST,7) ); //top right
                this.lights.add( new Light(xPos+0.6*RoadTile.ROAD_DIMENTION, yPos+0.9*RoadTile.ROAD_DIMENTION, Direction.NORTH) ); //bottom right
                break;
            default:
                System.out.println("Invalid direction " + dir + " when setting up the lights of " + this);
        }
    }
    
    public Light getLightByDirection(Direction nDir){
        for(Light l : this.lights){
            if(l.getDir() == nDir){
                return l;
            }
        }
        return null;
    }
}
