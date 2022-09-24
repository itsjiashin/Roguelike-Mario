package game.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologue;
import game.Status;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.items.Key;

/**
 * A class representing a Bowser enemy, extends Enemy class
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 */
public class Bowser extends Enemy{

    /**
     * Bowser's original position in the lava zone along the x-axis
     */
    public static final int ORIGINAL_X_POSITION = 24;

    /**
     * Bowser's original position in the lava zone along the y-axis
     */
    public static final int ORIGINAL_Y_POSITION = 0;

    /**
     * A constructor that utilizes parent's constructor
     */
    public Bowser(){
        super("Bowser", 'B', 500);
        //Bowser will have the capability to breathe fire (Attacks with fire)
        this.addCapability(Status.FIRE_BREATHING);
        this.behaviours.clear();
        this.behaviours.put(7, new AttackBehaviour());
        this.damage = 80;
        //Bowser will always have a key in its inventory
        this.addItemToInventory(new Key());
    }

    /**
     * Allows Bowser to punch with 80 damage
     *
     * @return An IntrinsicWeapon object
     */
    protected IntrinsicWeapon getIntrinsicWeapon(){
        if(this.hasCapability(Status.POWER_WATER)) {
            damage += 15;
        }
        return new IntrinsicWeapon(damage, "punches");
    }

    /**
     * Moves Bowser back to its position and heal to maximum after ResetAction is executed
     *
     * @param map Current GameMap
     * @return true if to be reset, false otherwise
     */
    protected boolean resetUpdate(GameMap map){
        Location location = map.at(ORIGINAL_X_POSITION, ORIGINAL_Y_POSITION);
        if (mustResetNextTurn) {
            if (!location.containsAnActor()){
                map.moveActor(this, location);
                heal(getMaxHp());
                behaviours.clear();
                behaviours.put(7, new AttackBehaviour());
                mustResetNextTurn = false;
                return true;
            }
            else{
                //If there's an actor that's not Bowser at Bowser's original spawn point
                if (map.getActorAt(location) != this){
                    //Bowser will squash the actor to death
                    map.removeActor(map.getActorAt(location));
                    map.moveActor(this, location);
                    heal(getMaxHp());
                    behaviours.clear();
                    behaviours.put(7, new AttackBehaviour());
                    mustResetNextTurn = false;
                    return true;
                }
                else{
                    //Don't need to clear behaviours and add AttackBehaviour again since it means player is at spawn point's adjacent square
                    //or player has not approached Bowser yet
                    heal(getMaxHp());
                    mustResetNextTurn = false;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Figure out what to do next.
     *
     * @param actions A list of actions
     * @param lastAction An action
     * @param map Current GameMap
     * @param display Current display
     * @return An action that the enemy will perform in this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        resetUpdate(map);
        if(this.hasCapability(Status.POWER_WATER)){
            this.getIntrinsicWeapon();
            this.removeCapability(Status.POWER_WATER);
        }
        ticker += 1;
        if(ticker%2 == 0){
            Monologue.getInstance().bowserSpeak();
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
