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
        FourIntersectionRoadTile inter = new FourIntersectionRoadTile(0,0,Direction.NORTH);
        NonDrivableRoadTile ndrt = new NonDrivableRoadTile(0,0);
        Light l = new Light(10,10,Direction.WEST);
        ArrayList<Visualizable> viz = new ArrayList<>();
        //viz.add(vh);
        //viz.add(vh2);
        viz.add(l);
        Map m = new Map(1,1,viz);
        
        m.setCity(
            new RoadTile[][]{
                {ndrt,      srtVert,    ndrt},
                {srtHorz,   inter,      srtHorz},
                {ndrt,      srtVert,    ndrt},
            }
        );
        
        jf.add(m);
        m.repaint();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

}
