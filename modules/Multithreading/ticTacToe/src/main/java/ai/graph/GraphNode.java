package ai.graph;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import game.XOField;

import java.util.Set;

public class GraphNode {
    private final XOField node;
    private final Set<GraphNode> children;

    public GraphNode(final XOField field, Set<GraphNode>children) {
        this.node = field;
        this.children = children;
    }

    public XOField getNode() {
        return node;
    }

    public Set<GraphNode> getChildren() {
        return children;
    }
}
