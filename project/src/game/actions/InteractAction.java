package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;

import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.Bottle;
import game.weapons.Wrench;


import java.util.concurrent.ThreadLocalRandom;
import java.util.Collections;
import java.util.ArrayList;


/**
 * Class for getting bottle from taod
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public class InteractAction extends Action {
    /**
     * Method to place bottle into mario's inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String, the phrase that toad will say
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String return_string;
        if(!actor.hasCapability(Status.BOTTLE_INVENTORY)) {
            actor.addItemToInventory(Bottle.getInstance());
            return_string = "Bottle has been added to Mario's inventory";
        }
        else{
            return_string = "Mario already has bottle";
        }
        return return_string;
    }

    /**
     * Menu description of InteractAction on menu
     * @param actor The actor performing the action.
     * @return String to display the action
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " gets bottle from Toad";
    }
}
