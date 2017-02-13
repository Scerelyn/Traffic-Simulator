package tstopsim;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public interface Visualizable {
    public ArrayList<Rectangle2D> assemble(); //builds a list of rectangles, assembling the parts of what the final "image" looks like
    public ArrayList<Rectangle2D> getParts();
}
