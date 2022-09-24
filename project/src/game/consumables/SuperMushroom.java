package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.Player;
import game.Status;
import game.actions.ConsumeAction;

/**
 * A class representing the SuperMushroom object, extends ConsumableItems class
 *
 * @author Khor JIa Shin
 * @version 1.0.0
 */
public class SuperMushroom extends ConsumableItems{

    /**
     * A public attribute that stores the value of Super Mushroom
     */
    public static final int VALUE = 400;

    /**
     * Constructor for SuperMushroom
     */
    public SuperMushroom(){
        super("Super Mushroom", '^');
    }

    /**
     * SuperMushroom's consume method
     *
     * @param actor Actor consuming the super mushroom
     */
    public void consume(Actor actor){
        super.consume(actor);
        actor.increaseMaxHp(50);
        actor.addCapability(Status.TALL);
        actor.removeItemFromInventory(this);
    }
}
