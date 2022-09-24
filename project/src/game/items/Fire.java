package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Used to represent a fire, that will hurt actors that are stepping on it
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 */
public class Fire extends Item {

    /**
     * Ticker counter initialized to 3
     */
    private int ticker = 3;

    /**
     * A constructor that utilizes parent's constructor
     */
    public Fire(){
        super("Fire", 'v', false);
    }

    /**
     * Tick method when on the ground.
     *
     * @param location location of fire
     */
    public void tick(Location location){
        if (location.containsAnActor()){
            Actor actor = location.getActor();
            actor.hurt(20);
            //In our implementation, if someone died to fire, all the items in their inventory will be burned, dropping nothing(like Minecraft)
            if (!actor.isConscious()){
                location.map().removeActor(actor);
            }
        }
        ticker -=1;
        if (ticker == 0){
            location.removeItem(this);
        }
    }
}
