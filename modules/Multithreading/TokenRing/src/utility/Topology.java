package utility;

import node.Node;

import java.util.Map;

public abstract class Topology<E> {
    protected Map<Integer, Node>map;

    public abstract void add(int id, Node node);
    public abstract Node getNeighbor(Node node, E flag);




}
