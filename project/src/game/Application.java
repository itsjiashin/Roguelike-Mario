package game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.enemy.Bowser;
import game.enemy.Goomba;
import game.grounds.*;
import game.items.Coin;
import game.npc.PrincessPeach;
import game.npc.Toad;

/**
 * The main class for the Mario World game.
 */
public class Application {

    public final static int PLAYER_INIT_HP = 10000; //set to 100 after testing

    public final static int MUSHROOM_PIPE_ZONE_LEFT = 1;
    public final static int MUSHROOM_PIPE_ZONE_WIDTH = 79;
    public final static int MUSHROOM_PIPE_ZONE_TOP = 1;
    public final static int MUSHROOM_PIPE_ZONE_HEIGHT = 15;

    public final static int NUMBER_OF_WARP_PIPES = 10;



    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Lava());

        List<String> map = Arrays.asList(
                "..........................................##..........+.........................",
                "............+............+..................#...................................",
                "............................................#...................................",
                ".............................................##......................+..........",
                "...............................................#................................",
                "................................................#...............................",
                ".................+................................#.............................",
                ".................................................##.............................",
                "................................................##..............................",
                ".........+..............................+#____####.................+............",
                ".......................................+#_____###++.............................",
                ".......................................+#______###..............................",
                "........................................+#_____###..............................",
                "........................+........................##.............+...............",
                "...................................................#............................",
                "....................................................#...........................",
                "...................+.................................#..........................",
                "......................................................#.........................",
                ".......................................................##.......................");


        List<String> lavaZoneBlueprint = Arrays.asList(
                "........................................................LLLLLLLLLL",
                ".LLLLLLLLLLLLLLLLLL.............+.......................LLLLLLLLLL",
                ".LLLLLLLLLLLLLLLLLL.....................................LLLLLLLLLL",
                ".LLLLLLLLLLLLLLLLLL......LL.............................LLLLLLLLLL",
                ".LLLLLLLLLLLLLLLLLL......LL..............+..............LLLLLLLLLL",
                ".LLLLLLLLLLLLLLLLLL.....................................LLLLLLLLLL",
                ".LLLLLLLLLLLLLLLLLL.....................................LLLLLLLLLL",
                ".LLLLLLLLLLLLLLLLLL.....................................LLLLLLLLLL",
                "........................................................LLLLLLLLLL",
                "........................................................LLLLLLLLLL",
                ".........+.....................+................+.......LLLLLLLLLL",
                "........................................................LLLLLLLLLL",
                "........................................................LLLLLLLLLL");


        GameMap gameMap = new GameMap(groundFactory, map);
        GameMap lavaZone = new GameMap(groundFactory, lavaZoneBlueprint);
        world.addGameMap(gameMap);
        world.addGameMap(lavaZone);

        Actor mario = new Player("Player", 'm', PLAYER_INIT_HP);
        world.addPlayer(mario, gameMap.at(42, 10));
        lavaZone.at(Bowser.ORIGINAL_X_POSITION,Bowser.ORIGINAL_Y_POSITION).addActor(new Bowser());
        lavaZone.at(Bowser.ORIGINAL_X_POSITION+1, Bowser.ORIGINAL_Y_POSITION).addActor(new PrincessPeach());

        gameMap.at(44, 11).addActor(new Toad());
        //gameMap.at(43,10).addItem(new PowerStar());
        //gameMap.at(41,10).addItem(new SuperMushroom());

        //Just for testing
        gameMap.at(44, 10).addItem(new Coin(10000));
//        gameMap.at(42, 6).addActor(new FlyingKoopa());
        gameMap.at(42,9).setGround(PowerFountain.getInstance());
        gameMap.at(43,9).setGround(HealthFountain.getInstance());
        gameMap.at(43,9).addActor(new Goomba());
        Random random = new Random();

        Location lavaPipeLoc = lavaZone.at(0,0);
        WarpPipe lavaPipe = new WarpPipe(lavaPipeLoc, "Lava zone");
        lavaPipeLoc.setGround(lavaPipe);

        for (int count = 0; count < NUMBER_OF_WARP_PIPES; count++){
            Location pipe1Loc;
            do {
                int pipe1x = random.nextInt(MUSHROOM_PIPE_ZONE_WIDTH) + MUSHROOM_PIPE_ZONE_LEFT;
                int pipe1y = random.nextInt(MUSHROOM_PIPE_ZONE_HEIGHT) + MUSHROOM_PIPE_ZONE_TOP;

                pipe1Loc = gameMap.at(pipe1x, pipe1y);
                //can't spawn on a block that blocks spawning
            } while (!WarpPipe.canSpawnWarpPipe(pipe1Loc));
            WarpPipe pipe1 = new WarpPipe(pipe1Loc, "Mushroom kingdom");

            pipe1.setTargetPipe(lavaPipe);

            pipe1Loc.setGround(pipe1);

        }

        world.run();

    }
}
