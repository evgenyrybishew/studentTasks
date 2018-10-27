import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContainerTest {

    String path = "test resource/containerDoMap.txt";

    @Test
    public void ContainerDoMapTest() throws NoSuchFieldException, IllegalAccessException, IOException {


        Container container = new Container(1, 1, path);

        Field mapField = container.getClass().getDeclaredField("map");
        mapField.setAccessible(true);
        int[][] map = (int[][]) mapField.get(container);

        StringBuilder fromObj = new StringBuilder();
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
                fromObj.append(map[i][j]);
        List<String> list = Files.readAllLines(Paths.get(path));
        StringBuilder fromFile = new StringBuilder();
        for (String s : list)
            fromFile.append(s);
        Assert.assertTrue(fromObj.toString().trim().equals(fromFile.toString().trim()));
    }

    @Test
    public void doDistributeWork() throws IOException, NoSuchFieldException, IllegalAccessException {
        Container container = new Container(1, 7, path);

        Field mainNodeField = container.getClass().getDeclaredField("main");
        mainNodeField.setAccessible(true);
        ThreadPart main = (ThreadPart) mainNodeField.get(container);

        int map[][] = new int[6][10];

        ThreadPart temp = main;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++)
                for (int k = temp.getLeft(), m = 0; k <= temp.getRight(); k++, m++)
                    map[j][k] = temp.getValue(j, m);
            temp = temp.getNext();
        }
        StringBuilder fromObj = new StringBuilder();
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
                fromObj.append(map[i][j]);
        List<String> list = Files.readAllLines(Paths.get(path));
        StringBuilder fromFile = new StringBuilder();
        for (String s : list)
            fromFile.append(s);
        Assert.assertTrue(fromObj.toString().trim().equals(fromFile.toString().trim()));
    }

    @Test
    public void doNextGenTest1() throws IOException, NoSuchFieldException, IllegalAccessException {
        String testFilePath = "test resource/forDoNextGenTest1.txt";
        Container container = new Container(1, 7, testFilePath);
        container.doNextGen();


        Field mapField = container.getClass().getDeclaredField("map");
        mapField.setAccessible(true);
        int[][]map = (int[][]) mapField.get(container);

        List<String>anwer = Files.readAllLines(Paths.get("test resource/forDoNextGenTest1Answer.txt"));
        List<String>result = new ArrayList<>();

        for(int i = 0; i < map.length; i++){
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < map[0].length; j++)
                sb.append(map[i][j]);
            result.add(sb.toString());
        }

        Assert.assertTrue(anwer.equals(result));
    }

    @Test
    public void doNextGenTest2() throws IOException, NoSuchFieldException, IllegalAccessException {
        String testFilePath = "test resource/forDoNextGenTest2.txt";
        Container container = new Container(1, 7, testFilePath);
        container.doNextGen();

        Field mapField = container.getClass().getDeclaredField("map");
        mapField.setAccessible(true);
        int[][]map = (int[][]) mapField.get(container);

        List<String>anwer = Files.readAllLines(Paths.get("test resource/forDoNextGenTest2Answer.txt"));
        List<String>result = new ArrayList<>();

        for(int i = 0; i < map.length; i++){
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < map[0].length; j++)
                sb.append(map[i][j]);
            result.add(sb.toString());
        }

        Assert.assertTrue(anwer.equals(result));
    }

    @Test
    public void doNextGenTest3() throws IOException, NoSuchFieldException, IllegalAccessException {
        String testFilePath = "test resource/forDoNextGenTest3.txt";
        Container container = new Container(1, 7, testFilePath);
        container.doNextGen();

        Field mapField = container.getClass().getDeclaredField("map");
        mapField.setAccessible(true);
        int[][]map = (int[][]) mapField.get(container);

        List<String>anwer = Files.readAllLines(Paths.get("test resource/forDoNextGenTest3Answer.txt"));
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


