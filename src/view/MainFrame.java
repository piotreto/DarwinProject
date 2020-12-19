package view;

import Engine.Simulation;
import Map.WholeMap;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private WholeMap map;
    private Simulation engine;
    private boolean isMain;

    public MainFrame(WholeMap map, boolean isMain) {
        this.map = map;
        this.isMain = isMain;
        initializeLayout();
    }

    private void initializeLayout() {

        WorldPanel WorldMap = new WorldPanel(this.map);
        StatsPanel Stats = new StatsPanel(this.map);
        this.engine = new Simulation(WorldMap, Stats, this.map);
        WorldMap.setEngine(this.engine);
        ButtonsPanel Buttons = new ButtonsPanel(this.engine);
        sidePanel Side = new sidePanel(Buttons, Stats, WorldMap);

        setLayout(new FlowLayout());


        add(WorldMap);
        add(Side);


        setTitle("Darwin World");

        pack();
        if(this.isMain){
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
