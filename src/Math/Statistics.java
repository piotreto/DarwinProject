package Math;

import Assets.Animal;
import Map.WholeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public static int getSeed(Animal animal) {
        int result = animal.getChildren();
        for(Animal children : animal.getChildrens()) {
            result += getSeed(children);
        }
        return result;
    }
    public Genotype getDominateGenotype() {
        ArrayList<Animal> AnimalList = map.getList();
        Map<Genotype, Integer> counterMap = new HashMap<Genotype, Integer>();
        Genotype best = null;
        int bestC = 0;
        for(Animal animal : AnimalList) {
            if(counterMap.containsKey(animal.getGens())) {
                int counter = counterMap.get(animal.getGens());
                counterMap.replace(animal.getGens(), counter + 1);
                if( counter > bestC) {
                    bestC = counter;
                    best = animal.getGens();
                }
            } else {
                counterMap.put(animal.getGens(), 1);
                if(bestC == 0) {
                    bestC = 1;
                    best = animal.getGens();
                }
            }
        }
        return best;


    }
}
