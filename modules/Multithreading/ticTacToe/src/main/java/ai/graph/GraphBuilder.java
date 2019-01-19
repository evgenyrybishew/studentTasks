package ai.graph;

import game.XOField;

import java.util.HashSet;
import java.util.Set;

public class GraphBuilder {
    public static GraphNode build(final XOField.Figure currentFigure, final XOField currentField) {
        final XOField.Figure nextFigure = currentFigure == XOField.Figure.O ? XOField.Figure.X : XOField.Figure.O;
        final Set<GraphNode> children = new HashSet<GraphNode>();

        for(int i = 0; i < XOField.getFieldSize(); i++) {
            for(int j = 0; j < XOField.getFieldSize(); j++) {
                if(currentField.getFigure(j, i) != null) {
                    continue;
                }
                final XOField newField = new XOField(currentField);
                newField.setFigures(j, i, nextFigure);
                children.add(build(nextFigure, newField));
            }
        }
        return new GraphNode(currentField, children);
    }
}
