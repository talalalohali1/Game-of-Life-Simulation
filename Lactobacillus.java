import java.awt.Color;
import java.util.List;

/**
 * Simplest form of life.
 * Fun Fact: Lactobacillus are one of the simplest forms of life.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06 (1)
 */

public class Lactobacillus extends Cell {

    /**
     * Create a new Lactobacillus.
     *
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     */
    public Lactobacillus(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
     * This is how the Lactobacillus decides if it's alive or not
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);
        
        int ruleRandom = random.nextInt(100);       //Chance of each rule being implemented
        int diseaseRandom = random.nextInt(100);    //Chance of becoming diseased
        int count = 0;                              //Number of living neighbours diseased
        
        // Count how many neighbours are diseased.
        for (Cell cell : neighbours) {
            if (cell.hasDisease()) {
                count++;
            }
        }
        // If less than four neighbours are diseased, then the cell will also become diseased.
        // Otherwise, there is a 10% chance of the cell becoming diseased.
        // While diseased, the cell will turn from GREEN to BLUE.
        if (count > 4 || diseaseRandom < 10) {
            setDiseaseState(true);
            setColor(Color.BLUE);
        }
        
        
        if (hasDisease()) {
            // 50% chance of implementation
            if (ruleRandom < 50) {
                if (neighbours.size() > 2) {
                    setNextState(true);
                }
            // 50% chance of implementation
            } else {    
                if (neighbours.size() > 4) {
                    setNextState(true);
                    setDiseaseState(false);
                    setColor(Color.GREEN);
                }
            }
        } else {
            if (isAlive()) {
                // 50% chance of implementation
                if (ruleRandom < 50) {
                    if (neighbours.size() < 3) {
                        setNextState(true);
                    }
                // 35% chance of implementation    
                } else if (ruleRandom < 85) {
                    if (neighbours.size() == 8) {
                        setNextState(true);
                    }
                // 15% chance of implementation
                } else { 
                    if (neighbours.size() != 4) {
                        setNextState(true);
                    }
                }
            } else {
                // 75% chance of implementation
                if (ruleRandom < 75) {
                    if(neighbours.size() == 0) {
                        setNextState(true);
                    }
                // 25% chance of implementation    
                } else {
                    if (neighbours. size() > 6) {
                        setNextState(true);
                    }
                }
            }
        }
    }
}
