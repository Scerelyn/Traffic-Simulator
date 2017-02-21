package tstopsim;

import javax.swing.*;
import java.util.*;

public class Runner {

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setBounds(20,20,400,400);
        Vehicle vh = new Vehicle(40,40, Direction.EAST);
        Vehicle vh2 = new Vehicle(40,40, Direction.EAST);
        StraightRoadTile srt = new StraightRoadTile(0,0,Direction.WEST);
        StraightRoadTile srt2 = new StraightRoadTile(0,0,Direction.WEST);
        Light l = new Light(10,10,Direction.SOUTH);
        ArrayList<Visualizable> viz = new ArrayList<>();
        viz.add(vh);
        viz.add(vh2);
        viz.add(l);
        Map m = new Map(1,1,viz);
        
        m.setCity(
            new RoadTile[][]{
                {srt,srt2},
                
            }
        );
        
        jf.add(m);
        m.repaint();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

}
