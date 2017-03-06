package tstopsim.Roads;

import java.util.*;
import tstopsim.Vehicle.Vehicle;

public class InternalVehicleMove extends TimerTask{
    
    private Map m;
    
    public InternalVehicleMove(Map m){
        this.m = m;
    }
    
    @Override
    public void run() {
        for(Vehicle v : m.getCars()){
            v.internalMove( m.getCity()[v.getyMap()][v.getxMap()], m.getAdjacent(v.getxMap(), v.getyMap(), v.getDir()) );
        }
    }
}
