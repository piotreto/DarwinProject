package Map;

import Math.Vector2d;

import java.util.Random;

public class JungleMap {

    public Vector2d bottomLeft;
    public Vector2d upperRight;

    public int xSize;
    public int ySize;
    private int capacity = 0;

    public JungleMap(Vector2d bottomLeft, Vector2d upperRight, int xSize, int ySize) {
        this.bottomLeft = bottomLeft;
        this.upperRight = upperRight;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public Vector2d getRandomVect(){
        Random xGenerator = new Random();
        Random yGenerator = new Random();
        return new Vector2d(xGenerator.nextInt(this.xSize)+this.bottomLeft.x, yGenerator.nextInt(this.ySize)+this.bottomLeft.y);
    }

    public boolean contains(Vector2d position) {

        return position.precedes(upperRight) && position.follows(bottomLeft);

    }

    public boolean isFull() {
        return this.capacity >= this.xSize * this.ySize;
    }

    public void setCapacity(int n) {
        this.capacity = n;
    }

    public int getCapacity() {
        return this.capacity;
    }

}
