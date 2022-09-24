package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.DefaultAttackAction;
import game.actions.FireAttackAction;

/**
 * A class that allows actors to attack actors hostile to them by utilizing AttackAction
 *
 * @author Khor Jia Shin
 * @version 1.1.0
 */
public class AttackBehaviour implements Behaviour {

    /**
     * If there is a hostile actor in the 8 exits, launch an attack
     *
     * @param actor Actor currently seeing if there is any actors hostile to it in the 8 exits
     * @param map Current GameMap
     * @return AttackAction towards the hostile actor in the 8 exits, or null if there is no hostile actors
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        //For each exit of where the actor is standing
        for (Exit exit: location.getExits()){
            Location surrounding = exit.getDestination();
            //If the exit contains an actor that is hostile to enemy, launch an attack on that actor
            if(surrounding.containsAnActor()){
                Actor otherActor = surrounding.getActor();
                if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                    if(actor.hasCapability(Status.FIRE_BREATHING)){
                        return new FireAttackAction(otherActor, exit.getName());
                    }
                    else{
                        return new DefaultAttackAction(otherActor, exit.getName());
                    }
                }
            }
        }
        return null;
    }
}
