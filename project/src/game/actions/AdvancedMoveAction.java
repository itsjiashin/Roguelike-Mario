package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * this class is an action class that works in a similar way to MovaActorAction but it has
 * the added functionality of sending a signal
 */
public class AdvancedMoveAction extends Action {

    private String targetName;
    private Location targetLocation;
    private SimpleFunction signal;


    /**
     * constructor
     * @param targetLocation the location the actor will be moved to
     * @param targetName the name of the location the actor will be moved to
     */
    public AdvancedMoveAction(Location targetLocation ,String targetName){
        this.targetName = targetName;
        this.targetLocation = targetLocation;
        this.signal = null;
    }

    /**
     * a setter for the signal variable
     * @param signal the new signal
     */
    public void setSignal(SimpleFunction signal) {
        this.signal = signal;
    }

    /**
     * this is a function that is called when the action is executed
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return "{actor's name} moved to {new location name}'
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (signal != null){
            signal.run();
        }

        map.moveActor(actor, targetLocation);



        return actor + " moved to "+ targetName;
    }

    /**
     * a function the engine uses to know how to display the action on the screen
     * @param actor The actor performing the action.
     * @return "Move to {new location name}"
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Move to "+targetName;
    }
}
