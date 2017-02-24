package tstopsim;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import javax.swing.JComponent;

public class Map extends JComponent {
    private RoadTile[][] city;
    private ArrayList<Visualizable> components;
    
    public Map(){
        this.city = new RoadTile[1][1];
    }
    
    public Map(int x, int y, ArrayList<Visualizable> arr){
        this.city = new RoadTile[y][x];
        this.components = arr;
        components.sort((v1, v2) -> {
            return Integer.compare(v1.getPriorityInt(), v2.getPriorityInt());
        });
    }
    /**
     * Creates a map object with a given ArrayList of Visualizable objects, then
     * sorts the list by their priority integer
     *
     * @param arr The ArrayList of Visualizable-implementing objects
     */
    public Map(ArrayList<Visualizable> arr) {
        this.components = arr;
        components.sort((v1, v2) -> {
            return Integer.compare(v1.getPriorityInt(), v2.getPriorityInt());
        });
        this.city = new RoadTile[1][1];
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
                for(Light l : tile.getLights()){
                    for(ColoredRectangle2D part : l.getParts()){
                        g2.setPaint(part.getColor());
                        g2.fill(part.getRect());
                    }
                }
            }
        }
        for(Visualizable v : components){
            for(ColoredRectangle2D rect : v.getParts()){
                g2.setPaint( rect.getColor() );
                g2.fill(rect.getRect());
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
}
