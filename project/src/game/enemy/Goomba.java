package game.enemy;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologue;
import game.ResetManager;
import game.Status;
import game.behaviours.Behaviour;
import game.actions.SuicideAction;

import java.util.Random;

/**
 * A class representing a Goomba enemy, extends Enemy class.
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 */
public class Goomba extends Enemy {
	/**
	 * Percentage of Goomba suiciding
	 */
	private static final int CHANCE = 10;


	/**
	 * A constructor for Goomba class, which uses parent's class' constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		damage = 10;
	}

	/**
	 * Figure out what to do next. Chance to suicide is also added.
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
			Monologue.getInstance().goombaSpeak();
		}
		if (resetUpdate(map)) {
			return new DoNothingAction();
		}
		//If goomba is unlucky, it will suicide
		if (random.nextInt(100) < CHANCE){
			ResetManager.getInstance().cleanUp(this);
			return new SuicideAction();
		}
		for(Behaviour Behaviour : behaviours.values()) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

	/**
	 *  Allows Goomba to kick with 10 damage
	 *
	 * @return An IntrinsicWeapon object
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon(){
		if(this.hasCapability(Status.POWER_WATER)) {
			damage += 15;
		}
		return new IntrinsicWeapon(damage, "kick");
	}

}
