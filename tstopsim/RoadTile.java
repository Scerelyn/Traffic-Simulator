package tstopsim;

import java.awt.geom.*;
import java.util.*;

public abstract class RoadTile implements Visualizable{
    //road 80%, sidewalk 20%. sidewalk 10% road 80% sidewalk 10%
    public static final int ROAD_DIMENTION = 200; //pixels side, length and width of the tile. This is the only given constant, so everything else scales
    protected ArrayList<Rectangle2D> parts;
    
}
