package Map;

import Math.Vector2d;

import java.util.Random;

public class JungleMap {

    public Vector2d bottomLeft;
    public Vector2d upperRight;

    public int xSize;
    public int ySize;
    public int capacity;

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

}
