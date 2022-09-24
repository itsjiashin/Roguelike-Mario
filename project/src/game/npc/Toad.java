package game.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import game.Monologue;
import game.actions.BuyAction;
import game.actions.InteractAction;
import game.consumables.PowerStar;
import game.consumables.SuperMushroom;
import game.weapons.Wrench;

/**
 * A class representing toad actor that extends actor class.
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public class Toad extends Actor{

    /**
     * Initialise the HP of toad to 9999
     */
    private static final int HIT_POINTS = 9999;

    /**
     * Keep tracks of the turns that it has been spawned
     */
    private int ticker;

    /**
     * Declaring action
     */
    private InteractAction interactAction;


    /**
     * Constructor for Toad
     */
    public Toad() {
        super("Toad", 'O', HIT_POINTS);
        this.interactAction = new InteractAction();
        this.ticker = 0;
    }

    /**
     * Do nothing as toad is not required to do anything
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        ticker += 1;
        if(ticker%2 == 0){
            Monologue.getInstance().toadSpeak();
        }
        return new DoNothingAction();
    }

    /**
     * Figure out what actions that could be done to toad.
     * @param otherActor the Actor that might be interacting with Toad
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList that contains all the action that could be done to toad
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(interactAction);
        // adds new buyactions that contains a new item  and its value
        actions.add(new BuyAction(new Wrench(), Wrench.VALUE));
        actions.add(new BuyAction(new SuperMushroom(), SuperMushroom.VALUE));
        actions.add(new BuyAction(new PowerStar(), PowerStar.VALUE));
        return actions;
    }


}
