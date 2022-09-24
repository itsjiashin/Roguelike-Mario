package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * a class that represents a lava block
 */
public class Lava extends Ground {

    /**
     * Lava damage whenever an actor is on it
     */
    public static int LAVA_DAMAGE = 15;

    /**
     * constructor
     */
    public Lava() {
        super('L');
    }

    /**
     * a function that return if an actor can enter
     * @param actor the Actor to check
     * @return true if actor can enter, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.HOSTILE_TO_PLAYER)){
            return false;
        }
        return true;
    }

    /**
     * a function that will be called every turn
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        Actor actor = location.getActor();
        if (actor != null){
            actor.hurt(LAVA_DAMAGE);
            if(!actor.isConscious()){
                location.map().removeActor(actor);
            }
        }
    }
}
