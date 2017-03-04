package tstopsim;

import java.awt.Color;
import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class Runner {

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setBounds(20,20,800,800);
        
        Map m = new Map();
        
        m.setCity( //drawing a nascar circuit now
            new RoadTile[][]{
                {new NonDrivableRoadTile(0*RoadTile.ROAD_DIMENTION,0*RoadTile.ROAD_DIMENTION),  new NonDrivableRoadTile(1*RoadTile.ROAD_DIMENTION,0*RoadTile.ROAD_DIMENTION),               new NonDrivableRoadTile(2*RoadTile.ROAD_DIMENTION,0*RoadTile.ROAD_DIMENTION),               new NonDrivableRoadTile(3*RoadTile.ROAD_DIMENTION,0*RoadTile.ROAD_DIMENTION),               new NonDrivableRoadTile(4*RoadTile.ROAD_DIMENTION,0*RoadTile.ROAD_DIMENTION)},
                {new NonDrivableRoadTile(0*RoadTile.ROAD_DIMENTION,1*RoadTile.ROAD_DIMENTION),  new TurnRoadTile(1*RoadTile.ROAD_DIMENTION,1*RoadTile.ROAD_DIMENTION,Direction.EAST),       new StraightRoadTile(2*RoadTile.ROAD_DIMENTION,1*RoadTile.ROAD_DIMENTION,Direction.EAST),   new TurnRoadTile(3*RoadTile.ROAD_DIMENTION,1*RoadTile.ROAD_DIMENTION,Direction.SOUTH),      new NonDrivableRoadTile(4*RoadTile.ROAD_DIMENTION,1*RoadTile.ROAD_DIMENTION)},
                {new NonDrivableRoadTile(0*RoadTile.ROAD_DIMENTION,2*RoadTile.ROAD_DIMENTION),  new StraightRoadTile(1*RoadTile.ROAD_DIMENTION,2*RoadTile.ROAD_DIMENTION,Direction.SOUTH),  new NonDrivableRoadTile(2*RoadTile.ROAD_DIMENTION,2*RoadTile.ROAD_DIMENTION),               new StraightRoadTile(3*RoadTile.ROAD_DIMENTION,2*RoadTile.ROAD_DIMENTION,Direction.SOUTH),  new NonDrivableRoadTile(4*RoadTile.ROAD_DIMENTION,2*RoadTile.ROAD_DIMENTION)},
                {new NonDrivableRoadTile(0*RoadTile.ROAD_DIMENTION,3*RoadTile.ROAD_DIMENTION),  new TurnRoadTile(1*RoadTile.ROAD_DIMENTION,3*RoadTile.ROAD_DIMENTION,Direction.NORTH),      new StraightRoadTile(2*RoadTile.ROAD_DIMENTION,3*RoadTile.ROAD_DIMENTION,Direction.EAST),   new TurnRoadTile(3*RoadTile.ROAD_DIMENTION,3*RoadTile.ROAD_DIMENTION,Direction.WEST),       new NonDrivableRoadTile(4*RoadTile.ROAD_DIMENTION,3*RoadTile.ROAD_DIMENTION)},
                {new NonDrivableRoadTile(0*RoadTile.ROAD_DIMENTION,4*RoadTile.ROAD_DIMENTION),  new NonDrivableRoadTile(1*RoadTile.ROAD_DIMENTION,4*RoadTile.ROAD_DIMENTION),               new NonDrivableRoadTile(2*RoadTile.ROAD_DIMENTION,4*RoadTile.ROAD_DIMENTION),               new NonDrivableRoadTile(3*RoadTile.ROAD_DIMENTION,4*RoadTile.ROAD_DIMENTION),               new NonDrivableRoadTile(4*RoadTile.ROAD_DIMENTION,4*RoadTile.ROAD_DIMENTION)},
            }
        );
        Timer mainTimer = new Timer();
        mainTimer.schedule(new LightCycler(m),0,1000);
        
        m.addVehicle(new Vehicle(1,1,0,0, Direction.NORTH,m,Color.BLUE));
        m.addVehicle(new Vehicle(1,2,0,0, Direction.NORTH,m,Color.GREEN));
        m.addVehicle(new Vehicle(3,2,0,0, Direction.NORTH,m,Color.ORANGE));
        m.addVehicle(new Vehicle(2,3,0,0, Direction.NORTH,m,Color.MAGENTA));
        
        jf.add(m);
        m.repaint();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

}
