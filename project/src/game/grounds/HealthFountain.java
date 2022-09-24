package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.Wallet;
import game.actions.RefillAction;
import game.consumables.HealingWater;

import static game.Status.HOSTILE_TO_ENEMY;

/**
 * A class extending Fountain class
 *
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public class HealthFountain extends Fountain{

    /**
     * Declare the instance of HealthFountain
     */
    private static HealthFountain instance;

    /**
     * Constructor
     */
    private HealthFountain() {
        super('H',0,10);
        this.addCapability(Status.HEALTH_FOUNTAIN);
    }

    /**
     * Gets the instance of HealthFountain
     * @return instance
     */
    public static HealthFountain getInstance(){
        if(instance == null){
            instance = new HealthFountain();
        }
        return instance;
    }

    /**
     * The actions that can be done to the ground
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return An action list of allowable actions to the ground
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if(location.containsAnActor()) {
            if (location.getActor().hasCapability(HOSTILE_TO_ENEMY)) {
                actionList.add(new RefillAction(new HealingWater()));
            }
        }
        return actionList;
    }


}
