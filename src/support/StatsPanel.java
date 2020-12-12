package support;

import javax.swing.*;

import Map.WholeMap;
import Math.*;

import java.awt.*;

public class StatsPanel extends JPanel {

    private static final int HEIGHT = 100;
    private static final int WIDTH = 150;
    private JLabel animalsCounter;
    private JLabel grassCounter;
    private JLabel dominateGenom;
    private JLabel averageEnergy;
    private JLabel averageDeadsLive;
    private JLabel averageChildren;
    private Statistics stats;
    private WholeMap map;

    public StatsPanel(WholeMap map) {
        this.map = map;
        this.stats = new Statistics(map);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridLayout(6,1));
        animalsCounter = new JLabel(String.format("Liczba zwierząt: %d", this.stats.countAnimals()));
        grassCounter = new JLabel(String.format("Liczba roślin: %d", this.stats.countGrasses()));
        dominateGenom = new JLabel(String.format("Dominujący genom: %d", this.stats.getDominateGenom()));
        averageEnergy = new JLabel(String.format("Średni poziom energii %f", this.stats.getAverageEnergy()));
        averageDeadsLive = new JLabel(String.format("Średnia długość żyia: %f", this.stats.getAverageDeadsLive()));
        averageChildren = new JLabel(String.format("Średnia liczba dzieci: %f", this.stats.getAverageChildren()));
        this.add(animalsCounter);
        this.add(grassCounter);
        this.add(dominateGenom);
        this.add(averageEnergy);
        this.add(averageDeadsLive);
        this.add(averageChildren);

    }

    public void doOneLoop() {
        animalsCounter.setText(String.format("Liczba zwierząt: %d", this.stats.countAnimals()));
        grassCounter.setText(String.format("Liczba roślin: %d", this.stats.countGrasses()));
        dominateGenom.setText(String.format("Dominujący genom: %d", this.stats.getDominateGenom()));
        averageEnergy.setText(String.format("Średni poziom energii %f", this.stats.getAverageEnergy()));
        averageDeadsLive.setText(String.format("Średnia długość życia: %f", this.stats.getAverageDeadsLive()));
        averageChildren.setText(String.format("Średnia liczba dzieci: %f", this.stats.getAverageChildren()));
    }

}
