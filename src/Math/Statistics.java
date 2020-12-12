package Math;

import Assets.Animal;
import Map.WholeMap;

import java.util.ArrayList;

public class Statistics {

    private WholeMap map;

    public Statistics(WholeMap map) {
        this.map = map;
    }

    public int countAnimals() {
        ArrayList<Animal> AnimalList = map.getList();
        return AnimalList.size();
    }

    public int countGrasses() {
        return map.plants.size();
    }

    public int getDominateGenom() {
        ArrayList<Animal> AnimalList = map.getList();
        int[] gensCount = new int[]{0,0,0,0,0,0,0,0};
        for(Animal animal : AnimalList) {
            for(int i = 0;i < 8;i++) {
                gensCount[i] += animal.gens.getGensCounter()[i];
            }
        }
        int max = 0;
        for(int i = 1;i < 8;i++) {
            if (gensCount[i] > gensCount[max]) {
                max = i;
            }
        }
        return max;
    }

    public double getAverageEnergy() {
        ArrayList<Animal> AnimalList = map.getList();
        double energySum = 0;
        for(Animal animal : AnimalList) {
            energySum += animal.getAnimal_energy();
        }
        if(AnimalList.isEmpty()) {
            return 0;
        }
        return energySum / AnimalList.size();
    }

    public double getAverageDeadsLive() {
        if(this.map.getDeads() == 0) {
            return 0;
        }
        return this.map.getDeadslive() / this.map.getDeads();
    }

    public double getAverageChildren() {
        ArrayList<Animal> AnimalList = map.getList();
        double childrens = 0;
        for(Animal animal : AnimalList) {
            childrens += animal.getChildren();
        }
        if (AnimalList.isEmpty()) {
            return 0;
        }
        return childrens / AnimalList.size();
    }
}
