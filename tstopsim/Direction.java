package tstopsim;

public enum Direction {
    NORTH,EAST,SOUTH,WEST;
    
    public Direction getOpposite(){ //useful for traffic lights
        switch(this){
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            default:
                throw new RuntimeException("Invalid direction: " + this + " when returning opposites. How did this happen");
        }
    }
}
