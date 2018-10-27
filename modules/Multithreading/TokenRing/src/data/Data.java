package data;

public class Data {
    private String name;
    private String content;

    public Data(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Data(){
        this.content = null;
        this.name = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
