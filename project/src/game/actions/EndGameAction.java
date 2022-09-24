package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special action to end the game
 *
 * @author Khor Jia Shin
 * @version 1.1.0
 */
public class EndGameAction extends Action {

    /**
     * Ends the game by removing Player from the world.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A congratulations message, as we have won the game
     */
    public String execute(Actor actor, GameMap map){
        map.removeActor(actor);
        return "Congratulations, you have won the game!";
    }

    /**
     * A description of EndGameAction
     *
     * @param actor Actor saving the princess
     * @return A string, to display the message on the menu for this action
     */
    public String menuDescription(Actor actor){
        return actor + " saves Princess Peach";
    }

}
