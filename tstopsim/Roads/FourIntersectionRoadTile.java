package tstopsim.Roads;

import java.util.ArrayList;
import tstopsim.Visual.*;

public class FourIntersectionRoadTile extends RoadTile{

    public FourIntersectionRoadTile(double x, double y){
        super(x, y, Direction.NORTH);
        setupLights();
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D mainPlate = new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION,ROAD_COLOR);
        components.add(mainPlate);
        components.add(new ColoredRectangle2D(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR)); //top left
        components.add(new ColoredRectangle2D(xPos + 0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION,SIDE_WALK_COLOR)); //top right
        components.add(new ColoredRectangle2D(xPos, yPos + 0.9*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR)); //bottom left
        components.add(new ColoredRectangle2D(xPos + 0.9*RoadTile.ROAD_DIMENTION, yPos + 0.9*RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR)); //bottom right
        
        return components;
    }
    
    @Override
    public void setupLights(){
        int state1 = (int)(Math.random()*6);
        int state2 = state1+6;
        lights.clear();
        this.lights.add( new Light(xPos+0.1*RoadTile.ROAD_DIMENTION, yPos, Direction.SOUTH, state2) ); //top left
        this.lights.add( new Light(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos+0.1*RoadTile.ROAD_DIMENTION ,Direction.WEST,state1) ); //top right
        this.lights.add( new Light(xPos, yPos+0.6*RoadTile.ROAD_DIMENTION ,Direction.EAST,state1) );//bottom left
        this.lights.add( new Light(xPos+0.6*RoadTile.ROAD_DIMENTION, yPos+0.9*RoadTile.ROAD_DIMENTION, Direction.NORTH, state2) );
    }
}
