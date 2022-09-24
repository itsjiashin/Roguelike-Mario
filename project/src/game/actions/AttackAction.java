package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.ResetManager;
import game.Resettable;
import game.Status;

/**
 * Abstract class that is inherited by different attacks
 *
 * @author Khor Jia Shin
 * @version 1.2.0
 */
public abstract class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Message after attack is executed
	 */
	protected String message = "";
	/**
	 * Constructor.
	 * 
	 * @param target the Actor to be attacked
	 * @param direction Direction of target
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Deals with enemy's death after getting hurt by different types of attacks available
	 *
	 * @param actor Actor doing the attacking
	 * @param map Current GameMap
	 * @return A string, denoting the process that took place
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (!target.isConscious()) {
			//won't instantly kill the target, but make it dormant
			if(target.hasCapability(Status.NOT_DORMANT) && !actor.hasCapability(Status.INVINCIBLE)){
				target.removeCapability(Status.NOT_DORMANT);
				target.addCapability(Status.DORMANT);
				return target + " is now dormant";
			}
			else {
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove from resetManager
				if (target.hasCapability(Status.RESETTABLE)) {
					ResetManager.getInstance().cleanUp((Resettable) target);
				}
				// remove actor
				map.removeActor(target);
				return target + " is now killed";
			}
		}
		return null;
	}

	/**
	 * Menu description of the attack made
	 *
	 * @param actor Actor doing the attacking
	 * @return A string, to display the message on the menu for this action
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
