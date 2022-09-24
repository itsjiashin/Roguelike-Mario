package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;

/**
 *  A default attack action that has no extra effects to the target, extends AttackAction
 *
 * @author Khor Jia Shin
 * @version 1.1.0
 */
public class DefaultAttackAction extends AttackAction{

	/**
	 * A constructor that utilizes parent's constructor
	 *
	 * @param target The actor to be attacked
	 * @param direction Direction of target
	 */
    public DefaultAttackAction(Actor target, String direction){
        super(target,direction);
    }

	/**
	 * Hurts the target normally without any extra effects
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
			message = actor + " has instantly killed " + target;
		}
		else{
			target.hurt(damage);
			message = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		}

		String result = super.execute(actor,map);

		if (result != null){
			message += System.lineSeparator() + result;
		}
        return message;
    }
}
