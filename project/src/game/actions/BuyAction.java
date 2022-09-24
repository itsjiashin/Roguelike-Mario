package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Wallet;

/**
 * Class for buying items from toad, extends action class
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public class BuyAction extends Action {

    /**
     * items that toad is selling
     */
    private Item items;
    /**
     * value of the items
     */
    private int value;

    /**
     * Constructor for BuyAction class
     * @param item items that toad is selling
     * @param cost cost of item
     */
    public BuyAction(Item item,int cost){
        this.items = item;
        this.value = cost;
    }

    /**
     * Buy item from toad, either deduct from wallet or dont have enough credit
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String, determining whether item is bought or not.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // return string
        String result;
        // Checks if wallet amount is greater than or equal to the item value, if it is, add item to actor's inventory and deduct amount from wallet
        if(Wallet.getInstance().getAmount() >= value){
            actor.addItemToInventory(items);
            Wallet.getInstance().deductFromBalance(value);
            result = menuDescription(actor);
        }
        else {
            result = "You don't have enough coins!";
        }
        return result;
    }

    /**
     * Description of BuyAction in the menu
     * @param actor The actor performing the action.
     * @return A string, to display the message on the menu for this action
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " buys " + items.toString() + " $" + value;
    }
}
