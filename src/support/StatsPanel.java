package support;

import javax.swing.*;

import Map.WholeMap;
import Math.*;

import java.awt.*;

public class StatsPanel extends JList {

    private static final int HEIGHT = 400;
    private static final int WIDTH = 400;
    private JLabel animalsCounter;
    private JLabel grassCounter;
    private JLabel dominateGenom;
    private JLabel averageEnergy;
    private JLabel averageDeadsLive;
    private JLabel averageChildren;
    private Statistics stats;
    private WholeMap map;

    public StatsPanel(WholeMap map) {
        super(new DefaultListModel<>());
        this.map = map;
        this.stats = new Statistics(map);
        this.setVisibleRowCount(50);
        this.doOneLoop();


    }

    public void doOneLoop() {
        DefaultListModel<String> listText = new DefaultListModel<>();
        listText.addElement(String.format("Liczba zwierząt: %d", this.stats.countAnimals()));
        listText.addElement(String.format("Liczba roślin: %d", this.stats.countGrasses()));
        listText.addElement(String.format("Dominujący genom: %d", this.stats.getDominateGenom()));
        listText.addElement(String.format("Średni poziom energii %f", this.stats.getAverageEnergy()));
        listText.addElement(String.format("Średnia długość życia: %f", this.stats.getAverageDeadsLive()));
        listText.addElement(String.format("Średnia liczba dzieci: %f", this.stats.getAverageChildren()));
        this.setModel(listText);

    }

}
