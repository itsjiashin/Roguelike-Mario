package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.Status;

/**
 * A class representing the PowerStar object, extends ConsumableItem class
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 */
public class PowerStar extends ConsumableItems {

    /**
     * A public attribute that stores the value of Power Star
     */
    public static final int VALUE = 600;
    /**
     * Constructor for PowerStar class
     */
    public PowerStar(){
        super("Power Star", '*');
        this.setTicker(10);
        this.addCapability(Status.FADE);
    }

    /**
     * PowerStar's consume method
     *
     * @param actor Actor consuming the power star
     */
    @Override
    public void consume(Actor actor){
        super.consume(actor);
        actor.heal(200);
        actor.addCapability(Status.INVINCIBLE);
        this.setTicker(10);
    }

    /**
     * PowerStar's tick method when in player's inventory
     *
     * @param currentLocation current location
     * @param actor Actor who has the power star in it's inventory
     */
    public void tick(Location currentLocation, Actor actor){
        super.tick(currentLocation, actor);
        setTicker(getTicker()-1);

        if (getTicker() == 0){
            if(actor.hasCapability(Status.INVINCIBLE)){
                actor.removeCapability(Status.INVINCIBLE);
                actor.removeItemFromInventory(this);
            }
            else{
                actor.removeItemFromInventory(this);
            }
        }
    }

    /**
     * PowerStar's tick method when on the ground
     *
     * @param currentLocation current location
     */
    public void tick(Location currentLocation){
        setTicker(getTicker()-1);
        if (getTicker() == 0){
            currentLocation.removeItem(this);
        }
    }

}
