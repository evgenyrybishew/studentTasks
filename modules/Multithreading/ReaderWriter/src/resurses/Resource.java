package resurses;

public abstract class Resource {

    protected int readers = 0;
    protected String content;

   public abstract void read();
   public abstract void write(String data);

}
