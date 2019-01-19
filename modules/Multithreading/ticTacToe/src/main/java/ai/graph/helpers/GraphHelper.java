package ai.graph.helpers;

import ai.graph.GraphNode;

public class GraphHelper {
    private static final int LEVEL = 7;

    public static void show(final GraphNode node, final int level) {
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < level * LEVEL; i++) {
            sb.append(".");
        }
        sb.append("   ");
        final String space = sb.toString();
        final String nodeStr = node.getNode().toString();
        final String updateNodeStr = nodeStr.replace("\n", "\n" + space);
        System.out.println("\n" + space + updateNodeStr);
        for(GraphNode child : node.getChildren()) {
            show(child, level + 1);
        }
    }

    public static int countNode(final GraphNode root) {
        int count = 1;
        for(GraphNode child : root.getChildren()) {
            count += countNode(child);
        }
        return count;
    }


}
