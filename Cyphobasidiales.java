import java.awt.Color;
import java.util.List;

/**
 * Write a description of class Cyphobasidiales here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cyphobasidiales extends Cell{
    
    /**
     * Create a new Cyphobasidiales.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Cyphobasidiales(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
     * This is how the Cyphobasidiales decides if it's alive or not
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);
        
        int neighbourReq = 5;   //Number of neighbours required to continue living
        
        
        for (Cell cell : neighbours) {
            // Check if a cell (Cyanobacteria) is available for mutualistic symbiosis
            // Requires fewer neighbours to survive and come back to life.
            if (cell.getColor().equals(Color.RED) || cell.getColor().equals(Color.PINK)) {
                neighbourReq = 0;
            }
        }
        
        
        if (isAlive()) {
            if (neighbours.size() > neighbourReq) {
              setNextState(true);
            }
        } else {
            if(neighbours.size() > 3) {
                setNextState(true);
            }
        }
    }
}
