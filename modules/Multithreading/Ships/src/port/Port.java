package port;

import cargo.Cargo;
import context.Goods;
import ship.Ship;

import java.util.ArrayList;
import java.util.List;

public class Port {
    private Goods type;
    private List<Cargo> cargos;


    public Port(Goods type){
        this.type = type;
        cargos = new ArrayList<>();
    }

    public List<Cargo> getCargosList(){
        return this.cargos;
    }


    public Goods getType() {
        return type;
    }

    public synchronized void add(Ship ship) {
        cargos.add(ship.getCargo());
        System.out.println(ship.getName() + " unloaded");

    }
}

