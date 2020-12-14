package Data;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class readJSON {
    private static readJSON instance = null;
    public int width;
    public int height;
    public int animalsOnStart;
    public int startEnergy;
    public int grassOnStart;
    public int moveEnergy;
    public double jungleRatio;
    public int plantEnergy;
    public int speed;

    public static readJSON getInstance() {
        if (instance == null) {
            Gson gson = new Gson();
            String path = "./src/Data/params.json";
            try {
                JsonReader reader = new JsonReader(new FileReader(path));
                instance = gson.fromJson(reader, readJSON.class);
            } catch (FileNotFoundException e) {
                instance = new readJSON();
                e.printStackTrace();
            }
        }
        return instance;
    }

}

