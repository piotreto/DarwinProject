package support;

import Map.WholeMap;

import javax.swing.*;
import java.awt.*;

public class sidePanel extends JPanel {

    private ButtonsPanel Buttons;
    private StatsPanel Stats;

    public sidePanel(ButtonsPanel Buttons, StatsPanel Stats) {
        setLayout(new GridLayout(2,1));
        setPreferredSize(new Dimension(300, 200));
        this.add(Buttons);
        this.add(Stats);

    }
}
