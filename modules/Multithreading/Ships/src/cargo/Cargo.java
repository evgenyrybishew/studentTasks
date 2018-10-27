package cargo;

import context.Goods;

public class Cargo {

    private Goods type;
    private String name;
    private int count;

    public Cargo(Goods type, String name, int count) {
        this.type = type;
        this.name = name;
        this.count = count;
    }

    public Goods getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
