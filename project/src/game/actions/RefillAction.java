package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.consumables.ConsumableItems;
import game.grounds.Fountain;
import game.grounds.HealthFountain;
import game.grounds.PowerFountain;
import game.items.Bottle;

/**
 * Class for refilling water into bottle
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public class RefillAction extends Action {

    /**
     * Declaring the variable
     */
    private ConsumableItems water;

    /**
     *
     * @param water water that is going to be refilled
     */
    public RefillAction(ConsumableItems water){
        this.water = water;
    }

    /**
     * Execute method for RefillAction
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String return_string;
        // location of the actor is at
        Location location = map.locationOf(actor);
        int max_capacity = Fountain.MAX_CAPACITY;
        // Gets the capacity of the fountains
        int capacity;
        if(location.getGround().hasCapability(Status.HEALTH_FOUNTAIN)) {
            capacity = HealthFountain.getInstance().getCapacity();
        }
        else {
            capacity = PowerFountain.getInstance().getCapacity();
        }
        String string =" (" + capacity + "/" + max_capacity +")";
        // If actor has bottle in inventory and fountain is not depleted, add water into bottle
        if(actor.hasCapability(Status.BOTTLE_INVENTORY)&&!location.getGround().hasCapability(Status.DEPLETED)){
            Bottle.getInstance().addWater(water);
            location.getGround().addCapability(Status.BEING_REFILLED);
            return_string = actor + " refills bottle with " + water.toString() + string + location.getGround().capabilitiesList().toString();
        }
        else{
            return_string = "Fountain has depleted, come back later.";
        }
        return return_string;
    }

    /**
     * Menu description of refill action
     * @param actor The actor performing the action.
     * @return String
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Mario refills "+ water.toString();
    }
}
