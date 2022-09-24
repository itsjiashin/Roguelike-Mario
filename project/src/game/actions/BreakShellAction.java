package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.consumables.SuperMushroom;
import game.enemy.Enemy;
import game.enemy.Koopa;

/**
 * A class for breaking shells, extends Action class
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 */
public class BreakShellAction extends Action {

    /**
     * The target that is going to have it's shell broken
     */
    private Koopa target;

    /**
     * The direction of target
     */
    private String direction;

    /**
     * A constructor for BreakShellAction class
     */
    public BreakShellAction(Koopa target, String direction){
        this.target = target;
        this.direction = direction;
    }

    /**
     * Destroys the target's shell, essentially killing the target
     *
     * @param actor Actor doing the shell breaking
     * @param map Current GameMap
     *
     * @return A string, denoting that the target's shell is broken
     */
    @Override
    public String execute(Actor actor, GameMap map){
        ActionList dropActions = new ActionList();
        Location location = map.locationOf(target);
        //Spawn a SuperMushroom item where Koopa's shell is broken
        location.addItem(new SuperMushroom());
        // drop all items
        for (Item item : target.getInventory())
            dropActions.add(item.getDropAction(actor));
        for (Action drop : dropActions)
            drop.execute(target, map);
        // remove actor
        map.removeActor(target);
        return actor + " has destroyed the shell at location (" + location.x() + "," + location.y() + ")";
    }

    /**
     * A description of BreakShellAction on the menu
     *
     * @param actor Actor doing the shell breaking
     * @return A string, to display the message on the menu for this action
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " breaks the shell of " + target + " at " + direction;
    }
}
