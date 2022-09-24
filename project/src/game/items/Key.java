package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * Used to represent a Key object, extends Item class
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 */
public class Key extends Item {

    /**
     * Name of key
     */
    public static final String NAME = "Key";

    /**
     * A constructor that utilizes parent's constructor
     */
    public Key(){
        super(NAME, 'k', true);
    }
}
