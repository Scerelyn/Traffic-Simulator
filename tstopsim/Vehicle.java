package tstopsim;

import java.util.ArrayList;
import java.awt.Color;

public class Vehicle implements Visualizable {
    public static final int SIDE_LENGTH = (int)(RoadTile.ROAD_DIMENTION*0.3);
    private double xPos,yPos;
    private int xMap,yMap,xTile,yTile; //pos is on the map for drawing (the jframe x/y), map is relative to the map (tile x/y), tile is the roadtile x/y for the tile's array (inner tile x/y)
    private Direction dir;
    private ArrayList<ColoredRectangle2D> parts;
    private Map m;
    private Color carColor;
    public Vehicle(Map m){
        xPos=0;
        yPos=0;
        this.dir = Direction.NORTH;
        this.carColor = Color.CYAN;
        this.parts = assemble();
    }

    public Vehicle(int xMap, int yMap, int xTile, int yTile, Direction dir, Map m, Color c) {
        this.xMap = xMap;
        this.yMap = yMap;
        this.xTile = xTile;
        this.yTile = yTile;
        this.dir = dir;
        this.carColor = c;
        this.m = m;
        this.parts = assemble();
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> assemble() {
        ArrayList<ColoredRectangle2D> components = new ArrayList<>();
        ColoredRectangle2D chassis = new ColoredRectangle2D(xPos,yPos,SIDE_LENGTH,SIDE_LENGTH,carColor); 
        components.add(chassis);
        
        switch(this.dir){
            case NORTH:
                components.add(new ColoredRectangle2D(xPos, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //top left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); // top right
                components.add(new ColoredRectangle2D(xPos, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); //bottom left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); //bottom right
                break;
            case EAST:
                components.add(new ColoredRectangle2D(xPos, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); //top left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); // top right
                components.add(new ColoredRectangle2D(xPos, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); //bottom left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //bottom right
                break;
            case WEST:
                components.add(new ColoredRectangle2D(xPos, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //top left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); // top right
                components.add(new ColoredRectangle2D(xPos, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //bottom left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); //bottom right
                break;
            case SOUTH:
                components.add(new ColoredRectangle2D(xPos, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); //top left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); // top right
                components.add(new ColoredRectangle2D(xPos, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //bottom left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //bottom right
                break;
            default:
                System.out.println("Invalid direction when assembling car " + this);
                components.add(new ColoredRectangle2D(xPos, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.WHITE)); //top left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.RED)); // top right
                components.add(new ColoredRectangle2D(xPos, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.GREEN)); //bottom left
                components.add(new ColoredRectangle2D(xPos + 0.8*SIDE_LENGTH, yPos + 0.8*SIDE_LENGTH, 0.2*SIDE_LENGTH, 0.2*SIDE_LENGTH,Color.MAGENTA)); //bottom right
        }
        
        return components;
    }
    
    @Override
    public ArrayList<ColoredRectangle2D> getParts(){
        return this.parts;
    }
    
    @Override
    public int getPriorityInt(){
        return 1;
    }
    
    @Override
    public void rebuild(){
        this.parts = assemble();
    }
    
    public void moveLocations(double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rebuild();
    }
    
    /**
     * Rotates the vehicle a direction
     *
     * @param dir The direction to rotate to
     */
    public void rotate(Direction dir){
        this.dir = dir;
        this.rebuild();
    }
    
    /**
     * Randomly gets the left or right direction
     *
     * @return A random direction that is either to the left or right of the
     * current direction
     */
    public Direction getRandomTurn(){
        return Math.random() <= 0.5 ? dir.getLeft() : dir.getRight();
    }
    
    /**
     * Randomly returns true or false, used for deciding if the vehicle should
     * do a turn or not
     *
     * @return Randomly either true or false
     */
    public boolean doATurn(){
        return (int)(Math.random()*2) == 1;
    }
    
    //0,0 1,0
    //0,1 1,1
    /**
     * Moves the vehicle internally, as in through the map's tile's carSpots[][]
     * 
     * @param on The tile that this vehicle is on
     * @param next The title Adjacent to the on tile that the vehicle will move
     * to
     */
    //this method's size is cringe worthy
    public void internalMove(RoadTile on, RoadTile next){
        switch(dir){
            case NORTH:
                if(on instanceof TurnRoadTile){
                    switch(on.getDir()){
                        case NORTH:
                            if(xTile == 1 && yTile == 1){ //facing the center of the road, what
                                this.rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0 && next.getCarSpots()[1][1] == null && next.isSelectedLightGreen(Direction.SOUTH)){ 
                                next.getCarSpots()[1][1] = this;
                                on.getCarSpots()[0][1] = null;
                                yMap--;
                                xTile = 1;
                                yTile = 1;
                            } else if(xTile == 0 && yTile == 0){ //wrong way
                                rotate(dir.getOpposite());
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){ //also wrongway
                                rotate(dir.getOpposite());
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case SOUTH:
                            if(xTile == 1 && yTile == 1 && on.getCarSpots()[0][1] == null){
                                on.getCarSpots()[0][1] = this;
                                on.getCarSpots()[1][1] = null;
                                xTile = 1;
                                yTile = 0;
                            } else if(xTile == 1 && yTile == 0 && on.getCarSpots()[0][0] == null){ //on the corner, so turn
                                this.rotate(dir.getLeft());
                                on.getCarSpots()[0][0] = this;
                                on.getCarSpots()[0][1] = null;
                                xTile = 0;
                                yTile = 0;
                            } else if(xTile == 0 && yTile == 0){ //shouldnt be here, should've been facing west
                                this.rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){ //on the wrong lane
                                this.rotate(dir.getOpposite());
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case WEST:
                            if(xTile == 1 && yTile == 1 && on.getCarSpots()[0][1] == null){ //corner
                                on.getCarSpots()[0][1] = this;
                                on.getCarSpots()[1][1] = null;
                                xTile = 1;
                                yTile = 0;
                            } else if(xTile == 1 && yTile == 0 && next.getCarSpots()[1][1] == null && next.isSelectedLightGreen(Direction.SOUTH)){ //on the edge
                                    next.getCarSpots()[1][1] = this;
                                    on.getCarSpots()[0][1] = null;
                                    yMap--;
                                    xTile = 1;
                                    yTile = 1;                                
                            } else if(xTile == 0 && xTile == 0){ //wrong way
                                rotate(dir.getOpposite());
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){ //facing incorrectly
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case EAST:
                            if(xTile == 1 && yTile == 1 && next.getCarSpots()[1][0] == null && next.isSelectedLightGreen(Direction.SOUTH)){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && xTile == 0){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(dir.getOpposite());
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        default:
                            System.out.println("Undefined behavior when vehicle traversed in direction: " + dir);
                    }
                } else if(on instanceof TIntersectionRoadTile){
                    switch(on.getDir()){
                        case NORTH:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0 && next.getCarSpots()[1][1] == null && next.isSelectedLightGreen(Direction.SOUTH)){
                                next.getCarSpots()[1][1] = this;
                                on.getCarSpots()[0][1] = null;
                                yMap--;
                                xTile = 1;
                                yTile = 1;
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case SOUTH:
                            if (xTile == 1 && yTile == 1) {
                                Direction toTurn = this.getRandomTurn();
                                if (toTurn.equals(Direction.WEST) && on.getCarSpots()[0][1] == null) {
                                    on.getCarSpots()[0][1] = this;
                                    on.getCarSpots()[1][1] = null;
                                    this.yTile = 0;
                                    this.xTile = 1;
                                } else { //east or cant turn left (westward)
                                    rotate(Direction.EAST);
                                    //internalMove(on, m.getAdjacent(xMap, yMap, dir));
                                }
                            } else if (xTile == 0 && yTile == 1) {
                                rotate(Direction.EAST);
                                Direction toTurn = this.getRandomTurn();
                                if (toTurn.equals(this.dir.getRight())) {
                                    rotate(toTurn);
                                    //internalMove(on, m.getAdjacent(xMap, yMap, dir));
                                } else if(on.getCarSpots()[1][1] == null){
                                    on.getCarSpots()[1][1] = this;
                                    on.getCarSpots()[1][0] = null;
                                    xTile = 1;
                                    yTile = 1;
                                }
                            } else if (xTile == 1 && yTile == 0) {
                                rotate(Direction.WEST);
                                //internalMove(on, m.getAdjacent(xMap, yMap, dir));
                            } else if (xTile == 0 && yTile == 0) {
                                rotate(Direction.WEST);
                                //internalMove(on, m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case EAST:
                            if(xTile == 1 && yTile == 1){
                                Direction toTurn = this.getRandomTurn();
                                if(toTurn.equals(Direction.EAST)){
                                    rotate(Direction.EAST);
                                    //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                                } else if(on.getCarSpots()[0][1] == null){
                                    on.getCarSpots()[0][1] = this;
                                    on.getCarSpots()[1][1] = null;
                                    yTile = 0;
                                    xTile = 1;
                                }
                            } else if(xTile == 1 && yTile == 0 && next.isSelectedLightGreen(Direction.SOUTH) && next.getCarSpots()[1][1] == null){
                                next.getCarSpots()[1][1] = this;
                                on.getCarSpots()[0][1] = null;
                                yTile = 1;
                                xTile = 1;
                                yMap--;
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case WEST:
                            if(xTile == 1 && yTile == 1 && on.getCarSpots()[0][1] == null){
                                on.getCarSpots()[0][1] = this;
                                on.getCarSpots()[1][1] = null;
                                xTile = 1;
                                yTile = 1;
                            } else if(xTile == 1 && yTile == 0 && next.getCarSpots()[1][1] == null && next.isSelectedLightGreen(Direction.SOUTH)){
                                next.getCarSpots()[1][1] = this;
                                on.getCarSpots()[0][1] = null;
                                yMap--;
                                xTile = 1;
                                yTile = 1;
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        default:
                            System.out.println("Undefined behavior when traversing north on a t intersection");
                    }
           
                } else if(on instanceof FourIntersectionRoadTile){
                    if(xTile == 1 && yTile == 1){
                        Direction toTurn = this.getRandomTurn();
                        if(toTurn.equals(Direction.EAST)){
                            rotate(Direction.EAST);
                            //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                        } else if(on.getCarSpots()[0][1] == null){
                            on.getCarSpots()[0][1] = this;
                            on.getCarSpots()[1][1] = null;
                            xTile = 1;
                            yTile = 0;
                        }
                    } else if(xTile == 1 && yTile == 0 && next.getCarSpots()[1][1] == null && next.isSelectedLightGreen(Direction.SOUTH)){
                        next.getCarSpots()[1][1] = this;
                        on.getCarSpots()[0][1] = null;
                        yMap--;
                        xTile = 1;
                        yTile = 1;
                    } else if(xTile == 0 && yTile == 1){
                        rotate(Direction.SOUTH);
                        //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                    } else if(xTile == 0 && yTile == 0){
                        rotate(Direction.SOUTH);
                        //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                    }
                } else if(on instanceof StraightRoadTile){
                    switch (on.getDir()) {
                        case NORTH:
                        case SOUTH:
                            if (xTile == 1 && yTile == 1 && on.getCarSpots()[0][1] == null) {
                                on.getCarSpots()[0][1] = this;
                                on.getCarSpots()[1][1] = null;
                                xTile = 1;
                                yTile = 0;
                            } else if (xTile == 1 && yTile == 0 && next.getCarSpots()[1][1] == null && next.isSelectedLightGreen(Direction.SOUTH)) {
                                next.getCarSpots()[1][1] = this;
                                on.getCarSpots()[0][1] = null;
                                yMap--;
                                xTile = 1;
                                yTile = 1;
                            } else if (xTile == 0 && yTile == 1) {
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if (xTile == 0 && yTile == 0) {
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case EAST:
                        case WEST:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        default:
                            System.out.println("Undefined movement when travelling north on a straight road tile");
                    }
                }
                break;
            case SOUTH:
                if(on instanceof TurnRoadTile){
                    switch(on.getDir()){
                        case NORTH:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0 && on.getCarSpots()[1][0] == null){
                                on.getCarSpots()[1][0] = this;
                                on.getCarSpots()[0][0] = null;
                                yTile = 1;
                                xTile = 0;
                            }
                            break;
                        case SOUTH:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1 && next.getCarSpots()[0][0] == null && next.isSelectedLightGreen(Direction.NORTH)){
                                next.getCarSpots()[0][0] = this;
                                on.getCarSpots()[1][0] = null;
                                xTile = 0;
                                yTile = 0;
                                yMap++;
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case EAST:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on, m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1 && next.getCarSpots()[0][0] == null && next.isSelectedLightGreen(Direction.NORTH)){
                                next.getCarSpots()[0][0] = this;
                                on.getCarSpots()[1][0] = null;
                                yMap++;
                                yTile = 0;
                                xTile = 0;
                            } else if(xTile == 0 && yTile == 0 && on.getCarSpots()[1][0] == null){
                                on.getCarSpots()[1][0] = this;
                                on.getCarSpots()[0][0] = null;
                                yTile = 1;
                                xTile = 0;
                            }
                            break;
                        case WEST:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        default:
                            System.out.println("Invalid direction when traveling south on a turn tile");
                    }
                } else if(on instanceof TIntersectionRoadTile){
                    switch(on.getDir()){
                        case NORTH:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,next);
                            } else if(xTile == 0 && yTile == 0){
                                Direction toTurn = this.getRandomTurn();
                                if(toTurn.equals(dir.getRight())){
                                    rotate(toTurn);
                                    //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                                } else if(on.getCarSpots()[1][0] == null){
                                    on.getCarSpots()[1][0] = this;
                                    on.getCarSpots()[0][0] = null;
                                    xTile = 0;
                                    yTile = 1;
                                }
                            }
                            break;
                        case SOUTH:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1 && next.getCarSpots()[0][0] == null && next.isSelectedLightGreen(Direction.NORTH)){
                                next.getCarSpots()[0][0] = this;
                                on.getCarSpots()[1][0] = null;
                                xTile = 0;
                                yTile = 0;
                                yMap++;
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case EAST:
                            if(xTile == 1 && yTile == 1){
                                Direction toTurn = this.getRandomTurn();
                                if(toTurn.equals(dir.getRight())){
                                    rotate(toTurn);
                                    //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                                } else {
                                    rotate(Direction.NORTH);
                                }
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1 && next.getCarSpots()[0][0] == null && next.isSelectedLightGreen(Direction.NORTH)){
                                next.getCarSpots()[0][0] = this;
                                on.getCarSpots()[1][0] = null;
                                xTile = 0;
                                yTile = 0;
                                yMap++;
                            } else if(xTile == 0 && yTile == 0 && on.getCarSpots()[1][0] == null){
                                on.getCarSpots()[1][0] = this;
                                on.getCarSpots()[0][0] = null;
                                xTile = 0;
                                yTile = 1;
                            }
                            break;
                        case WEST:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1 && next.getCarSpots()[1][0] == null && next.isSelectedLightGreen(Direction.NORTH)){
                                next.getCarSpots()[0][0] = this;
                                on.getCarSpots()[1][0] = null;
                                xTile = 0;
                                yTile = 0;
                                yMap++;
                            } else if(xTile == 0 && yTile == 0 && on.getCarSpots()[1][0] == null){
                                on.getCarSpots()[1][0] = this;
                                on.getCarSpots()[0][0] = null;
                                xTile = 0;
                                yTile = 1;
                            }
                            break;
                        default:
                            System.out.println("Invalid direction when traveling south on a t intersection");
                    }
                } else if(on instanceof FourIntersectionRoadTile){
                    if (xTile == 1 && yTile == 1) {
                        rotate(Direction.NORTH);
                        //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                    } else if (xTile == 1 && yTile == 0) {
                        rotate(Direction.NORTH);
                        //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                    } else if (xTile == 0 && yTile == 1 && next.getCarSpots()[0][0] == null && next.isSelectedLightGreen(Direction.NORTH)) {
                        next.getCarSpots()[0][0] = this;
                        on.getCarSpots()[1][0] = null;
                        xTile = 0;
                        yTile = 0;
                        yMap++;
                    } else if (xTile == 0 && yTile == 0) {
                        Direction toTurn = this.getRandomTurn();
                        if(toTurn.equals(dir.getRight())){
                            rotate(toTurn);
                            //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                        } else if(on.getCarSpots()[1][0] == null){
                            on.getCarSpots()[1][0] = this;
                            on.getCarSpots()[0][0] = null;
                            xTile = 0;
                            yTile = 1;
                        }
                    }
                } else if(on instanceof StraightRoadTile){
                    switch(on.getDir()){
                        case NORTH:
                        case SOUTH:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1 && next.getCarSpots()[0][0] == null && next.isSelectedLightGreen(Direction.NORTH)){
                                next.getCarSpots()[0][0] = this;
                                on.getCarSpots()[1][0] = null;
                                xTile = 0;
                                yTile = 0;
                                yMap++;
                            } else if(xTile == 0 && yTile == 0 && on.getCarSpots()[1][0] == null){
                                on.getCarSpots()[1][0] = this;
                                on.getCarSpots()[0][0] = null;
                                xTile = 0;
                                yTile = 1;
                            }
                            break;
                        case EAST:
                        case WEST:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        default:
                            System.out.println("Invalid direction when traveling south on a straight road tile");
                    }
                }
                break;
            case WEST:
                if(on instanceof TurnRoadTile){
                    switch(on.getDir()){
                        case NORTH:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case SOUTH:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0 && on.getCarSpots()[0][0] == null){
                                on.getCarSpots()[0][0] = this;
                                on.getCarSpots()[1][0] = null;
                                xTile = 0;
                                yTile = 0;
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0 && next.getCarSpots()[0][1] == null && next.isSelectedLightGreen(Direction.EAST)){
                                next.getCarSpots()[0][1] = this;
                                on.getCarSpots()[0][0] = null;
                                xTile = 1;
                                yTile = 0;
                                xMap--;
                            }
                            break;
                        case EAST:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0 && on.getCarSpots()[0][0] == null){
                                on.getCarSpots()[0][0] = this;
                                on.getCarSpots()[0][1] = null;
                                xTile = 0;
                                yTile = 0;
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case WEST:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0 && next.getCarSpots()[0][1] == null && next.isSelectedLightGreen(Direction.EAST)){
                                next.getCarSpots()[0][1] = this;
                                on.getCarSpots()[0][0] = null;
                                xTile = 1;
                                yTile = 0;
                                xMap--;
                            }
                            break;
                        default:
                            System.out.println("Invalid direction when traveling south west on a turn tile");
                    }
                } else if(on instanceof TIntersectionRoadTile){
                    switch(on.getDir()){
                        case NORTH:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                Direction toTurn = this.getRandomTurn();
                                if(toTurn == dir.getRight()){
                                    rotate(toTurn);
                                    //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                                } else if(on.getCarSpots()[0][0] == null){
                                    on.getCarSpots()[0][0] = this;
                                    on.getCarSpots()[0][1] = null;
                                    xTile = 0;
                                    yTile = 0;
                                }
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0 && next.getCarSpots()[0][1] == null && next.isSelectedLightGreen(Direction.EAST)){
                                next.getCarSpots()[0][1] = this;
                                on.getCarSpots()[0][0] = null;
                                xTile = 1;
                                yTile = 0;
                                xMap--;
                            }
                            break;
                        case SOUTH:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0 && on.getCarSpots()[0][0] == null){
                                on.getCarSpots()[0][0] = this;
                                on.getCarSpots()[0][1] = null;
                                xTile = 0;
                                yTile = 0;
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0 && next.getCarSpots()[0][1] == null && next.isSelectedLightGreen(Direction.EAST)){
                                next.getCarSpots()[0][1] = this;
                                on.getCarSpots()[0][0] = null;
                                xTile = 1;
                                yTile = 0;
                                xMap--;
                            }
                            break;
                        case EAST:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case WEST:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0 && next.getCarSpots()[0][1] == null && next.isSelectedLightGreen(Direction.EAST)){
                                next.getCarSpots()[0][1] = this;
                                on.getCarSpots()[0][0] = null;
                                xTile = 1;
                                yTile = 0;
                                xMap--;
                            }
                            break;
                        default:
                            System.out.println("Invalid direction when traveling west on a t intersection");
                    }
                } else if(on instanceof FourIntersectionRoadTile){
                    if (xTile == 1 && yTile == 1) {
                        rotate(Direction.EAST);
                        //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                    } else if (xTile == 1 && yTile == 0 && on.getCarSpots()[0][0] == null) {
                        on.getCarSpots()[0][0] = this;
                        on.getCarSpots()[0][1] = null;
                        xTile = 0;
                        yTile = 0;
                    } else if (xTile == 0 && yTile == 1) {
                        rotate(Direction.EAST);
                        //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                    } else if (xTile == 0 && yTile == 0) {
                        Direction toTurn = this.getRandomTurn();
                        if(toTurn == dir.getLeft()){
                            rotate(Direction.SOUTH);
                            //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                        } else if(next.getCarSpots()[0][1] == null && next.isSelectedLightGreen(Direction.EAST)){
                            next.getCarSpots()[0][1] = this;
                            on.getCarSpots()[0][0] = null;
                            xTile = 1;
                            yTile = 0;
                            xMap--;
                        }
                    }
                } else if(on instanceof StraightRoadTile){
                    switch(on.getDir()){
                        case NORTH:
                        case SOUTH:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case EAST:
                        case WEST:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0 && on.getCarSpots()[0][0] == null){
                                on.getCarSpots()[0][0] = this;
                                on.getCarSpots()[0][1] = null;
                                xTile = 0;
                                yTile = 0;
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.EAST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0 && next.getCarSpots()[0][1] == null && next.isSelectedLightGreen(Direction.EAST)){
                                next.getCarSpots()[0][1] = this;
                                on.getCarSpots()[0][0] = null;
                                xTile = 1;
                                yTile = 0;
                                xMap--;
                            }
                            break;
                        default:
                            System.out.println("Invalid direction when traveling west on a straight road tile");
                    }
                }
                break;
            case EAST:
                if(on instanceof TurnRoadTile){
                    switch(on.getDir()){
                        case NORTH:
                            if(xTile == 1 && yTile == 1 && next.getCarSpots()[1][0] == null && next.isSelectedLightGreen(Direction.WEST)){
                                next.getCarSpots()[1][0] = this;
                                on.getCarSpots()[1][1] = null;
                                xTile = 0;
                                yTile = 1;
                                xMap++;
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1 && on.getCarSpots()[1][1] == null){
                                
                                on.getCarSpots()[1][1] = this;
                                on.getCarSpots()[1][0] = null;
                                xTile = 1;
                                yTile = 1;
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case SOUTH:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case EAST:
                            if(xTile == 1 && yTile == 1 && next.getCarSpots()[1][0] == null && next.isSelectedLightGreen(Direction.WEST)){
                                next.getCarSpots()[1][0] = this;
                                on.getCarSpots()[1][1] = null;
                                xTile = 0;
                                yTile = 1;
                                xMap++;
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case WEST:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1 && on.getCarSpots()[1][1] == null){
                                on.getCarSpots()[1][1] = this;
                                on.getCarSpots()[1][0] = null;
                                xTile = 1;
                                yTile = 1;
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        default:
                            System.out.println("Invalid direction when travelling east on a turn tile");
                    }
                } else if(on instanceof TIntersectionRoadTile){
                    switch(on.getDir()){
                        case NORTH:
                            if(xTile == 1 && yTile == 1 && next.getCarSpots()[1][0] == null && next.isSelectedLightGreen(Direction.WEST)){
                                next.getCarSpots()[1][0] = this;
                                on.getCarSpots()[1][1] = null;
                                xTile = 0;
                                yTile = 1;
                                xMap++;
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1 && on.getCarSpots()[1][1] == null){
                                on.getCarSpots()[1][1] = this;
                                on.getCarSpots()[1][0] = null;
                                xTile = 1;
                                yTile = 1;
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case SOUTH:
                            if(xTile == 1 && yTile == 1 && next.getCarSpots()[1][0] == null && next.isSelectedLightGreen(Direction.WEST)){
                                next.getCarSpots()[1][0] = this;
                                on.getCarSpots()[1][1] = null;
                                xTile = 0;
                                yTile = 1;
                                xMap++;
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                Direction toTurn = this.getRandomTurn();
                                if(toTurn.equals(dir.getRight())){
                                    rotate(Direction.SOUTH);
                                    //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                                } else if(on.getCarSpots()[1][1] == null){
                                    on.getCarSpots()[1][1] = this;
                                    on.getCarSpots()[1][0] = null;
                                    xTile = 1;
                                    yTile = 1;
                                }
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case EAST:
                            if(xTile == 1 && yTile == 1 && next.getCarSpots()[1][0] == null && next.isSelectedLightGreen(Direction.WEST)){
                                next.getCarSpots()[1][0] = this;
                                on.getCarSpots()[1][1] = null;
                                xTile = 0;
                                yTile = 1;
                                xMap++;
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case WEST:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        default:
                            System.out.println("Invalid direction when traveling east on a 3 intersection tile");
                    }
                } else if(on instanceof FourIntersectionRoadTile){
                    if (xTile == 1 && yTile == 1 && next.getCarSpots()[1][0] == null && next.isSelectedLightGreen(Direction.WEST)) {
                        next.getCarSpots()[1][0] = this;
                        on.getCarSpots()[1][1] = null;
                        xTile = 0;
                        yTile = 1;
                        xMap++;
                    } else if (xTile == 1 && yTile == 0) {
                        rotate(Direction.WEST);
                        //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                    } else if (xTile == 0 && yTile == 1) {
                        Direction toTurn = this.getRandomTurn();
                        if(toTurn.equals(dir.getRight())){
                            rotate(toTurn);
                            //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                        } else if(on.getCarSpots()[1][1] == null){
                            on.getCarSpots()[1][1] = this;
                            on.getCarSpots()[1][0] = null;
                            xTile = 1;
                            yTile = 1;
                        }
                    } else if (xTile == 0 && yTile == 0) {
                        rotate(Direction.WEST);
                        //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                    }
                } else if(on instanceof StraightRoadTile){
                    switch(on.getDir()){
                        case NORTH:
                        case SOUTH:
                            if(xTile == 1 && yTile == 1){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 1 && yTile == 0){
                                rotate(Direction.NORTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.SOUTH);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        case EAST:
                        case WEST:
                            if(xTile == 1 && yTile == 1 && next.getCarSpots()[1][0] == null && next.isSelectedLightGreen(Direction.WEST)){
                                next.getCarSpots()[1][0] = this;
                                on.getCarSpots()[1][1] = null;
                                xTile = 0;
                                yTile = 1;
                                xMap++;
                            } else if(xTile == 1 && yTile == 0 && on.getCarSpots()[1][1] == null){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            } else if(xTile == 0 && yTile == 1){
                                on.getCarSpots()[1][1] = this;
                                on.getCarSpots()[1][0] = null;
                                xTile = 1;
                                yTile = 1;
                            } else if(xTile == 0 && yTile == 0){
                                rotate(Direction.WEST);
                                //internalMove(on,m.getAdjacent(xMap, yMap, dir));
                            }
                            break;
                        default:
                            System.out.println("Invalid direction when traveling east on a straight road tile");
                    }
                }
                break;
            default:
                System.out.println("Invalid direction " + dir + " when vehicle " + this + " moved internally");
        }
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public int getxMap() {
        return xMap;
    }

    public int getyMap() {
        return yMap;
    }

    public int getxTile() {
        return xTile;
    }

    public int getyTile() {
        return yTile;
    }

    public Direction getDir() {
        return dir;
    }
    
    
}
