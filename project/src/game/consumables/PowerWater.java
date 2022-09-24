package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;

/**
 * A class representing the Power Water object, extends ConsumableItems class
 *
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public class PowerWater extends ConsumableItems{

    private final int DAMAGE = 15;

    /**
     * Constructor for Power Water
     */
    public PowerWater() {
        super("Power Water", 'P');
    }

    /**
     * Power Water consume method
     * @param actor Actor consuming the item
     */
    @Override
    public void consume(Actor actor) {
        super.consume(actor);
        actor.addCapability(Status.POWER_WATER);
    }
}
