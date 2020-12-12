package Math;

import java.util.Random;

public class Genotype {

    private int[] gens;
    private int[] gensCounter;

    public Genotype() {

    }


    public Genotype(int [] gens) {
        this.gens = gens;
        this.gensCounter = countGens();
        validate();
    }

    public int getRandomGen() {
        Random generator = new Random();
        int i = generator.nextInt(32);
        return gens[i];

    }

    public static Genotype generateRandomGens() {
        int [] new_gens = new int [32];
        Random generator = new Random();
        for(int i = 0;i < 32;i++) {
            new_gens[i] = generator.nextInt(8);
        }
        return new Genotype(new_gens);
    }

    public int[] countGens() {
        int [] counter = new int[8];
        for(int i : this.gens) {
            counter[i] += 1;
        }
        return counter;
    }



    public void validate() {
        boolean valide = false;
        while(!valide){
            valide = true;
            for(int i = 0;i < 8;i++){
                if(this.gensCounter[i] == 0){
                    valide = false;
                    int prev = Math.floorMod(i-1,8);
                    int next = Math.floorMod(i+1, 8);
                    if(gensCounter[prev] > 0) {
                        gensCounter[prev] -= 1;
                        gensCounter[i] += 1;
                    }
                    if(gensCounter[next] > 0) {
                        gensCounter[next] -= 1;
                        gensCounter[i] += 1;
                    }
                }
            }
        }
        int idx = 0;
        for(int i = 0;i < 8;i++) {
            while (gensCounter[i] > 0) {
                gens[idx] = i;
                gensCounter[i] -= 1;
                idx += 1;
            }
        }
        this.gensCounter = countGens();
    }

    public Genotype procreate(Genotype other) {

        Genotype g = new Genotype();
        g.gens = new int[32];
        g.gensCounter = new int[]{0,0,0,0,0,0,0,0};
        Random generator = new Random();
        int split1 = generator.nextInt(32);
        int split2 = split1;
        while(split1 == split2) {
            split2 = generator.nextInt(32);
        }
        if (split1 > split2) {
            int temp = split2;
            split2 = split1;
            split1 = temp;
        }
        for(int i = 0;i < 32;i++){
            if (i < split1 || i > split2){
                g.gens[i] = this.gens[i];
                g.gensCounter[g.gens[i]]++;
            } else {
                g.gens[i] = other.gens[i];
                g.gensCounter[g.gens[i]]++;
            }
        }
        g.validate();
        return g;
    }

    public int[] getGensCounter() {
        return this.gensCounter;
    }

    @Override
    public String toString() {
        String result = String.format("[");
        for(int i = 0;i < 31;i++) {
            result += String.format("%d,", this.gens[i]);
        }
        result += String.format("%d]", this.gens[31]);
        return result;
    }

}
