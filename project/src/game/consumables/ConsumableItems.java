package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeAction;

/**
 * An abstract class to represent items that can be consumed, extends Item class
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 */
public abstract class ConsumableItems extends Item{

    /**
     * Every item that is consumable can be consumed using ConsumeAction
     */
    private ConsumeAction consumeAction = new ConsumeAction(this);

    /**
     * Attribute to determine if item is picked up or not
     */
    private boolean pickedUp = false;

    /**
     * Attribute to store ticker
     */
    private int ticker;

    /**
     * Constructor for ConsumableItems class
     *
     * @param name Name of consumable item
     * @param displayChar Character used to represent the item on display
     */
    public ConsumableItems(String name, char displayChar){
        super(name, displayChar, false);
    }

    /**
     * Tick method when item is in player's inventory
     *
     * @param currentLocation Current location of item
     * @param actor Which actor holds the item in its inventory
     */
    public void tick(Location currentLocation, Actor actor){
        if (pickedUp == false){
            this.addAction(consumeAction);
            pickedUp = true;
        }
    }

    /**
     * Overwritten the getPickUpAction, to allow for every consumable item to be picked up no matter what
     *
     * @param actor Actor picking the item up
     * @return New PickUpItemAction
     */
    public PickUpItemAction getPickUpAction(Actor actor){
        return new PickUpItemAction(this);
    }

    /**
     * What happens when the consumable item is consumed
     *
     * @param actor Actor consuming the item
     */
    public void consume(Actor actor){
        this.removeAction(consumeAction);
    }

    protected void setTicker(int ticker){this.ticker = ticker;}

    public int  getTicker(){
        return ticker;
    }


}
