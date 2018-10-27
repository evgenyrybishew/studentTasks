import org.junit.Assert;
import org.junit.Test;
import resurses.FileSystem;
import resurses.Resource;


import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Tests {

    String pathTofileForReadTest = "resources/forReadTest.txt";


    public static String readFile(String path) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(path));
        String source = "";
        for (String str : lines)
            source += str + "\n";
        return source;
    }





    @Test
    public void readerTest() throws NoSuchFieldException, IllegalAccessException, IOException, InterruptedException {
        Resource fs = new FileSystem(pathTofileForReadTest);
        StringReader sr = new StringReader(fs);
        sr.start();

        Thread.sleep(300);

        Field contenttField = fs.getClass().getSuperclass().getDeclaredField("content");
        contenttField.setAccessible(true);
        String content = (String)contenttField.get(fs);

        Assert.assertTrue(content.equals(readFile(pathTofileForReadTest)));
    }

    @Test
    public void writteTest() throws IOException, InterruptedException {
        String pathForTestFile = "resources/forWriteTest.txt";

        Resource fs = new FileSystem(pathForTestFile);
        StringWriter sw = new StringWriter(fs);
        sw.setBuffer(readFile("resources/forReadTest.txt"));
        sw.start();

        Thread.sleep(300);

        String textFromForWritteTest = readFile(pathForTestFile);
        FileSystem.deleteFile(pathForTestFile);
        Assert.assertTrue(textFromForWritteTest.trim().equals(readFile(pathTofileForReadTest).trim()));

    }
}
