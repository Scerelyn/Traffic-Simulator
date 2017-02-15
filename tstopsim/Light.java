package tstopsim;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Light implements Visualizable{
    private ArrayList<Rectangle2D> parts;
    private double xPos,yPos;
    
    public Light(){
        xPos=0;
        yPos=0;
        parts = assemble();
    }
    
    @Override
    public ArrayList<Rectangle2D> assemble() {
        ArrayList<Rectangle2D> components = new ArrayList<>();
        Rectangle2D box = new Rectangle2D.Double(xPos,yPos,0.3*RoadTile.ROAD_DIMENTION,0.1*RoadTile.ROAD_DIMENTION);
        Rectangle2D green = new Rectangle2D.Double(0.1*box.getWidth()+xPos,yPos+box.getHeight(),0.2*box.getWidth(),0.2*box.getHeight());
        Rectangle2D yellow = new Rectangle2D.Double(0.4*box.getWidth()+xPos,yPos+box.getHeight(),0.2*box.getWidth(),0.2*box.getHeight());
        Rectangle2D red = new Rectangle2D.Double(0.7*box.getWidth()+xPos,yPos+box.getHeight(),0.2*box.getWidth(),0.2*box.getHeight());
        components.add(box);
        components.add(green);
        components.add(red);
        components.add(yellow);
        return components;
    }

    @Override
    public ArrayList<Rectangle2D> getParts() {
        return this.parts;
    }

    @Override
    public int getPriorityInt() {
        return 2;
    }
    
    

}
