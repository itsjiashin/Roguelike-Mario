package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    INVINCIBLE, // use this status to tell that current instance can't take damage
    CAN_JUMP, // use this status to tell that current instance is a type that can jump, not to be confused with can jump at the moment
    HOSTILE_TO_PLAYER, // use this status to be considered hostile towards player
    NOT_DORMANT, // use this status for an actor that can be dormant but is currently not
    DORMANT, // use this status for an actor that is currently dormant
    FADE,   // use this status to indicate that an item has a certain lifespan
    FERTILE, // use to indicate that a ground is fertile
    RESETTABLE, // use to indicate an object is RESETTABLE
    BLOCKS_WARP_PIPE,// use to indicate that a wrap pipe can't spawn at this location
    CAN_USE_WARP_PIPE, // use to indicate that a wrap pipe can be used by said actor
    FIRE_BREATHING, // use this to indicate that an actor can attack with fire elements
    FLY, // use this to indicate that an actor can fly
    HEALTH_FOUNTAIN, // use this instance to indicate that the location is a health fountain
    POWER_FOUNTAIN, // use this instance to indicate that the location is a power fountain
    POWER_WATER, // use this instance to indicate the actor drank power water
    FOUNTAIN, // use this instance to indicate that the location is a fountain
    BOTTLE_INVENTORY, // use this instance to indicate bottle is in mario's inventory
    HAS_WATER, // use this instance to indicate bottle has water
    NO_WATER, // use this instance to indicate bottle has no water
    BEING_REFILLED, // use this instance to indicate fountain being refilled by actor
    BEING_CONSUMED, // use this instance to indicate fountain being consumed from actor
    DEPLETED, // use this instance to indicate fountain is depleted
}
