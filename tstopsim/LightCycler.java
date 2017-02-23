package tstopsim;

import java.util.*;

public class LightCycler extends TimerTask{
    private ArrayList<Light> lights = new ArrayList<>();
    private Map m;
    
    public LightCycler(Map m){
        this.m = m;
        for(RoadTile[] rtArr : m.getCity()){
            for(RoadTile rt : rtArr){
                lights.addAll(rt.getLights());
            }
        }
    }
    
    @Override
    public void run() {
        
        for(Light l : lights){
            l.changeLightState();
            //System.out.println(l.getLightState());
        }
        m.repaint();
    }

    public ArrayList<Light> getLights() {
        return lights;
    }
    
    
}
