package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

import java.util.Random;

/**
 * an action a player can use to move to a HighGround block
 */
public class JumpAction extends Action {

    private String direction;
    private String highGroundName;
    private Location newLocation;
    private int chance;
    private int fallDamage;

    /**
     * constructor.
     *
     * @param highGroundName the name of the high ground the player will try to jump to
     * @param direction the direction of the high ground the player will try to jump to
     * @param location the location of the high ground the player will try to jump to
     * @param chance the chance of success of the jump
     * @param fallDamage the damage taken by the player if the jump fails
     */
    public JumpAction(String highGroundName, String direction, Location location, int chance, int fallDamage){
        this.highGroundName = highGroundName;
        this.direction = direction;
        this.newLocation = location;
        this.chance = chance;
        this.fallDamage = fallDamage;
    }


    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random random = new Random();
        if (actor.hasCapability(Status.TALL) || random.nextInt(100) < chance){
            map.moveActor(actor, newLocation);
            return String.format("%s jumped to the %s",actor.toString(), highGroundName);
        }
        else {
            actor.hurt(fallDamage);
            if(!actor.isConscious()){
                map.removeActor(actor);
            }
            return String.format("%s fell on his face and took %d damage", actor.toString(), fallDamage);
        }
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action (most likely the player)
     * @return the text we put on the menu it will be something like "player jump to wall(3,10) at North"
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s jump to %s(%d,%d) at %s", actor.toString(), highGroundName, newLocation.x(), newLocation.y(), direction);
    }
}
