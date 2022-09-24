package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.consumables.ConsumableItems;
import game.grounds.Fountain;
import game.grounds.HealthFountain;
import game.grounds.PowerFountain;

/**
 * Class for Actors to consume water from fountain that extends action
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public class ConsumeWaterAction extends Action {

    /**
     * Declaring the variable
     */
    private ConsumableItems water;

    /**
     * Constructor for this action
     * @param water the water that is going to be consumed
     */
    public ConsumeWaterAction(ConsumableItems water){
        this.water = water;
    }

    /**
     * Execute method for Consume
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //Gets the ground that the actor is standing on
        Ground ground = map.locationOf(actor).getGround();
        //Gets the capacity of the fountains
        int capacity;
        if(ground.hasCapability(Status.HEALTH_FOUNTAIN)) {
            capacity = HealthFountain.getInstance().getCapacity();
        }
        else {
            capacity = PowerFountain.getInstance().getCapacity();
        }
        int max_capacity = Fountain.MAX_CAPACITY;
        String string =" (" + capacity + "/" + max_capacity +")";
        String return_string;
        // If fountain is not depleted actor will consume the water
        if(!ground.hasCapability(Status.DEPLETED)) {
            water.consume(actor);
            ground.addCapability(Status.BEING_CONSUMED);
            return_string = actor + " has consumed " + water.toString() + string;
        }
        else{
            return_string = "Fountain has depleted, come back later.";
        }
        return return_string ;
    }

    /**
     * Description of ConsumeWaterAction in the menu
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
