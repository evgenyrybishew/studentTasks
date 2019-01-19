package start;


import ai.graph.GraphBuilder;
import ai.graph.GraphNode;
import ai.graph.helpers.GraphHelper;
import game.XOField;

public class Main {
    private static int level = 0;

    public static void main(String[] args) {
        final GraphNode root = GraphBuilder.build(XOField.Figure.X, new XOField());
        System.out.println(root.getNode());
        GraphHelper.show(root, level);
    }
}
