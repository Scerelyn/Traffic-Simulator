package tstopsim.Roads;

import java.util.*;
import javax.swing.JOptionPane;
import tstopsim.Vehicle.Vehicle;

public class InternalVehicleMove extends TimerTask{
    
    private Map m;
    
    public InternalVehicleMove(Map m){
        this.m = m;
    }
    
    @Override
    public void run() {
        try {
            for (Vehicle v : m.getCars()) {
                v.internalMove(m.getCity()[v.getyMap()][v.getxMap()], m.getAdjacent(v.getxMap(), v.getyMap(), v.getDir()));
            }
        } catch(java.util.ConcurrentModificationException e){
            JOptionPane jop = new JOptionPane("The program crashed, don't spam the add 10 cars button so much next time");
            jop.grabFocus();
            jop.setVisible(true);
        }
    }
}
