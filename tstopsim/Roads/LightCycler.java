package tstopsim.Roads;

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
        }
    }

    public ArrayList<Light> getLights() {
        return lights;
    }
    
    
    /**
     * Refills the lights arraylist with new light objects. This is called whenever Map m changes
     * @param newMap The new map to load lights from
     */
    public void reloadLights(Map newMap){
        this.m = newMap;
        this.lights.clear();
        for(RoadTile[] rtArr : m.getCity()){
            for(RoadTile rt : rtArr){
                lights.addAll(rt.getLights());
            }
        }
    }
}
