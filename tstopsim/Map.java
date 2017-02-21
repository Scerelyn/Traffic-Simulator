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
                RoadTile piece = this.city[y][x];
                System.out.println(x + " " + y);
                piece.setBounds(x*RoadTile.ROAD_DIMENTION, y*RoadTile.ROAD_DIMENTION);
                for(int part = 0; part < this.city[y][x].getParts().size(); part++){
                    Rectangle2D pieceRect = piece.getParts().get(part).getRect();
                    g2.setPaint( piece.getParts().get(part).getColor() );
                    g2.fill( pieceRect );
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
}
