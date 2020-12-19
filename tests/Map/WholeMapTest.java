package Map;

import Assets.Animal;
import Assets.Grass;
import Constants.Constants;
import org.junit.jupiter.api.Test;
import Math.*;

import static org.junit.jupiter.api.Assertions.*;

class WholeMapTest {

    @Test
    public void WholeMapTest() {
        WholeMap map = new WholeMap(0, 0,Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH,
                Constants.JUNGLE_RATIO);
        Animal fox = new Animal(map, new Vector2d(10, 10), 150, Genotype.generateRandomGens());
        Animal bat = new Animal(map, new Vector2d(20, 20), 150, Genotype.generateRandomGens());

        //validating gens
        for(int i = 0;i < 8;i++) {
            assertTrue(fox.getGens().countGens()[i] > 0);
            assertTrue(bat.getGens().countGens()[i] > 0);
        }

        map.place(fox);
        map.place(bat);

        // testing placing animals on map
        assertTrue(map.isOccupied(fox.getPosition()));
        assertTrue(map.isOccupied(bat.getPosition()));

        Vector2d oldPositionFox = fox.getPosition();
        Vector2d oldPositionBat = bat.getPosition();

        // testing moving animals
        map.moveAnimals();

        assertEquals(fox.getPosition(), oldPositionFox.add(fox.getDirection().toUnitVector()));
        assertEquals(bat.getPosition(), oldPositionBat.add(bat.getDirection().toUnitVector()));

        double oldEnergyFox = fox.getAnimal_energy();
        double oldEnergyBat = fox.getAnimal_energy();

        //testing daily energy decreasing
        map.decreaseEnergy();

        assertEquals(fox.getAnimal_energy(), oldEnergyFox - Constants.DIFF_ENERGY);
        assertEquals(bat.getAnimal_energy(), oldEnergyBat - Constants.DIFF_ENERGY);

        Animal deadAnimal = new Animal(map, new Vector2d(1,1), 0, Genotype.generateRandomGens());

        map.place(deadAnimal);

        //testing removing dead animals from map
        map.removeDeads();

        assertNull(map.objectAt(deadAnimal.getPosition()));
        assertNotNull(map.objectAt(fox.getPosition()));
        assertNotNull(map.objectAt(bat.getPosition()));

        map.plantGrassWorld(new Grass(fox.getPosition().add(fox.getDirection().toUnitVector()))); // we put grass on the field
        // where fox is going to move

        oldEnergyFox = fox.getAnimal_energy();

        fox.move(); // making move by fox

        //testing eating grass by animals
        map.eatGrass();

        assertEquals(fox.getAnimal_energy(), oldEnergyFox + Constants.GRASS_ENERGY);


        // testing procreating function

        oldEnergyBat = bat.getAnimal_energy();
        oldEnergyFox = fox.getAnimal_energy();

        Animal strangeMix = fox.procreate(bat);

        assertEquals(strangeMix.getAnimal_energy(), 0.25 * oldEnergyBat + 0.25 * oldEnergyFox);
        assertEquals(fox.getAnimal_energy(), 0.75 * oldEnergyFox);
        assertEquals(bat.getAnimal_energy(), 0.75 * oldEnergyBat);

    }
}

