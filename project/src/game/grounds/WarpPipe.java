package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.Status;
import game.actions.AdvancedMoveAction;
import game.enemy.PiranhaPlant;

import java.util.Objects;

/**
 * a class the represents a wrap pipe, a pipe that is used to teleport to other zones
 */
public class WarpPipe extends HighGround implements Resettable {

    private Location currentLocation;

    private String locationName;

    private WarpPipe targetPipe;

    /**
     * To check whether WarpPipe needs to be reset
     */
    private boolean mustResetNextTurn = false;

    /**
     * To check whether Piranha Plant has been spawned after first game tick
     */
    private boolean spawnedFirstPiranhaPlant = false;

    /**
     * constructor
     * @param currentLocation the location the pipe will be
     * @param locationName the name of the location the pipe will be on
     */
    public WarpPipe(Location currentLocation, String locationName) {
        super('c', "Warp Pipe", 100, 0);
        this.currentLocation = currentLocation;
        this.locationName = locationName;
        addCapability(Status.BLOCKS_WARP_PIPE);
        registerInstance();
    }

    /**
     * getter for locationName
     * @return locationName
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * setter for locationName
     * @param locationName locationName's new value
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * getter for currentLocation
     * @return currentLocation
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * setter for currentLocation
     * @param currentLocation currentLocation's new value
     */
    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * getter for targetPipe
     * @return targetPipe
     */
    public WarpPipe getTargetPipe() {
        return targetPipe;
    }

    /**
     * setter for targetPipe
     * @param targetPipe targetPipe's new value
     */
    public void setTargetPipe(WarpPipe targetPipe) {
        this.targetPipe = targetPipe;
    }

    /**
     * a function that will take 2 pipes and link them together
     * @param pipe1 first pipe
     * @param pipe2 second pipe
     */
    public static void linkPipes(WarpPipe pipe1, WarpPipe pipe2){
        pipe1.setTargetPipe(pipe2);
        pipe2.setTargetPipe(pipe1);
    }

    /**
     * a function to check weather it is ok to spawn
     * a WrapPipe at a given location
     * @param location the location to check
     * @return true, if ok to place, false otherwise
     */
    public static boolean canSpawnWarpPipe(Location location){
        //can't spawn on a block that blocks spawning
        if (location.getGround().hasCapability(Status.BLOCKS_WARP_PIPE)) {
            return false;
        }
        //there should be no actor
        if (location.getActor() != null){
            return false;
        }

        return true;
    }

    /**
     * a function that is called every turn
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        currentLocation = location;

        if (!spawnedFirstPiranhaPlant){
            location.addActor(new PiranhaPlant());
            spawnedFirstPiranhaPlant = true;
        }

        if (mustResetNextTurn){
            if (!location.containsAnActor()){
                location.addActor(new PiranhaPlant());
            }
            mustResetNextTurn = false;
        }
    }

    public void killActorOnTop(){
        Actor actor = currentLocation.getActor();
        if (actor != null){
            currentLocation.map().removeActor(actor);
        }
    }

    /**
     * the allowableActions function
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = super.allowableActions(actor, location, direction);
        if (Objects.equals(direction, "") && targetPipe != null && actor.hasCapability(Status.CAN_USE_WARP_PIPE)){
            AdvancedMoveAction advancedMoveAction = new AdvancedMoveAction(targetPipe.getCurrentLocation(), targetPipe.getLocationName());

            advancedMoveAction.setSignal(() -> {
                targetPipe.killActorOnTop();
                targetPipe.setTargetPipe(this);
            }); //a signal to link the used pipe and kill any actor on the pipe

            actionList.add(advancedMoveAction);
        }
        return actionList;
    }

    public void resetInstance(){
        mustResetNextTurn = true;
    }
}
