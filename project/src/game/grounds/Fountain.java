package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.RefillAction;

/**
 * An abstract class representing the Fountains, that extends Ground
 *
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public abstract class Fountain extends Ground {

    /**
     * Variables for
     */
    public static final int MAX_CAPACITY = 10;
    protected int capacity;
    protected int ticker;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar,int ticker, int capacity) {
        super(displayChar);
        this.addCapability(Status.FOUNTAIN);
        this.ticker = ticker;
        this.capacity = capacity;
    }

    /**
     * tick method for Fountain
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if(this.hasCapability(Status.BEING_REFILLED)){
            this.capacity -= 1;
            this.removeCapability(Status.BEING_REFILLED);
        }
        else if(this.hasCapability(Status.BEING_CONSUMED)){
            this.capacity -= 5;
            this.removeCapability(Status.BEING_CONSUMED);
        }
        if(this.capacity <= 0) {
            this.addCapability(Status.DEPLETED);
            this.ticker += 1;
            if (this.ticker == 5) {
                this.capacity = 10;
                this.ticker =0;
                this.removeCapability(Status.DEPLETED);
            }
        }
    }

    public int getCapacity() {
        return capacity;
    }
}
