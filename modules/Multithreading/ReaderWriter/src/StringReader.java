import resurses.Resource;

public class StringReader extends Thread {

    Resource resource;

    private String name = "StringReader";

    public StringReader(Resource resource) {
        this.resource = resource;
        this.setName(name);
    }

    @Override
    public void run() {
        System.out.println("Thread " + this.getName() + this.getId());
        resource.read();
    }
}
