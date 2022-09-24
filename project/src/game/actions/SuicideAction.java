package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

/**
 * Special action for Goomba to suicide
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 */
public class SuicideAction extends Action {
    /**
     * Allowing Goomba to suicide peacefully
     *
     * @param actor Goomba that is going to go to heaven
     * @param map Current GameMap
     * @return A string, denoting that the goomba at a specific location has died
     */
    @Override
    public String execute(Actor actor, GameMap map){
        String result = menuDescription(actor);
        //Remove actor from the map
        map.removeActor(actor);
        return result;
    }

    /**
     * A description of SuicideAction
     *
     * @param actor Actor doing the suiciding
     * @return A string, to display the message on the menu for this action
     */
    @Override
    public String menuDescription(Actor actor){
        return "Goomba has suicided";
    }

}
