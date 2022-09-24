package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;

/**
 * an action that is used by the player to reset the game
 */
public class ResetGameAction extends Action {

    /**
     * the function that is called when the action is used
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return player's name + " resets the game!"
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //get reset manager
        ResetManager resetManager = ResetManager.getInstance();

        //call resetInstance for every Resettable
        resetManager.run();

        return actor.toString() + " resets the game!";
    }

    /**
     * a function that return the menu discription
     * @param actor The actor performing the action.
     * @return "Reset the game."
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game.";
    }

    /**
     * a function used to select the hotkey of an action
     * @return "r"
     */
    @Override
    public String hotkey() {
        return "r";
    }
}
