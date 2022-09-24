package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.enemy.FlyingKoopa;
import game.enemy.Koopa;

import java.util.ArrayList;

/**
 * Mature is one of the 3 stages of a tree
 * it is created from growing a Sapling
 */
public class Mature extends Tree{

    //constants
    private final static int KOOPA_SPAWNING_CHANCE = 15;
    private final static int DEATH_CHANCE = 20;
    private final static int INVERSE_REPRODUCTION_RATE = 5; //reproduce every X turns

    //variables
    private int age;

    /**
     * Constructor.
     */
    public Mature() {
        super('T', "Mature", 70, 30);
        age = 0;
    }

    /**
     * Ground and its children can also experience the joy of time.
     * @param location The location of the Mature tree
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        //used to end the turn early, (e.g. when a tree dies at the start of a turn)
        if (endEarly){
            endEarly = false;
            return;
        }

        //spawning sprouts
        age++;
        if (age % INVERSE_REPRODUCTION_RATE == 0){
            spawnSprout(location);
            age = 0;
        }

        //roll dice to check if Koopa will spawn
        if (random.nextInt(100) < KOOPA_SPAWNING_CHANCE){
            if (!location.containsAnActor()){
                if(random.nextInt(100) < 50){
                    location.addActor(new Koopa());
                }
                else{
                    location.addActor(new FlyingKoopa());
                }
            }
        }

        //roll dice to check if Mature will die
        if (random.nextInt(100) < DEATH_CHANCE){
            die();
            location.setGround(new Dirt());
        }
    }

    /**
     * a function that looks at the surrounding and spawns
     * a new sprout in a random fertile ground (if there is any)
     * @param location location of the Mature tree
     */
    private void spawnSprout(Location location){
        ArrayList<Location> fertileGroundsLocations = new ArrayList<>();

        //get exits and add them to the list if fertile
        for (Exit exit: location.getExits()){
            Location currentLocation = exit.getDestination();
            if (currentLocation.getGround().hasCapability(Status.FERTILE)){
                fertileGroundsLocations.add((currentLocation));
            }
        }

        //if list not empty, place a tree in a random fertile spot
        if (!fertileGroundsLocations.isEmpty()){
            fertileGroundsLocations.get(random.nextInt(fertileGroundsLocations.size())).setGround(new Sprout());
        }
    }
}
