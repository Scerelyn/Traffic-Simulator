package tstopsim.Visual;

import tstopsim.Roads.Map;
import java.util.*;

public class Repainter extends TimerTask{
    private Map m;
    
    public Repainter(Map m){
        this.m = m;
    }
    
    @Override
    public void run() {
        m.repaint();
    }

}
