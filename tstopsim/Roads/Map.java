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
    
    public Map(){
        this.city = new RoadTile[][]{{new FourIntersectionRoadTile(0,0)}};
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
                                switch (rowArr[i + 1]) {
                                    case 'n':
                                        tempRow.add(new StraightRoadTile(pos, row, Direction.NORTH));
                                        pos++;
                                        break;
                                    case 's':
                                        tempRow.add(new StraightRoadTile(pos, row, Direction.SOUTH));
                                        pos++;
                                        break;
                                    case 'e':
                                        tempRow.add(new StraightRoadTile(pos, row, Direction.EAST));
                                        pos++;
                                        break;
                                    case 'w':
                                        tempRow.add(new StraightRoadTile(pos, row, Direction.WEST));
                                        pos++;
                                        break;
                                }
                                i++;
                                break;
                            case 't':
                                switch (rowArr[i + 1]) {
                                    case 'n':
                                        tempRow.add(new TurnRoadTile(pos, row, Direction.NORTH));
                                        pos++;
                                        break;
                                    case 's':
                                        tempRow.add(new TurnRoadTile(pos, row, Direction.SOUTH));
                                        pos++;
                                        break;
                                    case 'e':
                                        tempRow.add(new TurnRoadTile(pos, row, Direction.EAST));
                                        pos++;
                                        break;
                                    case 'w':
                                        tempRow.add(new TurnRoadTile(pos, row, Direction.WEST));
                                        pos++;
                                        break;
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

    public ArrayList<Vehicle> getCars() {
        return cars;
    }
}
