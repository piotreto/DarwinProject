package Map;

import Assets.*;
import Constants.Constants;
import Math.*;
import logic.Observer;

import java.util.*;

public class WholeMap implements Observer{

    private int n_animals;
    private int n_grass;
    public int xSize;
    public int ySize;
    public double jungleRatio;
    public JungleMap jungle;
    public Map<Vector2d,Grass> plants = new LinkedHashMap<>();
    public Map<Vector2d, List<Animal>>animals = new HashMap<>();
    private Random xGenerator = new Random();
    private Random yGenerator = new Random();
    private int deadslive = 0;
    private int deads = 0;
    private int n = 0;

    public WholeMap(int n_animals, int n_grass, int height, int width, double jungleRatio) {
        this.n_animals = n_animals;
        this.n_grass = n_grass;
        this.ySize = height;
        this.xSize = width;
        this.jungle = new JungleMap(
                new Vector2d((int)(xSize/2 - xSize*jungleRatio/2),(int)(ySize/2 - ySize*jungleRatio/2)),
                new Vector2d((int)(xSize/2 + xSize*jungleRatio/2),(int)(ySize/2 + ySize*jungleRatio/2)),
                (int)(xSize*jungleRatio),
                (int)(ySize*jungleRatio));
        initialSetup();
    }

    public void initialSetup() {
        for(int i = 0;i < n_animals;i++) {
            while(!place(new Animal(this, getRandomVect(), Constants.START_ENERGY, Genotype.generateRandomGens())));
        }
        for(int i = 0;i < n_grass;i++) {
            while(!plantGrassWorld(new Grass(getRandomVect())));
        }
    }

    public boolean plantGrassWorld(Grass grass) {
        if(!isOccupied(grass.getPosition()) && objectAt(grass.getPosition()) == null && !this.jungle.contains(grass.getPosition())){
            plants.put(grass.getPosition(), grass);
            return true;
        }
        return false;
    }
    public boolean plantGrassJungle(Grass grass) {
        if(!isOccupied(grass.getPosition()) && objectAt(grass.getPosition()) == null){
            plants.put(grass.getPosition(), grass);
            this.jungle.setCapacity(this.jungle.getCapacity() + 1);
            return true;
        }
        return false;
    }


    public Vector2d getRandomVect(){
        return new Vector2d(xGenerator.nextInt(xSize), yGenerator.nextInt(ySize));
    }

    public void remove(Vector2d position, boolean place) {
        if(animals.get(position) == null) {
            return;
        }
        int idx = 0;
        for(Animal animal : animals.get(position)) {
            if(animal.getPosition() != position) {
                animals.get(position).remove(idx);
                if(animals.get(position).isEmpty()){
                    animals.remove(position);
                }
                if(place) {
                    if (animals.get(animal.getPosition()) == null)
                    {
                        animals.put(animal.getPosition(), new ArrayList<Animal>());
                    }
                    animals.get(animal.getPosition()).add(animal);
                }
                break;
            }
            idx++;
        }
    }

    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())) {
            if (animals.get(animal.getPosition()) == null)
            {
                animals.put(animal.getPosition(), new ArrayList<Animal>());
            }
            animals.get(animal.getPosition()).add(animal);
            animal.attach(this);
            return true;
        }
        return false;
    }


    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return animals.get(position);
        } else if(plants.containsKey(position)) {
            return plants.get(position);
        }
        return null;
    }


    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }


    public void update(Vector2d oldPosition, Vector2d newPosition) {
        remove(oldPosition, true);
    }

    public ArrayList<Animal> getList() {
        ArrayList<Animal> AnimalsArray = new ArrayList<>();
        for(List<Animal> list : animals.values()){
            for(Animal animal: list) {
                AnimalsArray.add(animal);
            }
        }
        return AnimalsArray;
    }

    public void moveAnimals() {

        List<Animal> AnimalsArray = getList();

        // deciding about direction of move
        for(Animal animal : AnimalsArray) {
            int i = animal.gens.getRandomGen();
            animal.setDirection(MapDirection.values()[i]);
            animal.move();
        }
    }

    public void procreate() {
        for(List<Animal> list : animals.values()) {
            list.sort(Comparator.comparing(Animal::getAnimal_energy));
        }

        ArrayList<Animal> AnimalsTo = new ArrayList<Animal>();

        for(List<Animal> list : animals.values()) {
            if(list.size() >= 2) {
                if(list.get(0).getAnimal_energy() >= 0.5 * Constants.START_ENERGY &&
                        list.get(1).getAnimal_energy() >= 0.5 * Constants.START_ENERGY) {
                    AnimalsTo.add(list.get(0).procreate(list.get(1)));
                }
            }
        }
        for(Animal animal: AnimalsTo) {
            this.place(animal);
        }
    }

    public void decreaseEnergy() {
        this.n += 1;
        for(List<Animal> list : animals.values()){
            for(Animal animal: list) {
                animal.setEnergy(animal.getAnimal_energy() - Constants.DIFF_ENERGY);
                animal.setYears(animal.getYears() + 1);
            }
        }
    }
    public void removeDeadAnimal(Animal animal) {

        Vector2d position = animal.getPosition();
        this.animals.get(position).remove(animal);
        if (this.animals.get(position).isEmpty()) {
            this.animals.remove(position);
        }

    }

    public void removeDeads() {

        List<Animal> AnimalsArray = getList();
        for(Animal animal : AnimalsArray) {
            if (animal.getAnimal_energy() <= 0) {
                this.setDeads(this.getDeads() + 1);
                this.setDeadslive(this.getDeadslive() + animal.getYears());
                animal.setDead(this.n);
                this.removeDeadAnimal(animal);
            }
        }

    }

    public void eatGrass() {

        for(List<Animal> list : animals.values()) {
            list.sort(Comparator.comparing(Animal::getAnimal_energy));
        }
        ArrayList<Grass> toRemove = new ArrayList<Grass>();
        for(Grass grass : plants.values()) {
            if(animals.get(grass.getPosition()) != null) {
                int divider = 0;
                double BiggestEnergy = animals.get(grass.getPosition()).get(0).getAnimal_energy();
                for(Animal animal : animals.get(grass.getPosition())){
                    if (animal.getAnimal_energy() < BiggestEnergy) {
                        break;
                    }
                    divider += 1;
                }
                for(Animal animal : animals.get(grass.getPosition())) {
                    animal.setEnergy(animal.getAnimal_energy() + Constants.GRASS_ENERGY / divider);
                }
                toRemove.add(grass);
            }
        }
        for(Grass grass : toRemove) {
            if(this.jungle.contains(grass.getPosition())) {
                this.jungle.setCapacity(this.jungle.getCapacity() - 1);
            }
            plants.remove(grass.getPosition());
        }
    }

    public void plantGrasses() {
        while(!plantGrassWorld(new Grass(getRandomVect())));
        if(!this.jungle.isFull()) {
            int i = 0;
            while(!plantGrassJungle(new Grass(jungle.getRandomVect())) && ++i <= (this.jungle.xSize*this.jungle.ySize));
        }

    }

    public void simulateOneDay() {

        decreaseEnergy();
        removeDeads();
        moveAnimals();
        procreate();
        eatGrass();
        plantGrasses();

    }

    public int getDeads() {
        return this.deads;
    }
    public void setDeads(int n) {
        this.deads = n;
    }
    public int getDeadslive() {
        return this.deadslive;
    }
    public void setDeadslive(int n) {
        this.deadslive = n;
    }
}
