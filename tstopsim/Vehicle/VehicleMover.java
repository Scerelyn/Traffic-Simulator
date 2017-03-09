package tstopsim.Vehicle;

import java.util.*;

public class VehicleMover extends TimerTask{
    private ArrayList<Vehicle> cars = new ArrayList<>();
    private double incrementRate;
    
    public VehicleMover(ArrayList<Vehicle> cars, double incrementRate){
        this.cars = cars;
        this.incrementRate = incrementRate;
    }
    
    @Override
    public void run() {
        for(Vehicle v : cars){
            if(v.getLastActionID() == 0){
                switch (v.getDir()) {
                    case NORTH:
                        if(v.getyInterPos() > v.getyPos()){
                            v.increInteryPos(-1 * incrementRate * Math.abs(v.getyLastPos() - v.getyPos()));
                        }
                        break;
                    case SOUTH:
                        if(v.getyInterPos() < v.getyPos()){
                            v.increInteryPos(incrementRate * Math.abs(v.getyLastPos() - v.getyPos()));
                        }
                        break;
                    case EAST:
                        if(v.getxInterPos() < v.getxPos()){
                            v.increInterxPos(incrementRate * Math.abs(v.getxLastPos() - v.getxPos()));
                        }
                        break;
                    case WEST:
                        if(v.getxInterPos() > v.getxPos()){
                            v.increInterxPos(-1 * incrementRate * Math.abs(v.getxLastPos() - v.getxPos()));
                        }
                        break;
                    default:
                }
            }
            v.rebuild();
        }
    }

}
