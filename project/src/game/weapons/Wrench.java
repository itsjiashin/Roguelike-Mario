package game.weapons;

import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Player;

/**
 * A class representing the Wrench weapon, extends WeaponItem class
 *
 * @author Khor Jia Shin
 */
public class Wrench extends WeaponItem{

    /**
     * A public static attribute that stores the value of wrench
     */
    public static final int VALUE = 200;
    /**
     * A public static attribute that stores the name of wrench
     */
    public static final String NAME = "Wrench";
    /**
     * Constructor for the Wrench class, which uses parent's class' constructor
     */
    public Wrench(){
        super(NAME, 'W', 50, "hits", 80);
    }



}
