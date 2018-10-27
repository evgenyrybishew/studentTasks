import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IntegrationTest {

    @Test
    public void doNextGens() throws IOException, NoSuchFieldException, IllegalAccessException, InterruptedException {

        Container container = new Container(4, 5, "test resource/forIntegrationTest1Gen.txt");


        for(int i = 0; i < 4; i++)
            container.doNextGen();


        Field mapField = container.getClass().getDeclaredField("map");
        mapField.setAccessible(true);
        int[][]map = (int[][]) mapField.get(container);

        List<String> anwer = Files.readAllLines(Paths.get("test resource/forIntegrationTestResut.txt"));
        List<String>result = new ArrayList<>();

        for(int i = 0; i < map.length; i++){
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < map[0].length; j++)
                sb.append(map[i][j]);
            result.add(sb.toString());
        }



        Assert.assertTrue(anwer.equals(result));

    }

}
