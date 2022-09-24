package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.ResetManager;
import game.Resettable;
import game.Status;
import game.consumables.FireFlower;

import java.util.Random;

/**
 * an abstract class the tree stages inherit from it
 */
public abstract class Tree extends HighGround implements Resettable{

    //constants
    protected final static int CHANCE_TO_RESET = 50;
    protected final static int CHANCE_TO_SPAWN_A_FLOWER = 50;

    //variables
    protected Random random;

    private boolean tryToResetNextTurn = false;

    protected boolean endEarly = false;

    /**
     * Constructor.
     * @param displayChar the display character of the tree
     * @param name name of the tree
     * @param chance chance of successfully jumping if the player attempt to jump to the tree
     * @param fallDamage the fall damage taken if the jump fails
     */
    public Tree(char displayChar, String name, int chance, int fallDamage) {
        super(displayChar, name, chance, fallDamage);
        random = new Random();
        registerInstance();
    }


    /**
     * a method called every turn
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if (tryToResetNextTurn){
            if (random.nextInt(100) < CHANCE_TO_RESET){
                location.setGround(new Dirt());
                endEarly = true;
            }
        }
    }

    /**
     * a function that will be called when attempting to spawn a fire flower
     * it has a ${CHANCE_TO_SPAWN_A_FLOWER}% change of success
     * @param location the location of the tree
     */
    protected void attemptSpawnAFireFlower(Location location){
        if (random.nextInt(100) < CHANCE_TO_SPAWN_A_FLOWER) {
            location.addItem(new FireFlower());
        }
    }

    /**
     * a method used when a tree dies, to clean it up
     */
    protected void die(){
        ResetManager.getInstance().cleanUp(this);
    }

    /**
     * a method called when the game resets
     */
    @Override
    public void resetInstance() {
        tryToResetNextTurn = true;
    }

}
