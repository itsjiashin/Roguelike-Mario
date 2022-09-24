package game.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import game.actions.AttackAction;
import game.actions.DefaultAttackAction;
import game.actions.FireAttackAction;
import game.behaviours.*;
import game.grounds.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 *  An enemy class that is a child class of Actor class, and is used to represent actors that are hostile to the player
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 *
 */
public abstract class Enemy extends Actor implements Resettable {
    /**
     * Protected attribute of a hashmap to store enemy's behaviours
     */
    protected final Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * To check if enemy needs to be reset
     */
    protected boolean mustResetNextTurn = false;
    /**
     * Random number generator
     */
    protected Random random = new Random();

    /**
     * Keep tracks of the turns that it has spawned
     */
    protected int ticker;

    /**
     * The base damage of an enemy
     */
    protected int damage;
    /**
     * A constructor for the Enemy class, which uses parent's class constructor, as well as add new capabilities and behaviours
     */
    public Enemy(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        //All enemies are hostile to players
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        this.addCapability(Status.RESETTABLE);
        //All enemies have WanderBehaviour as their least important priority
        this.behaviours.put(10, new WanderBehaviour());
        //All enemies have AttackBehaviour
        this.behaviours.put(7, new AttackBehaviour());
        this.behaviours.put(9, new DrinkBehaviour());
        this.ticker = 0;
        registerInstance();
    }

    /**
     * Change mustResetNextTurn to true
     */
    @Override
    public void resetInstance() {
        mustResetNextTurn = true;
    }

    /**
     * Allows other actors to perform actions to an enemy
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if(otherActor.hasCapability(Status.FIRE_BREATHING))
            actions.add(new FireAttackAction(this,direction));
            else{
                actions.add(new DefaultAttackAction(this,direction));
            }
            //This means that the enemy is within hostile actor's range, so follow that hostile actor
            this.behaviours.put(8, new FollowBehaviour(otherActor));
        }
        return actions;
    }

    /**
     * Figure out what to do next.
     *
     * @param actions A list of actions
     * @param lastAction An action
     * @param map Current GameMap
     * @param display Current display
     * @return An action that the enemy will perform in this turn
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Reset enemies after ResetAction is executed
     * @param map game map
     * @return mustDie
     */
    protected boolean resetUpdate(GameMap map){
        if (mustResetNextTurn){
            map.removeActor(this);
            return true;
        }
        return false;
    }
}
