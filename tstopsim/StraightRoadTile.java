package tstopsim;

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
    public ArrayList<Rectangle2D> assemble() {
        ArrayList<Rectangle2D> components = new ArrayList<>();
        Rectangle2D mainPlate = new Rectangle2D.Double(xPos, yPos, RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION);
        components.add(mainPlate);
        switch(dir){
            case NORTH:
            case SOUTH:
                components.add(new Rectangle2D.Double(xPos, yPos, 0.1*RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION));
                components.add(new Rectangle2D.Double(xPos+0.9*RoadTile.ROAD_DIMENTION, yPos, 0.1*RoadTile.ROAD_DIMENTION,RoadTile.ROAD_DIMENTION));
                break;
            case EAST:
            case WEST:
                components.add(new Rectangle2D.Double(xPos, yPos, RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION));
                components.add(new Rectangle2D.Double(xPos, yPos+0.9*RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, 0.1*RoadTile.ROAD_DIMENTION));
                break;
            default:
                System.out.println("Something broke");
        }
        return components;
    }

    @Override
    public ArrayList<Rectangle2D> getParts() {
        return this.parts;
    }

    @Override
    public int getPriorityInt() {
        return 0;
    }

}
