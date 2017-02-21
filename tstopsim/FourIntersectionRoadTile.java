package tstopsim;

import java.awt.Color;
import java.util.ArrayList;

public class FourIntersectionRoadTile extends RoadTile{

    public FourIntersectionRoadTile(double x, double y, Direction dir){
        super(x, y, dir);
        setupLights();
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D mainPlate = new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION,Color.DARK_GRAY);
        components.add(mainPlate);
        components.add(new ColoredRectangle2D(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY)); //top left
        components.add(new ColoredRectangle2D(xPos + 0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY)); //top right
        components.add(new ColoredRectangle2D(xPos, yPos + 0.9*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY)); //bottom left
        components.add(new ColoredRectangle2D(xPos + 0.9*RoadTile.ROAD_DIMENTION, yPos + 0.9*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION, Color.LIGHT_GRAY)); //bottom right
        
        return components;
    }
    
    @Override
    public void setupLights(){
        lights.clear();
        this.lights.add( new Light(xPos+0.1*RoadTile.ROAD_DIMENTION, yPos, Direction.SOUTH) ); //top left
        this.lights.add( new Light(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos+0.1*RoadTile.ROAD_DIMENTION ,Direction.WEST) ); //top right
        this.lights.add( new Light(xPos, yPos+0.6*RoadTile.ROAD_DIMENTION ,Direction.EAST) );//bottom left
        this.lights.add( new Light(xPos+0.6*RoadTile.ROAD_DIMENTION, yPos+0.9*RoadTile.ROAD_DIMENTION, Direction.NORTH) );
    }
}
