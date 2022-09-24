package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.consumables.ConsumableItems;
import game.items.Bottle;

/**
 * Class for drinking water from the bottle
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public class DrinkAction extends Action {

    /**
     * Executes the drinking action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ConsumableItems water = Bottle.getInstance().removeWater();
        water.consume(actor);
        return actor + "has consumed " + water.toString();
    }

    /**
     * Description of DrinkAction in the menu
     * @param actor The actor performing the action.
     * @return String
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Mario consumes bottle [" + Bottle.getInstance().getWater() +"]";
    }
}
