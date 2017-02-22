package tstopsim;

import javax.swing.*;
import java.util.*;

public class Runner {

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setBounds(20,20,400,400);
        Vehicle vh = new Vehicle(40,40, Direction.WEST);
        Vehicle vh2 = new Vehicle(100,100, Direction.NORTH);
        StraightRoadTile srtHorz = new StraightRoadTile(0,0,Direction.WEST);
        StraightRoadTile srtVert = new StraightRoadTile(0,0,Direction.NORTH);
        FourIntersectionRoadTile inter = new FourIntersectionRoadTile(0,0);
        TIntersectionRoadTile tin = new TIntersectionRoadTile(0,0,Direction.NORTH);
        TIntersectionRoadTile tin2 = new TIntersectionRoadTile(0,0,Direction.SOUTH);
        TIntersectionRoadTile tin3 = new TIntersectionRoadTile(0,0,Direction.EAST);
        TIntersectionRoadTile tin4 = new TIntersectionRoadTile(0,0,Direction.WEST);
        NonDrivableRoadTile ndrt = new NonDrivableRoadTile(0,0);
        Light l = new Light(10,10,Direction.WEST);
        ArrayList<Visualizable> viz = new ArrayList<>();
        //viz.add(vh);
        //viz.add(vh2);
        //viz.add(l);
        Map m = new Map(1,1,viz);
        
        m.setCity(
            new RoadTile[][]{
                {ndrt,      srtVert,    ndrt,       ndrt},
                {srtHorz,   inter,      srtHorz,    tin2},
                {ndrt,      srtVert,    ndrt,       tin3},
                {srtHorz,   tin,      srtHorz,      tin4},
                {ndrt,      ndrt,       ndrt,       ndrt},
            }
        );
        
        jf.add(m);
        m.repaint();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

}
