package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.Wallet;
import game.actions.DrinkAction;
import game.consumables.ConsumableItems;

import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * A singleton class representing bottle
 *
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public class Bottle extends Item {

    private static Bottle instance;
    /**
     * ArrayList that contains the stack of the bottle
     */
    private ArrayList<ConsumableItems> stackBottle;

    /**
     * Constructor for Bottle
     */
    private Bottle() {
        super("Bottle", 'B', false);
        stackBottle = new ArrayList<>();
        this.addCapability(Status.BOTTLE_INVENTORY);
    }

    public boolean getStackBottle() {
        return stackBottle.size() > 0;
    }

    /**
     * Get the singleton instance of bottle
     * @return the instance of bottle
     */
    public static Bottle getInstance(){
        if(instance == null){
            instance = new Bottle();
        }
        return instance;
    }

    /**
     * Adds the water into the stack of stackbottle
     * @param water the water that is going to be added
     */
    public void addWater(ConsumableItems water){
        stackBottle.add(water);
    }

    /**
     * Gets the stack of the bottle and converts it to string
     * @return String: The bottle stack
     */
    public String getWater(){
        String return_string = "";
        for(int i=0; i<stackBottle.size();i++){
            if(i == stackBottle.size()-1){
                return_string += stackBottle.get(i);
            }
            else{
                return_string += stackBottle.get(i)+ ", ";
            }
        }
        return return_string;
    }

    /**
     * Remove the water after consuming
     * @return the removed water
     */
    public ConsumableItems removeWater(){
        return stackBottle.remove(stackBottle.size()-1);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        if(this.getStackBottle()){
            this.addCapability(Status.HAS_WATER);
            this.removeCapability(Status.NO_WATER);
        }
        else{
            this.addCapability(Status.NO_WATER);
            this.removeCapability(Status.HAS_WATER);
        }
        super.tick(currentLocation, actor);
    }
}
