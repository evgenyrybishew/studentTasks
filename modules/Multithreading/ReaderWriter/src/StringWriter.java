import resurses.FileSystem;
import resurses.Resource;

public class StringWriter extends Thread {


    private String name = "StringWriter";

    private Resource resource;
    private String buffer;

    public StringWriter(Resource resource) {
        this.resource = resource;
        this.setName(name);
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        if (this.buffer == null) {
            System.err.println("Unspecified write text");
            return;
        }

        resource.write(this.buffer);
        System.err.println("Thread " + this.getName() + this.getId());
    }


}
