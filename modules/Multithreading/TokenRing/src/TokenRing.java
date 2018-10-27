import data.Data;
import node.Node;
import node.TokenRingNode;
import utility.Direction;
import utility.TokenRingTopology;
import utility.Topology;

import java.io.Closeable;
import java.util.*;

public class TokenRing implements Closeable {

    private Node main = null;
    private int nodes;
    private int capacityForNode;
    private List<Data> data;
    Topology topology = new TokenRingTopology();

    public TokenRing(int capacityForNode, int nodes, int packages) {
        this.capacityForNode = capacityForNode;
        this.nodes = nodes;
        init();
        this.data = dataGenerator(packages);
        setData(this.data);
    }

    private static String contentGenerate() {
        Random rnd = new Random();
        int length = rnd.nextInt(20);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++)
            result.append((char) rnd.nextInt(127));
        return result.toString();
    }


    private static List<Data> dataGenerator(int packages) {
        List<Data> result = new ArrayList<>();
        for (int i = 0; i < packages; i++)
            result.add(new Data("data #" + i, contentGenerate()));
        ;
        return result;
    }

    public List<Data> getData() {
        return this.data;
    }

    public Node getMain() {
        return this.main;
    }

    private void init() {
        if (main == null) {
            main = new TokenRingNode(capacityForNode, topology);
            topology.add(0, main);
        }
        for (int i = 0; i < nodes - 1; i++) {
            Node temp = new TokenRingNode(capacityForNode, topology);
            temp.setNodeId(i + 1);
            topology.add(temp.getNodeId(), temp);
        }
    }

    public Node getNext(Node node) {
        return topology.getNeighbor(node, Direction.NEXT);
    }

    public Node getPreve(Node node) {
        return topology.getNeighbor(node, Direction.PREV);
    }


    private void setData(List<Data> data) {
        if (data == null)
            return;
        int size = data.size() > (nodes * capacityForNode) ? nodes * capacityForNode : data.size();
        Node temp = this.main;
        for (int i = 0; i < size; i++) {
            temp.setData(data.get(i));
            temp = this.getNext(temp);
        }
    }

    public void start() {
        Node temp = this.main;
        for (int i = 0; i < nodes - 1; i++) {
            temp.start();
            temp = this.getNext(temp);
        }
    }

    public void stop() {
        Node temp = this.main;
        for (int i = 0; i < nodes; i++) {
            temp.stopWork();
            temp = this.getNext(temp);
        }
    }


    @Override
    public void close() {
        stop();
    }
}







