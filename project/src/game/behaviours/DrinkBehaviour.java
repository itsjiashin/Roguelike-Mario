package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeWaterAction;
import game.actions.DrinkAction;
import game.consumables.HealingWater;
import game.consumables.PowerWater;
import game.grounds.Fountain;
import game.grounds.HealthFountain;

/**
 * Class for drinking behaviour for actor except for mario
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public class DrinkBehaviour implements Behaviour {

    /**
     * If actor is standing on fountain, then drink from it.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return Consume water action if actor is an enemy and on fountains
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        if(actor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            if (location.getGround().hasCapability(Status.HEALTH_FOUNTAIN)) {
                return new ConsumeWaterAction(new HealingWater());
            }
            else if(location.getGround().hasCapability(Status.POWER_FOUNTAIN)){
                return new ConsumeWaterAction(new PowerWater());
            }
        }
        return null;
    }
}
