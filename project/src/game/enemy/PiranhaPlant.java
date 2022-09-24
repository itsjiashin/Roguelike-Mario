package game.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologue;
import game.Status;
import game.actions.DefaultAttackAction;
import game.actions.FireAttackAction;
import game.behaviours.AttackBehaviour;

/**
 * A class representing a PiranhaPlant enemy, extends Enemy class
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 */
public class PiranhaPlant extends Enemy{


    public PiranhaPlant(){
        super("Piranha plant", 'Y', 150);
        this.behaviours.clear();
        this.behaviours.put(7, new AttackBehaviour());
        this.damage = 90;
    }

    /**
     * Allows PiranhaPlant to chomp with 90 damage
     *
     * @return An IntrinsicWeapon object
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        if(this.hasCapability(Status.POWER_WATER)) {
            damage += 15;
        }
        return new IntrinsicWeapon(damage, "chomps");
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
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if (otherActor.hasCapability(Status.FIRE_BREATHING))
                actions.add(new FireAttackAction(this, direction));
            else {
                actions.add(new DefaultAttackAction(this, direction));
            }
        }
        return actions;
    }

    /**
     * Increases PiranhaPlants' hp by 50, and heal to maximum
     *
     * @param map Current GameMap
     * @return true if to be reset, false otherwise
     */
    protected boolean resetUpdate(GameMap map){
        if (mustResetNextTurn){
            this.increaseMaxHp(50);
            mustResetNextTurn = false;
            return true;
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
        if(this.hasCapability(Status.POWER_WATER)){
            this.getIntrinsicWeapon();
            this.removeCapability(Status.POWER_WATER);
        }
        ticker += 1;
        if(ticker%2 == 0){
            Monologue.getInstance().piranhaPlantSpeak();
        }
        resetUpdate(map);
        return super.playTurn(actions, lastAction, map, display);
    }

}
