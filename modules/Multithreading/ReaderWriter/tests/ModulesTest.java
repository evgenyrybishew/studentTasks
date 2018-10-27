import org.junit.Assert;
import org.junit.Test;
import resurses.FileSystem;
import resurses.Resource;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class ModulesTest {





    private Set<String> createWordSetFromText(String text){
        String[] words = text.split("\\s");
        Set<String>result = new HashSet<>();
        for(String s : words)
            result.add(s.trim());
        return result;
    }

    @Test
    public void MultithreadTest() throws IOException, InterruptedException {
        String pathForMainPartOfText = "resources/forModulTest/mainPart.txt";

        FileSystem.createFile(pathForMainPartOfText);

        Resource resource = new FileSystem(pathForMainPartOfText);

        for (int i = 1; i < 6; i++){
            new StringReader(resource).start();
            StringWriter tempSW1 = new StringWriter(resource);
            tempSW1.setBuffer(Tests.readFile("resources/forModulTest/part" + i + ".txt"));
            tempSW1.start();
            new StringReader(resource).start();
            StringWriter tempSW2 = new StringWriter(resource);
            tempSW2.setBuffer(Tests.readFile("resources/forModulTest/newPart" + i + ".txt"));
            tempSW2.start();


        }

        Thread.sleep(2000);
        String result = Tests.readFile(pathForMainPartOfText);
        String pattern = Tests.readFile("resources/forModulTest/resultText.txt");
        FileSystem.deleteFile(pathForMainPartOfText);
        Assert.assertTrue(createWordSetFromText(result).equals(createWordSetFromText(pattern)));
    }
}
