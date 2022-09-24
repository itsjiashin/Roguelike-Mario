package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.items.Fire;

/**
 * A attack action that not only hurts the target, but also drops a fire at target's ground. Extends AttackAction
 *
 * @author Khor Jia Shin
 * @version 1.1.0
 */
public class FireAttackAction extends  AttackAction{

    /**
     * A constructor that utilizes parent's constructor
     *
     * @param target The actor to be attacked
     * @param direction Direction of target
     */
    public FireAttackAction(Actor target, String direction){
        super(target, direction);
    }

    /**
     * Hurts the target, as well as dropping a fire at target's ground at each successful attack.
     *
     * @param actor Actor doing the attacking
     * @param map Current GameMap
     * @return A string, denoting the processes that took place
     */
    public String execute(Actor actor, GameMap map){
        Weapon weapon = actor.getWeapon();

        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }

        int damage = weapon.damage();
        //If actor has invincibility effect, can one-shot the target
        if (actor.hasCapability(Status.INVINCIBLE)){
            target.resetMaxHp(0);
            //drop a fire at target's ground
            this.dropFire(map);
            message = actor + " has instantly killed " + target + ", and laid fire on the ground";
        }
        else{
            target.hurt(damage);
            //drop a fire at target's ground
            this.dropFire(map);
            message = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage, and laid fire on the ground";
        }

        String result = super.execute(actor,map);

        if (result != null){
            message += System.lineSeparator() + result;
        }
        return message;
    }

    /**
     * A description of FireAttackAction on the menu
     *
     * @param actor Actor doing the attacking
     * @return A string, to display the message on the menu for this action
     */
    public String menuDescription(Actor actor){
        return super.menuDescription(actor) + " with fire!";
    }

    /**
     * Drops a fire at target's ground
     *
     * @param map Current GameMap
     */
    public void dropFire(GameMap map){
        Location location = map.locationOf(target);
        location.addItem(new Fire());
    }
}
