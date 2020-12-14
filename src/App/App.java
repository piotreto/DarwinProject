package App;


import Constants.Constants;
import Map.WholeMap;
import support.MainFrame;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater( () -> {
            WholeMap map = new WholeMap(Constants.N_ANIMALS,Constants.N_GRASS, Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH, Constants.JUNGLE_RATIO);
            new MainFrame(map);
        });
    }
}
