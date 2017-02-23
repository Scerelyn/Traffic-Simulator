package tstopsim;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class Runner {

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setBounds(20,20,800,800);
        
        Vehicle vh = new Vehicle(40,40, Direction.WEST);
        Vehicle vh2 = new Vehicle(100,100, Direction.NORTH);
        
//        StraightRoadTile srtHorz = new StraightRoadTile(0,0,Direction.WEST);
//        StraightRoadTile srtVert = new StraightRoadTile(0,0,Direction.NORTH);
//        
//        FourIntersectionRoadTile inter = new FourIntersectionRoadTile(0,0);
//        
//        TIntersectionRoadTile tin = new TIntersectionRoadTile(0,0,Direction.NORTH);
//        TIntersectionRoadTile tin2 = new TIntersectionRoadTile(0,0,Direction.SOUTH);
//        TIntersectionRoadTile tin3 = new TIntersectionRoadTile(0,0,Direction.EAST);
//        TIntersectionRoadTile tin4 = new TIntersectionRoadTile(0,0,Direction.WEST);
//        
//        TurnRoadTile turn = new TurnRoadTile(0,0,Direction.NORTH);
//        TurnRoadTile turn2 = new TurnRoadTile(0,0,Direction.EAST);
//        TurnRoadTile turn3 = new TurnRoadTile(0,0,Direction.SOUTH);
//        TurnRoadTile turn4 = new TurnRoadTile(0,0,Direction.WEST);
//        
//        NonDrivableRoadTile ndrt = new NonDrivableRoadTile(0,0);
//        
        Light l = new Light(10,10,Direction.WEST);
        
        ArrayList<Visualizable> viz = new ArrayList<>();
        //viz.add(vh);
        //viz.add(vh2);
        //viz.add(l);
        Map m = new Map(1,1,viz);
        
        m.setCity(
            new RoadTile[][]{ //now semi hard coded bounds
                {new NonDrivableRoadTile(0*RoadTile.ROAD_DIMENTION,0*RoadTile.ROAD_DIMENTION),                  new StraightRoadTile(1*RoadTile.ROAD_DIMENTION,0*RoadTile.ROAD_DIMENTION,Direction.NORTH),      new NonDrivableRoadTile(2*RoadTile.ROAD_DIMENTION,0*RoadTile.ROAD_DIMENTION),               new NonDrivableRoadTile(3*RoadTile.ROAD_DIMENTION,0*RoadTile.ROAD_DIMENTION)},
                {new StraightRoadTile(0*RoadTile.ROAD_DIMENTION,1*RoadTile.ROAD_DIMENTION,Direction.WEST),      new FourIntersectionRoadTile(1*RoadTile.ROAD_DIMENTION,1*RoadTile.ROAD_DIMENTION),              new StraightRoadTile(2*RoadTile.ROAD_DIMENTION,1*RoadTile.ROAD_DIMENTION,Direction.WEST),   new TIntersectionRoadTile(3*RoadTile.ROAD_DIMENTION,1*RoadTile.ROAD_DIMENTION,Direction.SOUTH), new TurnRoadTile(4*RoadTile.ROAD_DIMENTION,1*RoadTile.ROAD_DIMENTION,Direction.WEST)},
                {new NonDrivableRoadTile(0*RoadTile.ROAD_DIMENTION,2*RoadTile.ROAD_DIMENTION),                  new StraightRoadTile(1*RoadTile.ROAD_DIMENTION,2*RoadTile.ROAD_DIMENTION,Direction.NORTH),      new NonDrivableRoadTile(2*RoadTile.ROAD_DIMENTION,2*RoadTile.ROAD_DIMENTION),               new StraightRoadTile(3*RoadTile.ROAD_DIMENTION,2*RoadTile.ROAD_DIMENTION,Direction.NORTH)},
                {new StraightRoadTile(0*RoadTile.ROAD_DIMENTION,3*RoadTile.ROAD_DIMENTION,Direction.WEST),      new FourIntersectionRoadTile(1*RoadTile.ROAD_DIMENTION,3*RoadTile.ROAD_DIMENTION),              new StraightRoadTile(2*RoadTile.ROAD_DIMENTION,3*RoadTile.ROAD_DIMENTION,Direction.WEST),   new TIntersectionRoadTile(3*RoadTile.ROAD_DIMENTION,3*RoadTile.ROAD_DIMENTION,Direction.WEST)},
                {new NonDrivableRoadTile(0*RoadTile.ROAD_DIMENTION,4*RoadTile.ROAD_DIMENTION),                  new StraightRoadTile(1*RoadTile.ROAD_DIMENTION,4*RoadTile.ROAD_DIMENTION,Direction.NORTH),      new NonDrivableRoadTile(2*RoadTile.ROAD_DIMENTION,4*RoadTile.ROAD_DIMENTION),               new TurnRoadTile(3*RoadTile.ROAD_DIMENTION,4*RoadTile.ROAD_DIMENTION,Direction.NORTH),          new TurnRoadTile(4*RoadTile.ROAD_DIMENTION,4*RoadTile.ROAD_DIMENTION,Direction.SOUTH)},
                {new TurnRoadTile(0*RoadTile.ROAD_DIMENTION,5*RoadTile.ROAD_DIMENTION,Direction.EAST),          new TIntersectionRoadTile(1*RoadTile.ROAD_DIMENTION,5*RoadTile.ROAD_DIMENTION,Direction.NORTH), new NonDrivableRoadTile(2*RoadTile.ROAD_DIMENTION,5*RoadTile.ROAD_DIMENTION),               new NonDrivableRoadTile(3*RoadTile.ROAD_DIMENTION,5*RoadTile.ROAD_DIMENTION)},
                {new TIntersectionRoadTile(0*RoadTile.ROAD_DIMENTION,6*RoadTile.ROAD_DIMENTION,Direction.EAST)},
            }
        );
        
        Timer mainTimer = new Timer();
        mainTimer.schedule(new LightCycler(m),0,1000);
        
        jf.add(m);
        m.repaint();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

}
