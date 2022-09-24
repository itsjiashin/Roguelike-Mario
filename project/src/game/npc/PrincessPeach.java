package game.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologue;
import game.actions.EndGameAction;
import game.items.Key;

import java.util.List;

/**
 * A class representing PrincessPeach that extends Actor class
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 */
public class PrincessPeach extends Actor  {
    /**
     * HP of PrincessPeach initialized to 9999
     */
    private static final int HIT_POINTS = 9999;

    /**
     * Keep tracks of the turns that it has been spawned
     */
    private int ticker;

    /**
     * A constructor that utilizes parent's constructor
     */
    public PrincessPeach(){
        super("Princess Peach", 'P', HIT_POINTS);
        this.ticker = 0;
    }

    /**
     * Do nothing as PrincessPeach is not required to do anything
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        ticker += 1;
        if(ticker%2 == 0){
            Monologue.getInstance().peachSpeak();
        }
        return new DoNothingAction();
    }

    /**
     * Figure out what actions that could be done to PrincessPeach.
     * @param otherActor the Actor that might be interacting with PrincessPeach
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList that contains all the action that could be done to PrincessPeach
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map){
        ActionList actions = new ActionList();
        for (Item item: otherActor.getInventory()){
            if (item.toString().equals(Key.NAME)){
                actions.add(new EndGameAction());
            }
        }
        return actions;
    }


}
