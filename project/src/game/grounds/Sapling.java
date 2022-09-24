package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;

/**
 * Mature is one of the 3 stages of a tree
 * it is created from growing a Sprout
 */
public class Sapling extends Tree{

    //constants
    private final static int COIN_VALUE = 20;
    private final static int COIN_SPAWNING_CHANCE = 10;
    private final static int NEEDED_GROWTH_TIME = 10;

    //variables
    private int age;


    /**
     * Constructor.
     */
    public Sapling() {
        super('t', "Sapling", 80, 20);
        age = 0;
    }

    /**
     * Ground and its children can also experience the joy of time.
     * @param location The location of the Sapling tree
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        //used to end the turn early, (e.g. when a tree dies at the start of a turn)
        if (endEarly){
            endEarly = false;
            return;
        }

        //spawning a coin
        if (random.nextInt(100) < COIN_SPAWNING_CHANCE){
            location.addItem(new Coin(COIN_VALUE));
        }

        age++;

        if (age >= NEEDED_GROWTH_TIME){
            die();
            attemptSpawnAFireFlower(location);
            location.setGround(new Mature());
        }

    }
}
