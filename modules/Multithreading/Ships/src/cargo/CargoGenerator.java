package cargo;

import cargo.Cargo;
import context.Goods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class CargoGenerator {

    public static Cargo generate(){

        Random random = new Random();
        int min = 10000, max = 100000;
        int count = min + random.nextInt(max - min + 1);
        List<String> names = null;
        try {
            names = Files.readAllLines(Paths.get("/Users/mac/Developer/Projects/Java Projects/Multithreading/Ships/src/cargo/names.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Cargo(Goods.getRandomGood(), names.get(random.nextInt(names.size())).trim(), count);
    }

}
