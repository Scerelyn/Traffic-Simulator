package tstopsim;

import java.awt.Color;
import java.io.File;
import javax.swing.*;
import java.util.Timer;
import tstopsim.Roads.*;
import tstopsim.Roads.Map;
import tstopsim.Vehicle.*;
import tstopsim.Visual.*;

public class Runner {

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        
        jf.setBounds(20,20,800,800);
        
        //String workingDir = System.getProperty("user.dir");
	//System.out.println("Current working directory : " + workingDir);
        
        Map m = new Map(new File("src\\tstopsim\\data3.txt"));

        Timer mainTimer = new Timer();
        mainTimer.schedule(new LightCycler(m),0,1000);
        
        for(int i = 0; i < 30; i++){
            int yMrng = (int)(Math.random() * m.getCity().length);
            int xMrng = (int)(Math.random() * m.getCity()[yMrng].length);
            int yTrng = (int)(Math.random() * 2);
            int xTrng = (int)(Math.random() * 2);
            
            while(m.getCity()[yMrng][xMrng] instanceof NonDrivableRoadTile){
                yMrng = (int)(Math.random() * m.getCity().length);
                xMrng = (int)(Math.random() * m.getCity()[yMrng].length);
            }
            m.addVehicle( new Vehicle( xMrng,yMrng,xTrng,yTrng,Direction.NORTH,m,new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) ) );
        }
        
        //m.addVehicle(new Vehicle(1,1,0,0,Direction.NORTH,m,Color.CYAN));
        mainTimer.schedule(new VehicleMover(m.getCars(),0.016),0,10);
        mainTimer.schedule(new Repainter(m),0,10);
        mainTimer.schedule(new InternalVehicleMove(m),0,1000);
        jf.add(m);
        m.repaint();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon logo = new ImageIcon("src\\tstopsim\\logo.png");
        jf.setIconImage(logo.getImage());
        jf.setTitle("Crossroads Traffic Simulator");
        jf.setBounds(100, 100, m.getCity()[0].length*RoadTile.ROAD_DIMENTION+20, m.getCity().length*RoadTile.ROAD_DIMENTION+60);
        jf.setVisible(true);
    }

}
