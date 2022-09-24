package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	public Floor() {
		super('_');
	}

	/**
	 * Allows an actor to enter the floor depending on the condition
	 *
	 * @param actor the Actor to check
	 * @return True if actor can enter, false otherwise
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		//Don't allow enemies to enter floor
		if (actor.hasCapability(Status.HOSTILE_TO_PLAYER)){
			return false;
		}
		else{
			return true;
		}
	}
}
