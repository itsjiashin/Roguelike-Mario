package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.ConsumeAction;
import game.actions.DrinkAction;
import game.actions.ResetGameAction;
import game.items.Bottle;
import game.weapons.Wrench;

import java.util.List;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable{

	private final Menu menu = new Menu();
	private List<Enum<?>> originalCapabilities;

	private DrinkAction drinkAction = new DrinkAction();
	private boolean canReset = true;
	/**
	 * Base damage of the player
	 */
	private int damage;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		//call super
		super(name, displayChar, hitPoints);

		//capabilities
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.CAN_JUMP);
		this.addCapability(Status.CAN_USE_WARP_PIPE);
		originalCapabilities = this.capabilitiesList();

		//resettable
		registerInstance();

		//Just for testing
		this.addItemToInventory(new Wrench());
		damage = 5;
	}

	/**
	 * a function that is called when the game resets
	 */
	@Override
	public void resetInstance() {
		for (Enum<?> capability: capabilitiesList()){
			removeCapability(capability);
		}

		for (Enum<?> capability: originalCapabilities){
			addCapability(capability);
		}

		increaseMaxHp(0);

		canReset = false;
	}

	/**
	 * the function that is used when it is the players turn to play
	 * @param actions    collection of possible Actions for the player
	 * @param lastAction The Action the player took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the player
	 * @param display    the I/O object to which messages may be written
	 * @return the action the player will take
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		display.println(this + this.printHp() + " at (" + map.locationOf(this).x() + "," + map.locationOf(this).y() + ")");
		display.println(Wallet.getInstance().printWallet());
		// Checks if player has power water consumed
		if(this.hasCapability(Status.POWER_WATER)){
			this.getIntrinsicWeapon();
			this.removeCapability(Status.POWER_WATER);
		}
		// Checks if player has water in bottle
		if(this.hasCapability(Status.HAS_WATER)){
			actions.add(drinkAction);
		}
		else{
			actions.remove(drinkAction);
		}
		if(this.hasCapability(Status.INVINCIBLE)){
			display.println(this + " is INVINCIBLE!");
		}

		if(this.hasCapability(Status.FIRE_BREATHING)){
			display.println(this + " is on fire!");
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		//add reset action
		if (canReset){
			actions.add(new ResetGameAction());
		}

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}


	/**
	 * a function that the engine uses to know how to display the player
	 * @return the display char
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * a function used to reduce hp
	 * @param hitPoints number of hp to deduct.
	 */
	public void hurt(int hitPoints){
		if(this.hasCapability(Status.INVINCIBLE)){
			super.hurt(0);
		}
		else{
			super.hurt(hitPoints);
		}

		if(this.hasCapability(Status.TALL) && !this.hasCapability(Status.INVINCIBLE)){
			this.removeCapability(Status.TALL);
		}
	}

	/**
	 * Increases base damage for player
	 * @return new IntrinsicWeapon with updated damage
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		if(this.hasCapability(Status.POWER_WATER)) {
			damage += 15;
		}
		return new IntrinsicWeapon(damage, "punches");
	}
}
