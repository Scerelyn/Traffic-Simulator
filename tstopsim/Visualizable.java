package tstopsim;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public interface Visualizable {

    /**
     * Builds an ArrayList of Rectangle2D objects which is used to draw the
     * object that implements this interface. The ArrayList should be ordered
     * from lower rectangles, ie: bottommost, to topmost rectangles
     *
     * @return An ArrayList of Rectangle2D objects
     */
    public ArrayList<ColoredRectangle2D> assemble();

    /**
     * This simply returns the ArrayList created from assemble(), assuming it is
     * stored.
     *
     * @return An ArrayList of Rectangle2D objects
     */
    public ArrayList<ColoredRectangle2D> getParts();

    /**
     * Returns the priority integer of the class which implements this
     * interface. Lower integers means the object is drawn earlier, and higher
     * ones means it is draw last
     *
     * @return
     */
    public int getPriorityInt();
}
