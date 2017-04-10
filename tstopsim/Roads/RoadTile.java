package tstopsim.Roads;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import tstopsim.Visual.*;
import tstopsim.Vehicle.*;



public abstract class RoadTile implements Visualizable{
    //road 80%, sidewalk 20%. sidewalk 10% road 80% sidewalk 10%
    
    /*
    The length and width of the RoadTile and all inheriting Tiles. All objects related to RoadTiles are based off of this dimention
    */
    public static final int ROAD_DIMENTION = readConfig();
    
    protected ArrayList<ColoredRectangle2D> parts;
    protected Vehicle[][] carSpots = new Vehicle[2][2]; //{ north{west, east}, south{west, east}}
    protected ArrayList<Light> lights = new ArrayList<>();
    protected double xPos,yPos;
    protected Direction dir;
    protected Color specialColor; //incase a tile has a color determined by an input file such as DecorativeCenterRoadTile's background color
    protected boolean hasSidewalk = true; //for sidewalkless variants
    protected final static Color SIDE_WALK_COLOR = Color.LIGHT_GRAY;
    protected final static Color ROAD_COLOR = Color.LIGHT_GRAY.darker();
    protected final static Color GRASS_COLOR = Color.GREEN.darker().darker();
    protected final static Color WATER_COLOR = Color.CYAN.darker();
    protected final static Color MIDLANE_PAINT = new Color(180,180,120);
    
    public RoadTile(double x, double y, Direction dir){
        setBounds(x*RoadTile.ROAD_DIMENTION,y*RoadTile.ROAD_DIMENTION,dir);
    }
    
    public RoadTile(double x, double y, Direction dir, Color special){
        this.specialColor = special;
        setBounds(x*RoadTile.ROAD_DIMENTION,y*RoadTile.ROAD_DIMENTION,dir);
    }
    
    public RoadTile(double x, double y, Direction dir, boolean hasSidewalk){
        this.hasSidewalk = hasSidewalk;
        setBounds(x*RoadTile.ROAD_DIMENTION,y*RoadTile.ROAD_DIMENTION,dir);
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> getParts() {
        return this.parts;
    }
    
    /**
     * Sets the bounds to a specified point and direction, which in turn
     * rebuilds all parts within the roadtile and lights if present
     *
     * @param x The x coordinate to move to
     * @param y The y coordinate to move to
     * @param dir The direction to face
     */
    public final void setBounds(double x, double y, Direction dir){
        this.xPos = x;
        this.yPos = y;
        this.dir = dir;
        this.parts = assemble();
        setupLights();
    }
    
    /**
     * An overload of setBounds which sets the bounds to a specified point 
     * and direction as well setting specialColor for tiles which require 
     * a certain color
     * 
     * @param x The x coordinate to move to
     * @param y The y coordinate to move to
     * @param dir The direction to face
     * @param c The special color to assign
     */
    public final void setBounds(double x, double y, Direction dir, Color c){
        this.specialColor = c;
        setBounds(x,y,dir);
    }
    
    /**
     * Adds lights to the lights ArrayList. Left as empty but not needed to be
     * overridden since only some road tiles need lights
     */
    public void setupLights(){}
    
    @Override
    public int getPriorityInt() {
        return 0;
    }
    
    @Override
    public void rebuild(){
        this.parts = assemble();
    }
    
    public Vehicle[][] getCarSpots() {
        return carSpots;
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public Direction getDir() {
        return dir;
    }
    
    /**
     * Returns a Light object facing the given direction
     * @param nDir The direction to look for
     * @return A Light object facing in nDir direction, or null if no light is found
     */
    public Light getLightByDirection(Direction nDir){
        for(Light l : this.lights){
            if(l.getDir() == nDir){
                return l;
            }
        }
        return null;
    }
    
    /**
     * Checks to see if the selected light is green or "green", as in, it is legal for a car to drive past
     * @param dir The direction of the light to look for
     * @return True if the light's state is green or if no light is present
     */
    public boolean isSelectedLightGreen(Direction dir){
        Light l = getLightByDirection(dir);
        return (l != null) 
                ? l.getLightState() >= Light.GREEN_LOWER && l.getLightState() <= Light.GREEN_UPPER 
                : true; //assume no lights is always green, since it kinda is by how people respond to no lights
    }
    
    /**
     * Reads config.txt to set the RoadTile.ROAD_DIMENTION value
     * @return The read value on config.txt, if no file is found 100 is returned
     */
    private static int readConfig(){
        File f = new File("src\\tstopsim\\config.txt");
        int sideLength = 100; //default value
        try {
            Scanner sc = new Scanner(f);
            sideLength = sc.nextInt();
        } catch (FileNotFoundException ex) {
            System.out.println("Config file not found, defaulting to value 100px for roadtile side length");
        }
        return sideLength;
    }
}
