package game;


import edu.monash.fit2099.engine.displays.Display;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A singleton class that manages all the monologues for the actor in the game
 *
 * @author Lim Hong Yee
 * @version 1.0.0
 */
public class Monologue {
    /**
     * Declaring all variables that is needed
     */
    private ArrayList<String> peach;
    private final String PEACH_PHRASE_ONE = "Dear Mario, I'll be waiting for you...";
    private final String PEACH_PHRASE_TWO = "Never gonna give you up!";
    private final String PEACH_PHRASE_THREE = "Release me, or I will kick you!";
    private ArrayList<String> toad;
    private final String TOAD_PHRASE_ONE = "You might need a wrench to smash Koopa's hard shells.";
    private final String TOAD_PHRASE_TWO = "You better get back to finding the Power Stars.";
    private final String TOAD_PHRASE_THREE = "The Princess is depending on you! You are our only hope.";
    private final String TOAD_PHRASE_FOUR = "Being imprisoned in these walls can drive a fungus crazy :(";
    private ArrayList<String> bowser;
    private final String BOWSER_PHRASE_ONE ="What was that sound? Oh, just a fire.";
    private final String BOWSER_PHRASE_TWO ="Princess Peach! You are formally invited... to the creation of my new kingdom!";
    private final String BOWSER_PHRASE_THREE ="Never gonna let you down!";
    private final String BOWSER_PHRASE_FOUR ="Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!";
    private ArrayList<String> goomba;
    private final String GOOMBA_PHRASE_ONE = "Mugga mugga!";
    private final String GOOMBA_PHRASE_TWO = "Ugha ugha... (Never gonna run around and desert you...)";
    private final String GOOMBA_PHRASE_THREE = "Ooga-Chaka Ooga-Ooga!";
    private ArrayList<String> koopa;
    private final String KOOPA_PHRASE_ONE = "Never gonna make you cry!";
    private final String KOOPA_PHRASE_TWO = "Koopi koopi koopii~!";
    private ArrayList<String> flyingKoopa;
    private final String FLYING_PHRASE_ONE = "Pam pam pam!";
    private ArrayList<String> piranhaPlant;
    private final String PIRANHA_PHRASE_ONE = "Slsstssthshs~! (Never gonna say goodbye~)";
    private final String PIRANHA_PHRASE_TWO = "Ohmnom nom nom nom.";
    private Display display;

    private static Monologue instance;

    /**
     * Constructor method for Monologue class
     */
    private Monologue(){
        this.peach = new ArrayList<>();
        peach.add(PEACH_PHRASE_ONE);
        peach.add(PEACH_PHRASE_TWO);
        peach.add(PEACH_PHRASE_THREE);
        this.toad = new ArrayList<>();
        toad.add(TOAD_PHRASE_ONE);
        toad.add(TOAD_PHRASE_TWO);
        toad.add(TOAD_PHRASE_THREE);
        toad.add(TOAD_PHRASE_FOUR);
        this.bowser = new ArrayList<>();
        bowser.add(BOWSER_PHRASE_ONE);
        bowser.add(BOWSER_PHRASE_TWO);
        bowser.add(BOWSER_PHRASE_THREE);
        bowser.add(BOWSER_PHRASE_FOUR);
        this.goomba = new ArrayList<>();
        goomba.add(GOOMBA_PHRASE_ONE);
        goomba.add(GOOMBA_PHRASE_TWO);
        goomba.add(GOOMBA_PHRASE_THREE);
        this.koopa = new ArrayList<>();
        koopa.add(KOOPA_PHRASE_ONE);
        koopa.add(KOOPA_PHRASE_TWO);
        this.flyingKoopa = new ArrayList<>();
        flyingKoopa.add(KOOPA_PHRASE_TWO);
        flyingKoopa.add(KOOPA_PHRASE_ONE);
        flyingKoopa.add(FLYING_PHRASE_ONE);
        this.piranhaPlant = new ArrayList<>();
        piranhaPlant.add(PIRANHA_PHRASE_ONE);
        piranhaPlant.add(PIRANHA_PHRASE_TWO);
        this.display = new Display();
    }

    /**
     * Get the singleton instance of the object
     * @return the instance of monologue
     */
    public static Monologue getInstance(){
        if(instance == null){
            instance = new Monologue();
        }
        return instance;
    }

    /**
     * Method used for Princess Peach when speaking
     */
    public void peachSpeak(){
        String return_string;
        return_string = peach.get(ThreadLocalRandom.current().nextInt(3));
        display.println("Princess Peach: " + return_string);
    }

    /**
     * Method used for Toad when speaking
     */
    public void toadSpeak(){
        String return_string;
        return_string = toad.get(ThreadLocalRandom.current().nextInt(4));
        display.println("Toad: " + return_string);
    }

    /**
     * Method used for Bowser when speaking
     */
    public void bowserSpeak(){
        String return_string;
        return_string = bowser.get(ThreadLocalRandom.current().nextInt(4));
        display.println("Bowser: " + return_string);
    }

    /**
     * Method used for Goomba when speaking
     */
    public void goombaSpeak(){
        String return_string;
        return_string = goomba.get(ThreadLocalRandom.current().nextInt(3));
        display.println("Goomba: " + return_string);
    }

    /**
     * Method used for Koopa when speaking
     */
    public void koopaSpeak(){
        String return_string;
        return_string = koopa.get(ThreadLocalRandom.current().nextInt(2));
        display.println("Koopa: " + return_string);
    }

    /**
     * Method used for Flying Koopa when speaking
     */
    public void flyingKoopaSpeak(){
        String return_string;
        return_string = flyingKoopa.get(ThreadLocalRandom.current().nextInt(3));
        display.println("Flying Koopa: " + return_string);
    }

    /**
     * Method used for Piranha Plant when speaking
     */
    public void piranhaPlantSpeak(){
        String return_string;
        return_string = piranhaPlant.get(ThreadLocalRandom.current().nextInt(2));
        display.println("Piranha Plant: " + return_string);
    }
}
