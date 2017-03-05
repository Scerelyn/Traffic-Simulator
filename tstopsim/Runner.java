package tstopsim;

import java.awt.Color;
import java.io.File;
import javax.swing.*;
import java.util.Timer;

public class Runner {

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        
        jf.setBounds(20,20,800,800);
        
        //String workingDir = System.getProperty("user.dir");
	//System.out.println("Current working directory : " + workingDir);
        
        Map m = new Map(new File("src\\tstopsim\\data.txt"));
        
//        m.setCity( //drawing a nascar circuit now
//            new RoadTile[][]{
//                {new NonDrivableRoadTile(0,0),  new NonDrivableRoadTile(1,0),                       new NonDrivableRoadTile(2,0),               new NonDrivableRoadTile(3,0),                       new NonDrivableRoadTile(4,0),               new NonDrivableRoadTile(5,0),                   new NonDrivableRoadTile(6,0)},
//                {new NonDrivableRoadTile(0,1),  new TurnRoadTile(1,1,Direction.EAST),               new StraightRoadTile(2,1,Direction.EAST),   new TIntersectionRoadTile(3,1,Direction.SOUTH),     new StraightRoadTile(4,1,Direction.WEST),   new TurnRoadTile(5,1,Direction.SOUTH),          new NonDrivableRoadTile(6,1)},
//                {new NonDrivableRoadTile(0,2),  new StraightRoadTile(1,2,Direction.SOUTH),          new NonDrivableRoadTile(2,2),               new StraightRoadTile(3,2,Direction.SOUTH),          new NonDrivableRoadTile(4,2),               new StraightRoadTile(5,2,Direction.NORTH),      new NonDrivableRoadTile(6,2)},
//                {new NonDrivableRoadTile(0,3),  new TIntersectionRoadTile(1,3,Direction.EAST),      new StraightRoadTile(2,3,Direction.EAST),   new FourIntersectionRoadTile(3,3),                  new StraightRoadTile(4,3,Direction.EAST),   new TIntersectionRoadTile(5,3,Direction.WEST),  new NonDrivableRoadTile(6,3)},
//                {new NonDrivableRoadTile(0,4),  new StraightRoadTile(1,4,Direction.SOUTH),          new NonDrivableRoadTile(2,4),               new StraightRoadTile(3,4,Direction.SOUTH),          new NonDrivableRoadTile(4,4),               new StraightRoadTile(5,4,Direction.SOUTH),      new NonDrivableRoadTile(6,4)},
//                {new NonDrivableRoadTile(0,5),  new TurnRoadTile(1,5,Direction.NORTH),              new StraightRoadTile(2,5,Direction.EAST),   new TIntersectionRoadTile(3,5,Direction.NORTH),     new StraightRoadTile(4,5,Direction.EAST),   new TurnRoadTile(5,5,Direction.WEST),           new NonDrivableRoadTile(6,5)},
//                {new NonDrivableRoadTile(0,6),  new NonDrivableRoadTile(1,6),                       new NonDrivableRoadTile(2,6),               new NonDrivableRoadTile(3,6),                       new NonDrivableRoadTile(4,6),               new NonDrivableRoadTile(5,6),                   new NonDrivableRoadTile(6,6)},
//            }
//        );

        Timer mainTimer = new Timer();
        mainTimer.schedule(new LightCycler(m),0,1000);
        
//        for(int i = 0; i < 30; i++){
//            int yMrng = (int)(Math.random() * m.getCity().length);
//            int xMrng = (int)(Math.random() * m.getCity()[yMrng].length);
//            int yTrng = (int)(Math.random() * 2);
//            int xTrng = (int)(Math.random() * 2);
//            
//            while(m.getCity()[yMrng][xMrng] instanceof NonDrivableRoadTile){
//                yMrng = (int)(Math.random() * m.getCity().length);
//                xMrng = (int)(Math.random() * m.getCity()[yMrng].length);
//            }
//            m.addVehicle( new Vehicle( xMrng,yMrng,xTrng,yTrng,Direction.NORTH,m,new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) ) );
//        }
        
        jf.add(m);
        m.repaint();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

}
