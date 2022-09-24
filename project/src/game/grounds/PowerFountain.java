package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.RefillAction;
import game.consumables.PowerWater;

import static game.Status.HOSTILE_TO_ENEMY;

/**
 * A class extending Fountain class
 *
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public class PowerFountain extends Fountain{

    /**
     * Declare the instance of PowerFountain
     */
    private static PowerFountain instance;

    /**
     * Constructor
     */
    private PowerFountain() {
        super('P',0,10);
        this.addCapability(Status.POWER_FOUNTAIN);
    }

    /**
     * Gets the instance of PowerFountain
     * @return instance
     */
    public static PowerFountain getInstance(){
        if(instance == null){
            instance = new PowerFountain();
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
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if(location.containsAnActor()) {
            if (location.getActor().hasCapability(HOSTILE_TO_ENEMY)) {
                actionList.add(new RefillAction(new PowerWater()));
            }
        }
        return actionList;
    }
}
