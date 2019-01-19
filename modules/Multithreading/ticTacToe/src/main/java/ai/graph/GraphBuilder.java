package ai.graph;

import game.XOField;

import java.util.*;
import java.util.concurrent.*;

public class GraphBuilder {
    public static GraphNode build(final XOField.Figure currentFigure, final XOField currentField) {
        final XOField.Figure nextFigure = currentFigure == XOField.Figure.O ? XOField.Figure.X : XOField.Figure.O;
        final Set<GraphNode> children = new HashSet<GraphNode>();

        for (int i = 0; i < XOField.getFieldSize(); i++) {
            for (int j = 0; j < XOField.getFieldSize(); j++) {
                if (currentField.getFigure(j, i) != null) {
                    continue;
                }
                final XOField newField = new XOField(currentField);
                newField.setFigures(j, i, nextFigure);
                children.add(build(nextFigure, newField));
            }
        }
        return new GraphNode(currentField, children);
    }

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public static GraphNode asincBuild(final XOField.Figure currentFigure, final XOField currentField, final int level) {
        if (level > 3) return new GraphNode(currentField, Collections.<GraphNode>emptySet());
        final List<Future> futures = new ArrayList<Future>();

        final XOField.Figure nextFigure = currentFigure == XOField.Figure.O ? XOField.Figure.X : XOField.Figure.O;
        final Set<GraphNode> children = new CopyOnWriteArraySet<>();

        for (int i = 0; i < XOField.getFieldSize(); i++) {
            for (int j = 0; j < XOField.getFieldSize(); j++) {
                if (currentField.getFigure(j, i) != null) {
                    continue;
                }
                final XOField newField = new XOField(currentField);
                newField.setFigures(j, i, nextFigure);

                final Future<?> future = EXECUTOR_SERVICE.submit(new Runnable() {
                    public void run() {
                        children.add(asincBuild(nextFigure, newField, level + 1));
                    }
                });
                futures.add(future);
            }
        }
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (final InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return new GraphNode(currentField, children);
    }
}