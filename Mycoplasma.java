import java.awt.Color;
import java.util.List;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06 (1)
 */

public class Mycoplasma extends Cell {

    /**
     * Create a new Mycoplasma.
     *
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     */
    public Mycoplasma(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
     * This is how the Mycoplasma decides if it's alive or not
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);
        
        int diseaseRandom = random.nextInt(100);    //Chance of becoming diseased
        int count = 0;                              //Number of living neighbours diseased
        
        // Count how many neighbours are diseased.
        for (Cell cell : neighbours) {
            if (cell.hasDisease()) {
                count++;
            }
        }
        // If more than four neighbours are diseased, then the cell will also become diseased.
        // Otherwise, there is a 25% chance of the cell becoming diseased.
        // While diseased, the cell will turn from ORANGE to YELLOW.
        if (count > 4 || diseaseRandom < 25) {
            setDiseaseState(true);
            setColor(Color.YELLOW);
        }
        
        
        if (hasDisease()){
            if (neighbours.size() > 1) {
                setNextState(true);
            }
            if (neighbours.size() > 5){
                setNextState(true);
                setDiseaseState(false);
                setColor(Color.ORANGE);
            }
        } else {    
            if (isAlive()) {
                if (neighbours.size() == 2 || neighbours.size() == 3) {
                  setNextState(true);
                }
            } else {
                if(neighbours.size() == 3) {
                    setNextState(true);
                }
            }
        }
    }
}
