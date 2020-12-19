package Assets;

import java.util.*;
import Math.Vector2d;
import Map.*;
import logic.Observer;
import logic.Observable;
import Math.Genotype;

public class Animal implements Observable{

    private final List<Observer> observers = new ArrayList<>();
    private MapDirection orient = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);
    private WholeMap map;
    private Genotype gens;
    private double animal_energy;
    private int children = 0;
    private int years = 0;
    private int dead = 0;
    private int ancestors = 0;
    private ArrayList<Animal> childrens = new ArrayList<Animal>();


    public Animal(WholeMap map, Vector2d initialPosition, double animal_energy, Genotype gens) {
        this.map = map;
        this.position = initialPosition;
        this.animal_energy = animal_energy;
        this.gens = gens;
    }

    public void move() {
        Vector2d oldPosition = this.position;
        Vector2d test = fixPosition(position.add(this.orient.toUnitVector()));
        if(true){
            this.position = test;
            notifyObservers(oldPosition, this.position);
        }
    }

    public Animal procreate(Animal other) {
        Genotype newGenotype = this.gens.procreate(other.gens);
        double energy = 0.25 * this.getAnimal_energy() + 0.25 * other.getAnimal_energy();
        this.setEnergy(0.75 * this.getAnimal_energy());
        this.setChildren(this.getChildren() + 1);
        other.setChildren(other.getChildren() + 1);
        other.setEnergy(0.75 * other.getAnimal_energy());
        MapDirection dir = MapDirection.values()[newGenotype.getRandomGen()];
        Vector2d position = null;
        for(int i = 0;i < 8;i++) {
            MapDirection move = MapDirection.values()[i];
            position = this.getPosition().add(move.toUnitVector());
            if (!map.isOccupied(position)) {
                break;
            }
        }
        if (position == null) {
            while(map.isOccupied(position)) {
                position = map.getRandomVect();
            }
        }
        Animal brzdac = new Animal(map, position, energy, newGenotype);
        this.addChild(brzdac);
        other.addChild(brzdac);

        brzdac.setAncestors(this.getAncestors() + other.getAncestors() + 2);
        return brzdac;

    }

    public void addChild(Animal child) {
        this.childrens.add(child);
    }

    public int getAncestors() {
        return this.ancestors;
    }
    public void setAncestors(int n) {
        this.ancestors = n;
    }

    public void setEnergy(double n) {
        this.animal_energy = n;
    }

    private Vector2d fixPosition(Vector2d add) {
        int fixedX = add.x;
        int fixedY = add.y;
        if(add.x < 0){
            fixedX = map.xSize + add.x;
        }
        if(add.y < 0){
            fixedY = map.ySize + add.y;
        }
        if(add.x >= map.xSize) {
            fixedX = add.x % map.xSize;
        }
        if(add.y >= map.ySize) {
            fixedY = add.y % map.ySize;
        }
        return new Vector2d(fixedX, fixedY);
    }

    public Vector2d getPosition() {
        return position;
    }

    public double getAnimal_energy() {
        return animal_energy;
    }

    public MapDirection getDirection() {
        return this.orient;
    }

    public String toString() {
        return orient.toString();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Vector2d oldPos, Vector2d newPos) {
        observers.forEach(observer -> observer.update(oldPos, newPos));
    }

    public void setDirection(MapDirection dir) {
        this.orient = dir;
    }

    public int getChildren() {
        return this.children;
    }
    public void setChildren(int n) {
        this.children = n;
    }
    public int getYears() {
        return this.years;
    }
    public void setYears(int n) {
        this.years = n;
    }
    public void setDead(int year) {
        this.dead = year;
    }
    public int getDead() {
        return this.dead;
    }
    public ArrayList<Animal> getChildrens() { return this.childrens; }
    public Genotype getGens() { return  this.gens; }



}