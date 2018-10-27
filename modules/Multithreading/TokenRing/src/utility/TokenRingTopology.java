package utility;

import node.Node;

import java.util.HashMap;

public class TokenRingTopology extends Topology {
    private int counter;


    public TokenRingTopology(){
        this.counter = 0;
        this.map = new HashMap();
    }


    @Override
    public void add(int id, Node node) {
        counter++;
        this.map.put(id, node);

    }

    @Override
    public Node getNeighbor(Node node, Object flag) {
        Direction direction = (Direction)flag;

        if(direction.equals(Direction.NEXT)){
            int nextId = node.getNodeId() + 1;
            if (nextId >= counter)
                return (Node) map.get(0);
            return (Node) map.get(nextId);
        }
        else if(direction.equals(Direction.PREV)){
            int prevId = node.getNodeId() - 1;
            if (prevId < 0)
                return (Node) map.get(this.counter - 1);
            return (Node) map.get(prevId);
        }
        return null;
    }

}
