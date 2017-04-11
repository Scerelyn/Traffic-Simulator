package tstopsim.Roads;

import java.awt.Color;
import java.util.ArrayList;
import tstopsim.Vehicle.*;
import static tstopsim.Vehicle.Vehicle.SIDE_LENGTH;
import tstopsim.Visual.ColoredRectangle2D;
import tstopsim.Visual.Direction;

public class ParkingLotRoadTile extends NonDrivableRoadTile {

    private double car1Prob, car2Prob;
    
    public ParkingLotRoadTile(double x, double y, Direction dir) {
        super(x, y, dir, new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)), new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
    }

    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        car1Prob = Math.random();
        car2Prob = Math.random();
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D mainPlate = new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, ROAD_COLOR);
        components.add(mainPlate);
        switch (this.dir) {
            case NORTH:
                components.add(new ColoredRectangle2D(xPos, yPos, RoadTile.ROAD_DIMENTION, 0.1 * RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR));
                components.add(new ColoredRectangle2D(xPos + 0.475 * RoadTile.ROAD_DIMENTION, yPos + 0.1 * RoadTile.ROAD_DIMENTION, 0.05 * RoadTile.ROAD_DIMENTION, 0.8 * RoadTile.ROAD_DIMENTION, RoadTile.MIDLANE_PAINT));
                components.add(new ColoredRectangle2D(xPos, yPos + 0.1 * RoadTile.ROAD_DIMENTION, 0.025 * RoadTile.ROAD_DIMENTION, 0.8 * RoadTile.ROAD_DIMENTION, RoadTile.MIDLANE_PAINT));
                components.add(new ColoredRectangle2D(xPos + 0.975 * RoadTile.ROAD_DIMENTION, yPos + 0.1 * RoadTile.ROAD_DIMENTION, 0.025 * RoadTile.ROAD_DIMENTION, 0.8 * RoadTile.ROAD_DIMENTION, RoadTile.MIDLANE_PAINT));
                if (car1Prob <= 0.5) { //entire cars, this really bloats the methods size
                    components.add(new ColoredRectangle2D(xPos + 0.10 * RoadTile.ROAD_DIMENTION, yPos + 0.15 * RoadTile.ROAD_DIMENTION, SIDE_LENGTH, SIDE_LENGTH, specialColor1));
                    components.add(new ColoredRectangle2D(xPos + 0.10 * RoadTile.ROAD_DIMENTION, yPos + 0.15 * RoadTile.ROAD_DIMENTION, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); //top left
                    components.add(new ColoredRectangle2D(xPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, yPos + 0.15 * RoadTile.ROAD_DIMENTION, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); // top right
                    components.add(new ColoredRectangle2D(xPos + 0.10 * RoadTile.ROAD_DIMENTION, yPos + 0.15 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Vehicle.offBacklight)); //bottom left
                    components.add(new ColoredRectangle2D(xPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, yPos + 0.15 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Vehicle.offBacklight)); //bottom right
                    components.add(new ColoredRectangle2D(xPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.2 * SIDE_LENGTH, yPos + 0.15 * RoadTile.ROAD_DIMENTION + 0.2 * SIDE_LENGTH, 0.6 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.CYAN)); //window
                    components.add(new ColoredRectangle2D(xPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.45 * SIDE_LENGTH, yPos + 0.15 * RoadTile.ROAD_DIMENTION + -0.2 * SIDE_LENGTH, 0.1 * SIDE_LENGTH, 0.7 * SIDE_LENGTH, Color.BLACK)); //direction pointer
                }
                if (car2Prob <= 0.5) {
                    components.add(new ColoredRectangle2D(xPos + 0.60 * RoadTile.ROAD_DIMENTION, yPos + 0.15 * RoadTile.ROAD_DIMENTION, SIDE_LENGTH, SIDE_LENGTH, specialColor2));
                    components.add(new ColoredRectangle2D(xPos + 0.60 * RoadTile.ROAD_DIMENTION, yPos + 0.15 * RoadTile.ROAD_DIMENTION, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); //top left
                    components.add(new ColoredRectangle2D(xPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, yPos + 0.15 * RoadTile.ROAD_DIMENTION, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); // top right
                    components.add(new ColoredRectangle2D(xPos + 0.60 * RoadTile.ROAD_DIMENTION, yPos + 0.15 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Vehicle.offBacklight)); //bottom left
                    components.add(new ColoredRectangle2D(xPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, yPos + 0.15 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Vehicle.offBacklight)); //bottom right
                    components.add(new ColoredRectangle2D(xPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.2 * SIDE_LENGTH, yPos + 0.15 * RoadTile.ROAD_DIMENTION + 0.2 * SIDE_LENGTH, 0.6 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.CYAN)); //window
                    components.add(new ColoredRectangle2D(xPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.45 * SIDE_LENGTH, yPos + 0.15 * RoadTile.ROAD_DIMENTION + -0.2 * SIDE_LENGTH, 0.1 * SIDE_LENGTH, 0.7 * SIDE_LENGTH, Color.BLACK)); //direction pointer
                }
                break;
            case SOUTH:
                components.add(new ColoredRectangle2D(xPos, yPos + 0.9 * RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, 0.1 * RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR));
                components.add(new ColoredRectangle2D(xPos + 0.475 * RoadTile.ROAD_DIMENTION, yPos + 0.1 * RoadTile.ROAD_DIMENTION, 0.05 * RoadTile.ROAD_DIMENTION, 0.8 * RoadTile.ROAD_DIMENTION, RoadTile.MIDLANE_PAINT));
                components.add(new ColoredRectangle2D(xPos, yPos + 0.1 * RoadTile.ROAD_DIMENTION, 0.025 * RoadTile.ROAD_DIMENTION, 0.8 * RoadTile.ROAD_DIMENTION, RoadTile.MIDLANE_PAINT));
                components.add(new ColoredRectangle2D(xPos + 0.975 * RoadTile.ROAD_DIMENTION, yPos + 0.1 * RoadTile.ROAD_DIMENTION, 0.025 * RoadTile.ROAD_DIMENTION, 0.8 * RoadTile.ROAD_DIMENTION, RoadTile.MIDLANE_PAINT));
                if (car1Prob <= 0.5) {
                    components.add(new ColoredRectangle2D(xPos + 0.10 * RoadTile.ROAD_DIMENTION, yPos + 0.55 * RoadTile.ROAD_DIMENTION, SIDE_LENGTH, SIDE_LENGTH, specialColor1));
                    components.add(new ColoredRectangle2D(xPos + 0.10 * RoadTile.ROAD_DIMENTION, yPos + 0.55 * RoadTile.ROAD_DIMENTION, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Vehicle.offBacklight)); //top left
                    components.add(new ColoredRectangle2D(xPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.8*SIDE_LENGTH, yPos + 0.55 * RoadTile.ROAD_DIMENTION, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Vehicle.offBacklight)); // top right
                    components.add(new ColoredRectangle2D(xPos + 0.10 * RoadTile.ROAD_DIMENTION, yPos + 0.55 * RoadTile.ROAD_DIMENTION + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //bottom left
                    components.add(new ColoredRectangle2D(xPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.8*SIDE_LENGTH, yPos + 0.55 * RoadTile.ROAD_DIMENTION + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //bottom right
                    components.add(new ColoredRectangle2D(xPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.2*SIDE_LENGTH, yPos + 0.55 * RoadTile.ROAD_DIMENTION + 0.6*SIDE_LENGTH, 0.6*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.CYAN)); //window
                    components.add(new ColoredRectangle2D(xPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.45*SIDE_LENGTH, yPos + 0.55 * RoadTile.ROAD_DIMENTION + 0.5*SIDE_LENGTH, 0.1*SIDE_LENGTH, 0.7*SIDE_LENGTH,Color.BLACK)); //direction pointer
                }
                if (car2Prob <= 0.5) {
                    components.add(new ColoredRectangle2D(xPos + 0.60 * RoadTile.ROAD_DIMENTION, yPos + 0.55 * RoadTile.ROAD_DIMENTION, SIDE_LENGTH, SIDE_LENGTH, specialColor2));
                    components.add(new ColoredRectangle2D(xPos + 0.60 * RoadTile.ROAD_DIMENTION, yPos + 0.55 * RoadTile.ROAD_DIMENTION, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Vehicle.offBacklight)); //top left
                    components.add(new ColoredRectangle2D(xPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, yPos + 0.55 * RoadTile.ROAD_DIMENTION, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Vehicle.offBacklight)); // top right
                    components.add(new ColoredRectangle2D(xPos + 0.60 * RoadTile.ROAD_DIMENTION, yPos + 0.55 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); //bottom left
                    components.add(new ColoredRectangle2D(xPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, yPos + 0.55 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); //bottom right
                    components.add(new ColoredRectangle2D(xPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.2 * SIDE_LENGTH, yPos + 0.55 * RoadTile.ROAD_DIMENTION + 0.6 * SIDE_LENGTH, 0.6 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.CYAN)); //window
                    components.add(new ColoredRectangle2D(xPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.45 * SIDE_LENGTH, yPos + 0.55 * RoadTile.ROAD_DIMENTION + 0.5 * SIDE_LENGTH, 0.1 * SIDE_LENGTH, 0.7 * SIDE_LENGTH, Color.BLACK)); //direction pointer
                }
                break;
            case EAST:
                components.add(new ColoredRectangle2D(xPos + 0.9 * RoadTile.ROAD_DIMENTION, yPos, 0.1 * RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR));
                components.add(new ColoredRectangle2D(xPos + 0.1 * RoadTile.ROAD_DIMENTION, yPos + 0.475 * RoadTile.ROAD_DIMENTION, 0.8 * RoadTile.ROAD_DIMENTION, 0.05 * RoadTile.ROAD_DIMENTION, RoadTile.MIDLANE_PAINT));
                components.add(new ColoredRectangle2D(xPos + 0.1 * RoadTile.ROAD_DIMENTION, yPos, 0.8 * RoadTile.ROAD_DIMENTION, 0.025 * RoadTile.ROAD_DIMENTION, RoadTile.MIDLANE_PAINT));
                components.add(new ColoredRectangle2D(xPos + 0.1 * RoadTile.ROAD_DIMENTION, yPos + 0.975 * RoadTile.ROAD_DIMENTION, 0.8 * RoadTile.ROAD_DIMENTION, 0.025 * RoadTile.ROAD_DIMENTION, RoadTile.MIDLANE_PAINT));
                if (car1Prob <= 0.5) { 
                    components.add(new ColoredRectangle2D(xPos + 0.50 * RoadTile.ROAD_DIMENTION, yPos + 0.60 * RoadTile.ROAD_DIMENTION, SIDE_LENGTH, SIDE_LENGTH, specialColor1));
                    components.add(new ColoredRectangle2D(xPos + 0.50 * RoadTile.ROAD_DIMENTION, yPos + 0.60 * RoadTile.ROAD_DIMENTION, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Vehicle.offBacklight)); //top left
                    components.add(new ColoredRectangle2D(xPos + 0.50 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, yPos + 0.60 * RoadTile.ROAD_DIMENTION, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); // top right
                    components.add(new ColoredRectangle2D(xPos + 0.50 * RoadTile.ROAD_DIMENTION, yPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Vehicle.offBacklight)); //bottom left
                    components.add(new ColoredRectangle2D(xPos + 0.50 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, yPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); //bottom right
                    components.add(new ColoredRectangle2D(xPos + 0.50 * RoadTile.ROAD_DIMENTION + 0.6 * SIDE_LENGTH, yPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.6 * SIDE_LENGTH, Color.CYAN)); //window
                    components.add(new ColoredRectangle2D(xPos + 0.50 * RoadTile.ROAD_DIMENTION + 0.5 * SIDE_LENGTH, yPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.45 * SIDE_LENGTH, 0.7 * SIDE_LENGTH, 0.1 * SIDE_LENGTH, Color.BLACK)); //direction pointer));
                    
                }
                if (car2Prob <= 0.5) {
                    components.add(new ColoredRectangle2D(xPos + 0.50 * RoadTile.ROAD_DIMENTION, yPos + 0.10 * RoadTile.ROAD_DIMENTION, SIDE_LENGTH, SIDE_LENGTH, specialColor2));
                    components.add(new ColoredRectangle2D(xPos + 0.50 * RoadTile.ROAD_DIMENTION, yPos + 0.10 * RoadTile.ROAD_DIMENTION, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Vehicle.offBacklight)); //top left
                    components.add(new ColoredRectangle2D(xPos + 0.50 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, yPos + 0.10 * RoadTile.ROAD_DIMENTION, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); // top right
                    components.add(new ColoredRectangle2D(xPos + 0.50 * RoadTile.ROAD_DIMENTION, yPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Vehicle.offBacklight)); //bottom left
                    components.add(new ColoredRectangle2D(xPos + 0.50 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, yPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); //bottom right
                    components.add(new ColoredRectangle2D(xPos + 0.50 * RoadTile.ROAD_DIMENTION + 0.6 * SIDE_LENGTH, yPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.6 * SIDE_LENGTH, Color.CYAN)); //window
                    components.add(new ColoredRectangle2D(xPos + 0.50 * RoadTile.ROAD_DIMENTION + 0.5 * SIDE_LENGTH, yPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.45 * SIDE_LENGTH, 0.7 * SIDE_LENGTH, 0.1 * SIDE_LENGTH, Color.BLACK)); //direction pointer
                }
                break;
            case WEST:
                components.add(new ColoredRectangle2D(xPos, yPos, 0.1 * RoadTile.ROAD_DIMENTION, RoadTile.ROAD_DIMENTION, SIDE_WALK_COLOR));
                components.add(new ColoredRectangle2D(xPos + 0.1 * RoadTile.ROAD_DIMENTION, yPos + 0.475 * RoadTile.ROAD_DIMENTION, 0.8 * RoadTile.ROAD_DIMENTION, 0.05 * RoadTile.ROAD_DIMENTION, RoadTile.MIDLANE_PAINT));
                components.add(new ColoredRectangle2D(xPos + 0.1 * RoadTile.ROAD_DIMENTION, yPos, 0.8 * RoadTile.ROAD_DIMENTION, 0.025 * RoadTile.ROAD_DIMENTION, RoadTile.MIDLANE_PAINT));
                components.add(new ColoredRectangle2D(xPos + 0.1 * RoadTile.ROAD_DIMENTION, yPos + 0.975 * RoadTile.ROAD_DIMENTION, 0.8 * RoadTile.ROAD_DIMENTION, 0.025 * RoadTile.ROAD_DIMENTION, RoadTile.MIDLANE_PAINT));
                if (car1Prob <= 0.5) { 
                    components.add(new ColoredRectangle2D(xPos + 0.15 * RoadTile.ROAD_DIMENTION, yPos + 0.60 * RoadTile.ROAD_DIMENTION, SIDE_LENGTH, SIDE_LENGTH, specialColor1));
                    components.add(new ColoredRectangle2D(xPos + 0.15 * RoadTile.ROAD_DIMENTION, yPos + 0.60 * RoadTile.ROAD_DIMENTION, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); //top left
                    components.add(new ColoredRectangle2D(xPos + 0.15 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, yPos + 0.60 * RoadTile.ROAD_DIMENTION, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Vehicle.offBacklight)); // top right
                    components.add(new ColoredRectangle2D(xPos + 0.15 * RoadTile.ROAD_DIMENTION, yPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); //bottom left
                    components.add(new ColoredRectangle2D(xPos + 0.15 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, yPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); //bottom right
                    components.add(new ColoredRectangle2D(xPos + 0.15 * RoadTile.ROAD_DIMENTION + 0.2 * SIDE_LENGTH, yPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.6 * SIDE_LENGTH, Color.CYAN)); //window
                    components.add(new ColoredRectangle2D(xPos + 0.15 * RoadTile.ROAD_DIMENTION + -0.2 * SIDE_LENGTH, yPos + 0.60 * RoadTile.ROAD_DIMENTION + 0.45 * SIDE_LENGTH, 0.7 * SIDE_LENGTH, 0.1 * SIDE_LENGTH, Color.BLACK)); //direction pointer));
                }
                if (car2Prob <= 0.5) {
                    components.add(new ColoredRectangle2D(xPos + 0.15 * RoadTile.ROAD_DIMENTION, yPos + 0.10 * RoadTile.ROAD_DIMENTION, SIDE_LENGTH, SIDE_LENGTH, specialColor2));
                    components.add(new ColoredRectangle2D(xPos + 0.15 * RoadTile.ROAD_DIMENTION, yPos + 0.10 * RoadTile.ROAD_DIMENTION, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); //top left
                    components.add(new ColoredRectangle2D(xPos + 0.15 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, yPos + 0.10 * RoadTile.ROAD_DIMENTION, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Vehicle.offBacklight)); // top right
                    components.add(new ColoredRectangle2D(xPos + 0.15 * RoadTile.ROAD_DIMENTION, yPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Color.WHITE)); //bottom left
                    components.add(new ColoredRectangle2D(xPos + 0.15 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, yPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.8 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, Vehicle.offBacklight)); //bottom right
                    components.add(new ColoredRectangle2D(xPos + 0.15 * RoadTile.ROAD_DIMENTION + 0.2 * SIDE_LENGTH, yPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.2 * SIDE_LENGTH, 0.2 * SIDE_LENGTH, 0.6 * SIDE_LENGTH, Color.CYAN)); //window
                    components.add(new ColoredRectangle2D(xPos + 0.15 * RoadTile.ROAD_DIMENTION + -0.2 * SIDE_LENGTH, yPos + 0.10 * RoadTile.ROAD_DIMENTION + 0.45 * SIDE_LENGTH, 0.7 * SIDE_LENGTH, 0.1 * SIDE_LENGTH, Color.BLACK)); //direction pointer
                }
                break;
        }

        return components;
    }

}
