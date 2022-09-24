package game.consumables;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * A class representing the FireFlower object, extends ConsumableItems class
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 */
public class FireFlower extends ConsumableItems {

    /**
     * To check whether FireFlower has been consumed or not
     */
    private boolean consumed = false;

    /**
     * A constructor that utilizes parent's constructor, and ticker is set to 20
     */
    public FireFlower(){
        super("Fire Flower", 'f');
        this.setTicker(20);
    }

    /**
     * FireFlower's consume method
     *
     * @param actor Actor consuming the fire flower
     */
    public void consume(Actor actor){
        super.consume(actor);
        actor.addCapability(Status.FIRE_BREATHING);
        consumed = true;
    }

    /**
     * FireFlower's tick method when in player's inventory
     *
     * @param currentLocation current location
     * @param actor Actor who has the fire flower in its inventory
     */
    public void tick(Location currentLocation, Actor actor){
        super.tick(currentLocation, actor);

        if(actor.hasCapability(Status.FIRE_BREATHING) && consumed){
            setTicker(getTicker()-1);
            if(getTicker() == 0){
                actor.removeCapability(Status.FIRE_BREATHING);
                actor.removeItemFromInventory(this);
            }
        }
    }


}
