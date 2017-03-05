package tstopsim.Visual;

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
    
    public Direction getLeft(){
        switch(this){
            case NORTH:
                return WEST;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            case WEST:
                return SOUTH;
            default:
                throw new RuntimeException("Invalid direction: " + this + " when returning lefts. How did this happen");
        }
    }
    
    public Direction getRight(){
        switch(this){
            case NORTH:
                return EAST;
            case SOUTH:
                return WEST;
            case EAST:
                return SOUTH;
            case WEST:
                return NORTH;
            default:
                throw new RuntimeException("Invalid direction: " + this + " when returning rights. How did this happen");
        }
    }
}
