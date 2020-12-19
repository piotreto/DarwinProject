package view;

import Map.WholeMap;
import Math.*;

import java.io.*;

public class writtingToFile {
    private Statistics stats;
    private WholeMap map;
    private int n;

    public writtingToFile(WholeMap map, int n) {
        this.map = map;
        this.stats = new Statistics(map);
        this.n = n;
    }

    public void writeToFile() throws FileNotFoundException {
        try {
            FileWriter myWriter = new FileWriter("./src/statsDay" + n + ".txt");
            myWriter.write(String.format("Liczba zwierząt: %d\n", this.stats.countAnimals()));
            myWriter.write(String.format("Liczba roślin: %d\n", this.stats.countGrasses()));
            myWriter.write(String.format("Dominujący genotyp: %s\n", this.stats.getDominateGenotype()));
            myWriter.write(String.format("Średni poziom energii %f\n", this.stats.getAverageEnergy()));
            myWriter.write(String.format("Średnia długość życia: %f\n", this.stats.getAverageDeadsLive()));
            myWriter.write(String.format("Średnia liczba dzieci: %f\n", this.stats.getAverageChildren()));
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred during creating a file.");
        }
    }
}
