package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.Resettable;
import game.Wallet;

/**
 * A class representing a Coin item, extends Item class and implements Resettable interface
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public class Coin extends Item implements Resettable{


    /**
     * Attributes for coins
     */
    private int value;
    private boolean mustResetNextTurn = false;

    /**
     * Constructor for coin
     * @param value value of the coin
     */
    public Coin(int value) {
        super("Coin", '$', true);
        this.value = value;
        this.registerInstance();
    }

    /**
     * Reset instance method to reset coins
     */
    @Override
    public void resetInstance() {
        this.mustResetNextTurn = true;
    }

    /**
     * Coin tick method when on the ground
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (mustResetNextTurn){
            currentLocation.removeItem(this);
        }
    }

    /**
     * Coin tick method when in item's inventory, remove the coin and add into player's wallet
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
        // remove item from inventory
        actor.removeItemFromInventory(this);
        // adds the value of the coin into wallet
        Wallet.getInstance().addCoin(value);
    }

    /**
     * To String method to show coin's value in menu description
     * @return String
     */
    @Override
    public String toString() {
        return String.format("Coin($%d)", value);
    }
}
