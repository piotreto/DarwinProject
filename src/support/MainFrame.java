package support;

import Engine.Simulation;
import Map.WholeMap;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private WholeMap map;
    private Simulation engine;

    public MainFrame(WholeMap map) {
        this.map = map;
        initializeLayout();
    }

    private void initializeLayout() {

        WorldPanel WorldMap = new WorldPanel(this.map);
        StatsPanel Stats = new StatsPanel(this.map);
        this.engine = new Simulation(WorldMap, Stats, this.map);
        WorldMap.setEngine(this.engine);
        ButtonsPanel Buttons = new ButtonsPanel(this.engine);
        sidePanel Side = new sidePanel(Buttons, Stats);

        setLayout(new FlowLayout());


        add(WorldMap);
        add(Side);


        setTitle("Darwin World");

        pack();

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
