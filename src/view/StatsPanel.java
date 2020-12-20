package view;

import javax.swing.*;

import Map.WholeMap;
import Math.*;

public class StatsPanel extends JList {


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
        listText.addElement(String.format("Dominujący genotyp:"));
        listText.addElement(String.format("%s", this.stats.getDominateGenotype()));
        listText.addElement(String.format("Średni poziom energii %f", this.stats.getAverageEnergy()));
        listText.addElement(String.format("Średnia długość życia: %f", this.stats.getAverageDeadsLive()));
        listText.addElement(String.format("Średnia liczba dzieci: %f", this.stats.getAverageChildren()));
        this.setModel(listText);

    }

}
