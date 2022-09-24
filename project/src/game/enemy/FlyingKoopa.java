package game.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologue;
import game.Status;

/**
 * A class representing a FlyingKoopa enemy, extends Koopa class
 *
 * @author Khor Jia Shin
 * @version 1.0.0
 */
public class FlyingKoopa extends Koopa{


    /**
     * A constructor that utilizes parent's constructor
     */
    public FlyingKoopa(){
        super("Flying Koopa", 'F', 150);
        this.addCapability(Status.FLY);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        ticker += 1;
        if(ticker%2 == 0){
            Monologue.getInstance().flyingKoopaSpeak();
        }

        return super.playTurn(actions, lastAction, map, display);
    }
}
