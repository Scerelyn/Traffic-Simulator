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
                {new NonDrivableRoadTile(0,0),  new NonDrivableRoadTile(1,0),               new NonDrivableRoadTile(2,0),               new NonDrivableRoadTile(3,0),               new NonDrivableRoadTile(4,0)},
                {new NonDrivableRoadTile(0,1),  new TurnRoadTile(1,1,Direction.EAST),       new StraightRoadTile(2,1,Direction.EAST),   new TurnRoadTile(3,1,Direction.SOUTH),      new NonDrivableRoadTile(4,1)},
                {new NonDrivableRoadTile(0,2),  new StraightRoadTile(1,2,Direction.SOUTH),  new NonDrivableRoadTile(2,2),               new StraightRoadTile(3,2,Direction.SOUTH),  new NonDrivableRoadTile(4,2),               new NonDrivableRoadTile(5,2),               new NonDrivableRoadTile(6,2)},
                {new NonDrivableRoadTile(0,3),  new TurnRoadTile(1,3,Direction.NORTH),      new StraightRoadTile(2,3,Direction.EAST),   new FourIntersectionRoadTile(3,3),          new StraightRoadTile(4,3,Direction.EAST),   new TurnRoadTile(5,3,Direction.SOUTH),      new NonDrivableRoadTile(6,3)},
                {new NonDrivableRoadTile(0,4),  new NonDrivableRoadTile(1,4),               new NonDrivableRoadTile(2,4),               new StraightRoadTile(3,4,Direction.SOUTH),  new NonDrivableRoadTile(4,4),               new StraightRoadTile(5,4,Direction.SOUTH),  new NonDrivableRoadTile(6,4)},
                {new NonDrivableRoadTile(0,5),  new NonDrivableRoadTile(1,5),               new NonDrivableRoadTile(2,5),               new TurnRoadTile(3,5,Direction.NORTH),      new StraightRoadTile(4,5,Direction.EAST),   new TurnRoadTile(5,5,Direction.WEST),       new NonDrivableRoadTile(6,5)},
                {new NonDrivableRoadTile(0,6),  new NonDrivableRoadTile(1,6),               new NonDrivableRoadTile(2,6),               new NonDrivableRoadTile(3,6),               new NonDrivableRoadTile(4,6),               new NonDrivableRoadTile(5,6),               new NonDrivableRoadTile(6,6)},
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
