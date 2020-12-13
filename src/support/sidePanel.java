package support;

import Constants.Constants;
import Map.WholeMap;
import Math.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class sidePanel extends JPanel implements ActionListener {

    private ButtonsPanel Buttons;
    private StatsPanel Stats;
    private JButton newWorld;
    private JButton turnBest;
    private JButton save;
    private WorldPanel World;
    private Statistics stats;


    public sidePanel(ButtonsPanel Buttons, StatsPanel Stats, WorldPanel World) {
        setPreferredSize(new Dimension(300, 400));
        this.add(Buttons);
        this.add(Stats);
        this.World = World;
        JLabel text3 = new JLabel("Kliknij aby zapisać statystyki do pliku");
        this.add(text3);
        this.save = new JButton("Zapisz");
        save.addActionListener(this::actionPerformed);
        this.add(this.save);
        JLabel text1 = new JLabel("Kliknij aby dodać nowy świat");
        this.newWorld = new JButton("Nowy Świat");
        this.newWorld.addActionListener(this::actionPerformed);
        JLabel text2 = new JLabel("Kliknij aby wyróżnić zwięrzęta");
        this.turnBest = new JButton("Dominujący genom");
        this.turnBest.addActionListener(this::actionPerformed);
        this.add(text2);
        this.add(this.turnBest);
        this.add(text1);
        this.add(newWorld);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.newWorld) {
            WholeMap map2 = new WholeMap(Constants.N_ANIMALS,Constants.N_GRASS, Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH, Constants.JUNGLE_RATIO);
            new MainFrame(map2);
        } else if (actionEvent.getSource() == this.turnBest) {
            if(!this.World.isRunning()){
                this.World.setChooseBest(true);
                this.World.repaint();
            }
        } else if (actionEvent.getSource() == this.save) {
            writtingToFile writer = new writtingToFile(this.World.getMap(), this.World.getMap().getDays());
            try {
                writer.writeToFile();
            } catch (FileNotFoundException e) {
                System.out.println("Error occured during creating file");
            }


        }

    }
}
