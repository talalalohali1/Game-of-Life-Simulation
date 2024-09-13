import java.awt.Color;
import java.util.List;

/**
 * Simplest form of life.
 * Fun Fact: Cyanobacteria are one of the simplest forms of life.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06 (1)
 */

public class Cyanobacteria extends Cell {

    /**
     * Create a new Cyanobacteria.
     *
     * @param field     The field currently occupied.
     * @param location The location within the field.
     */
    public Cyanobacteria(Field field, Location location, Color col) {
        super(field, location, col);
    }


    /**
    * This is how the Cyanobacteria decides if it's alive or not
    */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);
        generation++;
        
        int diseaseRandom = random.nextInt(100);    //Chance of becoming diseased
        int count = 0;                              //Number of living neighbours diseased
        boolean symbiosis = false;                  //Flag determining if in symbiosis
        
        
        for (Cell cell : neighbours) {
            // Count how many neighbours are diseased.
            if (cell.hasDisease()) {
                count++;
            }
            // Check if a cell (Cyphobasidiales) is available for mutualistic symbiosis
            if (cell.getColor().equals(Color.MAGENTA)) {
                symbiosis = true;
            }
        }
        // If more than seven neighbours are diseased, then the cell will also become diseased.
        // Otherwise, there is a 35% chance of the cell becoming diseased.
        // While diseased, the cell will turn from RED to PINK.
        if (count > 7 || diseaseRandom < 35) {
            setDiseaseState(true);
            setColor(Color.PINK);
        }
        
        
        if (hasDisease()){
            if (symbiosis && isAlive()) {
                setNextState(true);
            } else if (generation < 1000) {
                setNextState(true);
            } else {
                if (generation % 50 == 0) {
                    setNextState(true);
                } else if (neighbours.size() < 2 || neighbours.size() > 6) {
                    setNextState(true);
                }
            }
            if (generation % 5 == 0) {
                if (neighbours.size() >= 2 && neighbours.size() <= 6) {
                    setNextState(true);
                    setDiseaseState(false);
                    setColor(Color.RED); 
                }
            } else {
                if (neighbours.size() >= 1 && neighbours.size() <= 4){
                    setNextState(true);
                    setDiseaseState(false);
                    setColor(Color.RED); 
                }
            }
        } else {    
            if (isAlive()) {
                if (symbiosis) {
                    if (neighbours.size() > 2) {
                        setNextState(true);
                    }
                } else if (generation < 7007) {
                    if (neighbours.size() > 7) {
                        setNextState(true);
                    }
                } else {
                    if (neighbours.size() > 6) {
                        setNextState(true);
                    }
                }
            } else {
                if (generation % 4 == 0) {
                    if (neighbours.size() % 3 == 0) {
                        setNextState(true);
                    }
                } else {
                    if (neighbours.size() > 3) {
                        setNextState(true);
                    }
                }
            }
        }
    }
}
