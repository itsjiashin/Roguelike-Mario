package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A class representing the Healing Water object, extends ConsumableItems class
 *
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public class HealingWater extends ConsumableItems{

    /**
     * Constructor for healing water that utilizes parent class
     */
    public HealingWater() {
        super("Healing Water", 'W');
    }

    /**
     * Healing Water consume method
     * @param actor Actor consuming the item
     */
    @Override
    public void consume(Actor actor) {
        super.consume(actor);
        actor.heal(50);
    }
}
