package MapBuilder;

import MapBuilder.enums.GameMode;

import java.io.File;

public class GameSettings {

    public static int MAP_WIDTH,GRID_WIDTH, MAP_HEIGHT,GRID_HEIGHT;
    public static double scaling;
    public static int rows,columns;
    public static String MAP_FILE = "";

    static {
        System.out.println(new File(MAP_FILE));
    }
    public static GameMode GAME_MODE = GameMode.EXPLORATION;


    public static int NUM_GUARDS;
    public static int NUM_INTRUDERS;


    public static double BASE_SPEED_INTRUDERS;
    public static double BASE_SPEED_GUARDS;
    public static double SPRINT_SPEED_INTRUDERS;





    public static double STEP_SIZE;


}
