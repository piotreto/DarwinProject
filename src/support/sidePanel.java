package support;

import Constants.Constants;
import Map.WholeMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sidePanel extends JPanel implements ActionListener {

    private ButtonsPanel Buttons;
    private StatsPanel Stats;
    private JButton newWorld;

    public sidePanel(ButtonsPanel Buttons, StatsPanel Stats) {
        setLayout(new GridLayout(4,1));
        setPreferredSize(new Dimension(300, 330 ));
        this.add(Buttons);
        this.add(Stats);
        JLabel text = new JLabel("Kliknij aby dodać nowy świat");
        this.add(text);
        this.newWorld = new JButton("Nowy");
        this.newWorld.addActionListener(this::actionPerformed);
        this.add(newWorld);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        WholeMap map2 = new WholeMap(Constants.N_ANIMALS,Constants.N_GRASS, Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH, Constants.JUNGLE_RATIO);
        new MainFrame(map2);
    }
}
