package Constants;

import Data.readJSON;

public class Constants {

    private Constants() {

    }

    public static final int obj_size = 10;
    public static final int N_ANIMALS = readJSON.getInstance().animalsOnStart;
    public static final int N_GRASS = readJSON.getInstance().grassOnStart;
    public static final int BOARD_WIDTH = readJSON.getInstance().width;
    public static final int BOARD_HEIGHT = readJSON.getInstance().height;
    public static final int GAME_SPEED = readJSON.getInstance().speed;
    public static final int START_ENERGY = readJSON.getInstance().startEnergy;
    public static final int DIFF_ENERGY = readJSON.getInstance().moveEnergy;
    public static final int GRASS_ENERGY = readJSON.getInstance().plantEnergy;
    public static final double JUNGLE_RATIO = readJSON.getInstance().jungleRatio;
}
