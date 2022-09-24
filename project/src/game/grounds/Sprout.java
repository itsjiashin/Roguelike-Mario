package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.enemy.Goomba;

/**
 * Mature is one of the 3 stages of a tree
 * it is created when a Mature Tree starts to spawn them in its surrounding
 */
public class Sprout extends Tree{

    //constants
    private final static int GOOMBA_SPAWNING_CHANCE = 10;
    private final static int NEEDED_GROWTH_TIME = 10;

    //variables
    private int age;

    /**
     * Constructor.
     */
    public Sprout() {
        super('+', "Sprout", 90, 10);
        age = 0;
    }

    /**
     * Ground and its children can also experience the joy of time.
     * @param location The location of the Sprout tree
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        //used to end the turn early, (e.g. when a tree dies at the start of a turn)
        if (endEarly){
            endEarly = false;
            return;
        }

        //spawning a goomba
        if (!location.containsAnActor()){
            if (random.nextInt(100) < GOOMBA_SPAWNING_CHANCE) {
                location.addActor(new Goomba());
            }
        }

        age++;
        if (age >= NEEDED_GROWTH_TIME){
            die();
            attemptSpawnAFireFlower(location);
            location.setGround(new Sapling());
        }
    }
}
