package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.ResetManager;
import game.Resettable;
import game.actions.JumpAction;
import game.Status;
import game.items.Coin;

/**
 * an abstract class for ground types the player has to jump
 * to them if he wants to stand on them
 */
public abstract class HighGround extends Ground {

    protected String name;
    protected int chance;
    protected int fallDamage;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     * @param name the name of the high ground
     * @param chance the chance of a successful jump
     * @param fallDamage the damage taken if the jump fails
     */
    public HighGround(char displayChar, String name, int chance, int fallDamage) {
        super(displayChar);
        this.name = name;
        this.chance = chance;
        this.fallDamage = fallDamage;
    }

    /**
     * getter for the ground's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * a function to check if an actor can just walk to it normally like any other ground
     * @param actor the Actor to check
     * @return true if yes, false if not
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.INVINCIBLE) || actor.hasCapability(Status.FLY)){
            return true;
        }
        return false;
    }

    /**
     * a function that will block thrown objects
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * Returns an Action list. that is either empty or contains jump action if
     * applicable
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();

        if (actor.hasCapability(Status.CAN_JUMP) && !actor.hasCapability(Status.INVINCIBLE) && !location.containsAnActor()){
            actionList.add(new JumpAction(name, direction, location, chance, fallDamage));
        }

        return actionList;
    }

    /**
     *  HighGround's tick method
     * @param location The location of the Ground
     */
    public void tick(Location location){
        if (location.containsAnActor()){
            Actor actor = location.getActor();

            //if a resettable high ground turns to dirt, clean it up
            if (actor.hasCapability(Status.INVINCIBLE)){
                if (this.hasCapability(Status.RESETTABLE)){
                    ResetManager.getInstance().cleanUp((Resettable) this);
                }
                location.setGround(new Dirt());
                location.addItem(new Coin(5));
            }

        }
    }

}
