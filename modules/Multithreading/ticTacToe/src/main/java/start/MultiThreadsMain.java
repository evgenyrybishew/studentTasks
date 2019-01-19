package start;

import ai.graph.GraphBuilder;
import ai.graph.GraphNode;
import ai.graph.helpers.GraphHelper;
import game.XOField;

public class MultiThreadsMain {

    private static int level = 0;
    public static void main(String[] args) {
        final GraphNode root = GraphBuilder.asincBuild(XOField.Figure.X, new XOField(), level);
        System.out.println(root.getNode());
        System.out.println(GraphHelper.countNode(root));
    }
}
