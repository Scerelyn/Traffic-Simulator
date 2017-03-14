package tstopsim.Roads;

import java.awt.Color;
import java.util.ArrayList;
import tstopsim.Visual.ColoredRectangle2D;

public class McDonaldsRoadTile extends NonDrivableRoadTile {

    public McDonaldsRoadTile(double x, double y) {
        super(x, y);
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D mainPlate = new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION,RoadTile.SIDE_WALK_COLOR);
        components.add(mainPlate);
        
        components.add( new ColoredRectangle2D(xPos+0.1*RoadTile.ROAD_DIMENTION,  yPos+0.1*RoadTile.ROAD_DIMENTION,   0.8*RoadTile.ROAD_DIMENTION,    0.8*RoadTile.ROAD_DIMENTION,    Color.RED) );
        components.add( new ColoredRectangle2D(xPos+0.2*RoadTile.ROAD_DIMENTION,  yPos+0.2*RoadTile.ROAD_DIMENTION,   0.1*RoadTile.ROAD_DIMENTION,    0.6*RoadTile.ROAD_DIMENTION,    Color.YELLOW) );
        components.add( new ColoredRectangle2D(xPos+0.2*RoadTile.ROAD_DIMENTION,  yPos+0.2*RoadTile.ROAD_DIMENTION,   0.6*RoadTile.ROAD_DIMENTION,    0.1*RoadTile.ROAD_DIMENTION,    Color.YELLOW) );
        components.add( new ColoredRectangle2D(xPos+0.45*RoadTile.ROAD_DIMENTION, yPos+0.2*RoadTile.ROAD_DIMENTION,  0.1*RoadTile.ROAD_DIMENTION,    0.6*RoadTile.ROAD_DIMENTION,    Color.YELLOW) );
        components.add( new ColoredRectangle2D(xPos+0.7*RoadTile.ROAD_DIMENTION,  yPos+0.2*RoadTile.ROAD_DIMENTION,   0.1*RoadTile.ROAD_DIMENTION,    0.6*RoadTile.ROAD_DIMENTION,    Color.YELLOW) );
        return components;
    }

}
