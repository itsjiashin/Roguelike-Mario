package game.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologue;
import game.Status;
import game.actions.AttackAction;
import game.actions.BreakShellAction;
import game.actions.DefaultAttackAction;
import game.actions.FireAttackAction;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.consumables.SuperMushroom;
import game.weapons.Wrench;

/**
 * A class representing a Koopa enemy, extends Enemy class
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 */
public class Koopa extends Enemy{

    /**
     * An attribute denoting whether dormant state has been processed or not
     */
    boolean processedDormantState = false;



    /**
     * A constructor for the Koopa class, which uses parent's class' constructor
     */
    public Koopa(){
        super("Koopa", 'K', 100);
        //All Koopas will have capability of being not dormant
        this.addCapability(Status.NOT_DORMANT);
        this.damage = 30;
    }

    public Koopa(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.addCapability(Status.NOT_DORMANT);
        this.damage = 30;
    }


    /**
     * Allows Koopa to punch with 30 damage
     *
     * @return An IntrinsicWeapon object
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){
        if(this.hasCapability(Status.POWER_WATER)) {
            damage += 15;
        }
        return new IntrinsicWeapon(damage, "punches");
    }


    /**
     * Gets the display character of Koopa
     *
     * @return A character representing Koopa on the display
     */
    @Override
    public char getDisplayChar(){
        return this.hasCapability(Status.DORMANT) ? 'D' : super.getDisplayChar();
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
        if(!this.hasCapability(Status.DORMANT) && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if(otherActor.hasCapability(Status.FIRE_BREATHING)){
                actions.add(new FireAttackAction(this,direction));
            }
            else {
                actions.add(new DefaultAttackAction(this, direction));
            }
            //This means that Koopa is within hostile actor's range, so follow that hostile actor
            this.behaviours.put(9, new FollowBehaviour(otherActor));
        }
        else if (this.hasCapability(Status.DORMANT) && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            //If the hostile actor is holding a wrench, will be able to break a dormant Koopa's shell
            if(otherActor.getWeapon().toString().equals(Wrench.NAME)){
                actions.add(new BreakShellAction(this,direction));
            }
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
        if(this.hasCapability(Status.POWER_WATER)){
            this.getIntrinsicWeapon();
            this.removeCapability(Status.POWER_WATER);
        }
        ticker += 1;
        if(ticker%2 == 0){
            Monologue.getInstance().koopaSpeak();
        }
        if (resetUpdate(map)) {
            return new DoNothingAction();
        }
        //If the Koopa is dormant, need to clear all of it's behaviours since it's not supposed to do anything
        if (this.hasCapability(Status.DORMANT) && processedDormantState == false){
            behaviours.clear();
            processedDormantState = true;
        }

        for(game.behaviours.Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }


}
