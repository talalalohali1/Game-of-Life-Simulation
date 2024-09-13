import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * A class representing the shared characteristics of all forms of life
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06 (1)
 */

public abstract class Cell {
    // Whether the cell is alive or not.
    private boolean alive;

    // Whether the cell will be alive in the next generation.
    private boolean nextAlive;

    // Whether the cell is diseased or not.
    private boolean disease;
    
    // Whether the cell will be diseased in the next generation.
    private boolean nextDisease;
    
    // The cell's field.
    private Field field;

    // The cell's position in the field.
    private Location location;

    // The cell's color.
    private Color color = Color.white;
    
    // Random number for chance of cell becoming diseased.
    protected Random random = new Random();
    
    // The number of generations the cell has existed for.
    protected int generation;


    /**
     * Create a new cell at location in field.
     *
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     */
    public Cell(Field field, Location location, Color col) {
        alive = true;
        nextAlive = false;
        disease = false;
        nextDisease = false;
        
        this.field = field;
        setLocation(location);
        setColor(col);
        generation = 0;
    }

    /**
     * Make this cell act - that is: the cell decides it's status in the
     * next generation.
     */
    abstract public void act();

    /**
     * Check whether the cell is alive or not.
     * @return true If the cell is still alive.
     */
    protected boolean isAlive() {
        return alive;
    }

    /**
     * Indicate that the cell is no longer alive.
     */
    protected void setDead() {
        alive = false;
    }

    /**
     * Indicate that the cell will be alive or dead in the next generation.
     */
    public void setNextState(boolean value) {
      nextAlive = value;
    }

    
    /**
     * Check whether the cell is diseased or not.
     * @return true If the cell has a disease.
     */
    protected boolean hasDisease() {
        return disease;
    }
    
    /**
     * Indicate that the cell will be diseased or not in the next generation.
     */
    public void setDiseaseState(boolean diseaseValue) {
      nextDisease = diseaseValue;
    }
    
    
    /**
     * Changes the state of the cell
     */
    public void updateState() {
      alive = nextAlive;
      disease = nextDisease;
    }

    /**
     * Changes the color of the cell
     */
    public void setColor(Color col) {
      color = col;
    }

    /**
     * Returns the cell's color
     * @return color    The cell's color
     */
    public Color getColor() {
      return color;
    }

    /**
     * Return the cell's location.
     * @return location The cell's location.
     */
    protected Location getLocation() {
        return location;
    }

    /**
     * Place the cell at the new location in the given field.
     * @param location  The cell's location.
     */
    protected void setLocation(Location location) {
        this.location = location;
        field.place(this, location);
    }

    /**
     * Return the cell's field.
     * @return field    The cell's field.
     */
    protected Field getField() {
        return field;
    }
}
