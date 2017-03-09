package tstopsim;
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
        Map m = new Map(new File("src\\tstopsim\\data.txt"));

        Timer mainTimer = new Timer();
        LightCycler lc = new LightCycler(m);
        mainTimer.schedule(lc,0,1000);
        
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
        jf.setBounds(0, 0, m.getCity()[0].length*RoadTile.ROAD_DIMENTION+20, m.getCity().length*RoadTile.ROAD_DIMENTION+60);
        jf.setVisible(true);
        
        FileWindow fw = new FileWindow(jf,m,lc);
    }

}
