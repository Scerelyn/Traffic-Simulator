package tstopsim.Roads;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.JComponent;
import tstopsim.Visual.*;
import tstopsim.Vehicle.*;

public class Map extends JComponent {
    private RoadTile[][] city;
    private ArrayList<Vehicle> cars = new ArrayList<>();
    private File currentMap = new File("src\\tstopsim\\data.txt");
    
    public Map(){
        this.city = parseFile(currentMap);
    }
    
    public Map(File f){
        this.city = parseFile(f);
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        for(int y = 0; y < this.city.length; y++){
            for(int x = 0; x < this.city[y].length; x++){
                RoadTile tile = this.city[y][x];
                for(int part = 0; part < this.city[y][x].getParts().size(); part++){
                    Rectangle2D pieceRect = tile.getParts().get(part).getRect();
                    g2.setPaint(tile.getParts().get(part).getColor() );
                    g2.fill( pieceRect );
                }
                
            }
        }
        for(Vehicle v : cars){
            for(ColoredRectangle2D rect : v.getParts()){
                g2.setPaint( rect.getColor() );
                g2.fill(rect.getRect());
            }
        }
        for(RoadTile[] tileArr : this.city){
            for(RoadTile tile : tileArr){
                for(Light l : tile.getLights()){
                    for(ColoredRectangle2D part : l.getParts()){
                        g2.setPaint(part.getColor());
                        g2.fill(part.getRect());
                    }
                }
            }
        }
    }
    
    public void setTile(int x, int y, RoadTile rt){
        this.city[y][x] = rt;
    }
    
    public void setCity(RoadTile[][] city){
        this.city = city;
    }

    public RoadTile[][] getCity() {
        return city;
    }
    
    /**
     * Returns an adjacent roadtile on the city roadtile matrix
     *
     * @param xmap The roadtile's x position on the matrix
     * @param ymap The roadtile's y position on the matrix
     * @param dir The direction to check for adjacency
     * @return An adjacent roadtile object. This method returns null if no tile
     * is found
     */
    public RoadTile getAdjacent(int xmap, int ymap, Direction dir){
        switch(dir){
            case NORTH:
                try{
                    return city[ymap-1][xmap];
                } catch(IndexOutOfBoundsException e) {
                    return null;
                }
            case SOUTH:
                try{
                    return city[ymap+1][xmap];
                } catch(IndexOutOfBoundsException e) {
                    return null;
                }
            case EAST:
                try{
                    return city[ymap][xmap+1];
                } catch(IndexOutOfBoundsException e) {
                    return null;
                }
            case WEST:
                try{
                    return city[ymap][xmap-1];
                } catch(IndexOutOfBoundsException e) {
                    return null;
                }
            default:
                System.out.println("Invalid direction " + dir + " when checking for adjacent roadtiles");
                return null;
        }
    }
    
    public void addVehicle(Vehicle v){
        this.cars.add(v);
    }
    
    /**
     * Parses a file from text into data that this map can use as a roadtile matrix
     * @param f The file to parse
     * @return A roadtile matrix resulting from the file parsing
     */
    public RoadTile[][] parseFile(File f){
        ArrayList<RoadTile> tempRow = new ArrayList<>();
        RoadTile[][] tCity;
        int rowCount = 0;
        try{
            Scanner in = new Scanner(f);
            while(in.hasNextLine()){
                in.nextLine();
                rowCount++;
            }
        } catch(IOException e){
            System.out.println("Missing file");
        }
        tCity = new RoadTile[rowCount][];
        
        try{
            Scanner in = new Scanner(f);
            int row = 0;
            while (in.hasNextLine()) {
                tempRow.clear();
                String rowStr = in.nextLine().toLowerCase();
                char[] rowArr = rowStr.toCharArray();

                int pos = 0;
                for (int i = 0; i < rowStr.length(); i++) {
                    if (rowArr[i] != ' ') { //spaces ignored
                        switch (rowArr[i]) {
                            case 'b':
                                tempRow.add(new NonDrivableRoadTile(pos, row));
                                pos++;
                                break;
                            case 'i':
                                tempRow.add(new FourIntersectionRoadTile(pos, row));
                                pos++;
                                break;
                            case 'f':
                                if (rowArr[i + 2] == 'y') { //has a character marking "hey no sidewalk"
                                    switch (rowArr[i + 1]) {
                                        case 'n':
                                            tempRow.add(new StraightRoadTile(pos, row, Direction.NORTH, false));
                                            pos++;
                                            break;
                                        case 's':
                                            tempRow.add(new StraightRoadTile(pos, row, Direction.SOUTH, false));
                                            pos++;
                                            break;
                                        case 'e':
                                            tempRow.add(new StraightRoadTile(pos, row, Direction.EAST, false));
                                            pos++;
                                            break;
                                        case 'w':
                                            tempRow.add(new StraightRoadTile(pos, row, Direction.WEST, false));
                                            pos++;
                                            break;
                                    }
                                    i++;
                                } else {
                                    switch (rowArr[i + 1]) {
                                        case 'n':
                                            tempRow.add(new StraightRoadTile(pos, row, Direction.NORTH, true));
                                            pos++;
                                            break;
                                        case 's':
                                            tempRow.add(new StraightRoadTile(pos, row, Direction.SOUTH, true));
                                            pos++;
                                            break;
                                        case 'e':
                                            tempRow.add(new StraightRoadTile(pos, row, Direction.EAST, true));
                                            pos++;
                                            break;
                                        case 'w':
                                            tempRow.add(new StraightRoadTile(pos, row, Direction.WEST, true));
                                            pos++;
                                            break;
                                    }
                                }
                                i++;
                                break;
                            case 't':
                                if (rowArr[i+2] == 'y') {
                                    switch (rowArr[i + 1]) {
                                        case 'n':
                                            tempRow.add(new TurnRoadTile(pos, row, Direction.NORTH,false));
                                            pos++;
                                            break;
                                        case 's':
                                            tempRow.add(new TurnRoadTile(pos, row, Direction.SOUTH,false));
                                            pos++;
                                            break;
                                        case 'e':
                                            tempRow.add(new TurnRoadTile(pos, row, Direction.EAST,false));
                                            pos++;
                                            break;
                                        case 'w':
                                            tempRow.add(new TurnRoadTile(pos, row, Direction.WEST,false));
                                            pos++;
                                            break;
                                    }
                                    i++;
                                } else {
                                    switch (rowArr[i + 1]) {
                                        case 'n':
                                            tempRow.add(new TurnRoadTile(pos, row, Direction.NORTH,true));
                                            pos++;
                                            break;
                                        case 's':
                                            tempRow.add(new TurnRoadTile(pos, row, Direction.SOUTH,true));
                                            pos++;
                                            break;
                                        case 'e':
                                            tempRow.add(new TurnRoadTile(pos, row, Direction.EAST,true));
                                            pos++;
                                            break;
                                        case 'w':
                                            tempRow.add(new TurnRoadTile(pos, row, Direction.WEST,true));
                                            pos++;
                                            break;
                                    }
                                }
                                i++;
                                break;
                            case 'h':
                                switch (rowArr[i + 1]) {
                                    case 'n':
                                        tempRow.add(new TIntersectionRoadTile(pos, row, Direction.NORTH));
                                        pos++;
                                        break;
                                    case 's':
                                        tempRow.add(new TIntersectionRoadTile(pos, row, Direction.SOUTH));
                                        pos++;
                                        break;
                                    case 'e':
                                        tempRow.add(new TIntersectionRoadTile(pos, row, Direction.EAST));
                                        pos++;
                                        break;
                                    case 'w':
                                        tempRow.add(new TIntersectionRoadTile(pos, row, Direction.WEST));
                                        pos++;
                                        break;
                                }
                                i++;
                                break;
                            case 'm':
                                tempRow.add(new McDonaldsRoadTile(pos, row));
                                pos++;
                                break;
                            case 'd': //decorative
                                switch(rowArr[i + 1]){ //type of decorative
                                    case 'c': //center
                                        switch(rowArr[i + 2]){ //color
                                            case 'a':
                                                tempRow.add(new DecorativeCenterRoadTile(pos,row,Direction.NORTH,RoadTile.WATER_COLOR));
                                                pos++;
                                                break;
                                            case 'g':
                                                tempRow.add(new DecorativeCenterRoadTile(pos,row,Direction.NORTH,RoadTile.GRASS_COLOR));
                                                pos++;
                                                break;
                                        }
                                        i++;
                                        break;
                                    case 'q': //edge
                                        switch(rowArr[i + 2]){ //color
                                            case 'a':
                                                switch(rowArr[i+3]){ //direction
                                                    case 'n':
                                                        tempRow.add(new DecorativeEdgeRoadTile(pos,row,Direction.NORTH,RoadTile.WATER_COLOR));
                                                        pos++;
                                                        break;
                                                    case 's':
                                                        tempRow.add(new DecorativeEdgeRoadTile(pos,row,Direction.SOUTH,RoadTile.WATER_COLOR));
                                                        pos++;
                                                        break;
                                                    case 'e':
                                                        tempRow.add(new DecorativeEdgeRoadTile(pos,row,Direction.EAST,RoadTile.WATER_COLOR));
                                                        pos++;
                                                        break;
                                                    case 'w':
                                                        tempRow.add(new DecorativeEdgeRoadTile(pos,row,Direction.WEST,RoadTile.WATER_COLOR));
                                                        pos++;
                                                        break;
                                                }
                                                i++;
                                                break;
                                            case 'g':
                                                switch(rowArr[i+3]){
                                                    case 'n':
                                                        tempRow.add(new DecorativeEdgeRoadTile(pos,row,Direction.NORTH,RoadTile.GRASS_COLOR));
                                                        pos++;
                                                        break;
                                                    case 's':
                                                        tempRow.add(new DecorativeEdgeRoadTile(pos,row,Direction.SOUTH,RoadTile.GRASS_COLOR));
                                                        pos++;
                                                        break;
                                                    case 'e':
                                                        tempRow.add(new DecorativeEdgeRoadTile(pos,row,Direction.EAST,RoadTile.GRASS_COLOR));
                                                        pos++;
                                                        break;
                                                    case 'w':
                                                        tempRow.add(new DecorativeEdgeRoadTile(pos,row,Direction.WEST,RoadTile.GRASS_COLOR));
                                                        pos++;
                                                        break;
                                                }
                                                i++;
                                                break;
                                        }
                                        i++;
                                        break;
                                    case 'v': //corner/vertex
                                        switch(rowArr[i + 2]){ //color
                                            case 'a':
                                                switch(rowArr[i+3]){ //direction
                                                    case 'n':
                                                        tempRow.add(new DecorativeCornerRoadTile(pos,row,Direction.NORTH,RoadTile.WATER_COLOR));
                                                        pos++;
                                                        break;
                                                    case 's':
                                                        tempRow.add(new DecorativeCornerRoadTile(pos,row,Direction.SOUTH,RoadTile.WATER_COLOR));
                                                        pos++;
                                                        break;
                                                    case 'e':
                                                        tempRow.add(new DecorativeCornerRoadTile(pos,row,Direction.EAST,RoadTile.WATER_COLOR));
                                                        pos++;
                                                        break;
                                                    case 'w':
                                                        tempRow.add(new DecorativeCornerRoadTile(pos,row,Direction.WEST,RoadTile.WATER_COLOR));
                                                        pos++;
                                                        break;
                                                }
                                                i++;
                                                break;
                                            case 'g':
                                                switch(rowArr[i+3]){
                                                    case 'n':
                                                        tempRow.add(new DecorativeCornerRoadTile(pos,row,Direction.NORTH,RoadTile.GRASS_COLOR));
                                                        pos++;
                                                        break;
                                                    case 's':
                                                        tempRow.add(new DecorativeCornerRoadTile(pos,row,Direction.SOUTH,RoadTile.GRASS_COLOR));
                                                        pos++;
                                                        break;
                                                    case 'e':
                                                        tempRow.add(new DecorativeCornerRoadTile(pos,row,Direction.EAST,RoadTile.GRASS_COLOR));
                                                        pos++;
                                                        break;
                                                    case 'w':
                                                        tempRow.add(new DecorativeCornerRoadTile(pos,row,Direction.WEST,RoadTile.GRASS_COLOR));
                                                        pos++;
                                                        break;
                                                }
                                                i++;
                                                break;
                                        }
                                        i++;
                                        break;
                                }
                                i++;
                                break; //oh man what a mess
                            default:
                                tempRow.add(new NonDrivableRoadTile(pos, row));
                                pos++;
                        }
                    }
                }
                Object[] tArr = tempRow.toArray();
                tCity[row] = new RoadTile[tArr.length];
                for(int i = 0; i < tCity[row].length; i++){
                    tCity[row][i] = (RoadTile)tArr[i];
                }
                row++;
            }
        } catch(IOException e){
            System.out.println("Missing file");
        }
        return tCity;
    }
    
    /**
     * Loads a new city roadtile matrix from a file
     * @param f 
     */
    public void loadNewCity(File f){
        this.wipeCars();
        this.city = this.parseFile(f);
    }
    
    /**
     * Loads in cars into random spots in the city roadtile matrix
     *
     * @param carAmount The amount of cars to add
     */
    public void loadInCars(int carAmount){
        for(int i = 0; i < carAmount; i++){
            int yMrng = (int)(Math.random() * this.getCity().length);
            int xMrng = (int)(Math.random() * this.getCity()[yMrng].length);
            int yTrng = (int)(Math.random() * 2);
            int xTrng = (int)(Math.random() * 2);
            
            while(this.getCity()[yMrng][xMrng] instanceof NonDrivableRoadTile){
                yMrng = (int)(Math.random() * this.getCity().length);
                xMrng = (int)(Math.random() * this.getCity()[yMrng].length);
            }
            if(this.city[yMrng][xMrng].getCarSpots()[yTrng][xTrng] == null){
                Vehicle v = new Vehicle( xMrng,yMrng,xTrng,yTrng,Direction.NORTH,this,new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
                this.city[yMrng][xMrng].getCarSpots()[yTrng][xTrng] = v;
                this.addVehicle( v );
            }
        }
    }
    
    /**
     * Wipes out all vehicle objects in the cars arraylist and those in  carspots of each tile
     */
    public void wipeCars(){ //not using for each loops since those make a copy, i dont want that, i want to replace
        this.cars.clear();
        for(int y = 0; y < city.length; y++){
            for(int x = 0; x < city[y].length; x++){
                for(int i = 0; i < city[y][x].getCarSpots().length; i++){
                    for(int j = 0; j < city[y][x].getCarSpots()[i].length; j++){
                        city[y][x].carSpots[i][j] = null;
                    }
                }
            }
        }
    }
    
    public ArrayList<Vehicle> getCars() {
        return cars;
    }

    public File getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(File currentMap) {
        this.currentMap = currentMap;
    }
}
