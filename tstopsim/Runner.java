package tstopsim;

import javax.swing.*;
import java.util.*;

public class Runner {

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        Vehicle vh = new Vehicle();
        StraightRoadTile srt = new StraightRoadTile();
        ArrayList<Visualizable> viz = new ArrayList<>();
        viz.add(vh);
        viz.add(srt);
        Map m = new Map(viz);
        jf.add(m);
        m.repaint();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

}
