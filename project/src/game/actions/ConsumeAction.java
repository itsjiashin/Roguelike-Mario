package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.consumables.ConsumableItems;

/**
 * A class representing ConsumeAction, extends Action class
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 */
public class ConsumeAction extends Action {

    /**
     * Attribute to store the item calling ConsumeAction
     */
    private ConsumableItems consumableItems;

    /**
     * Constructor for ConsumeAction class
     *
     * @param consumable ConsumableItem that is calling ConsumeAction
     */
    public ConsumeAction(ConsumableItems consumable){
        consumableItems = consumable;
    }

    /**
     * ConsumeAction's execute method
     *
     * @param actor Actor consuming the consumable item
     * @param map Current GameMap
     * @return A string denoting that the actor has consumed the consumable item
     */
    public String execute(Actor actor, GameMap map){
        consumableItems.consume(actor);
        return actor + " has consumed " + consumableItems;
    }

    /**
     * A description of ConsumeAction on the menu
     *
     * @param actor
     * @return A string, to display the message on the menu for this action
     */
    public String menuDescription(Actor actor){
        String result = actor + " consumes " + consumableItems;
        if(consumableItems.hasCapability(Status.FADE)){
            result += "(" + consumableItems.getTicker() + " turns remaining)";
        }
        return result;
    }
}
