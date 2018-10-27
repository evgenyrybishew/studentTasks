package node;

import utility.Direction;
import utility.FontColor;
import utility.Topology;

public class TokenRingNode extends Node {


    public TokenRingNode(int storage, Topology topology) {
        super(storage, topology);
    }


    @Override
    public void run() {
        while (!this.stop) {
            this.push(this.topology.getNeighbor(this, Direction.NEXT).get());
        }
        System.out.println(FontColor.PURPLE + "node #" + this.getNodeId() + "was stoped" + FontColor.RESET);
    }
}
