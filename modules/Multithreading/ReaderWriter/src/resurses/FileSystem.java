package resurses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileSystem extends Resource {

    private String path;
    public FileSystem(String path){
        this.path = path;
    }

    @Override
    public void read() {
        synchronized (this){
            readers++;
        }
        try {
            List<String> lines = Files.readAllLines(Paths.get(this.path));
            this.content = "";
            for(String s : lines)
                this.content += s + "\n";
        } catch (IOException e) {
            System.err.println("Error reading file");
            e.printStackTrace();
        }

        synchronized (this){
            readers--;
        }
    }

    @Override
    public void write(String data) {

        while(readers != 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (this) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.path, true))) {
                bw.write(data + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static boolean deleteFile(String path){
        File file = new File(path);
        if(file.delete())
            return true;
        return false;
    }

    public static boolean createFile(String path) throws IOException {
        File file = new File(path);
        if(file.createNewFile())
            return true;
        return false;
    }
}
