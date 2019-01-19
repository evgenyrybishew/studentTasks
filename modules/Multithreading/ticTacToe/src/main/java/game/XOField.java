package game;

import java.util.Arrays;

public class XOField {

    public enum Figure {
        X, O
    }
    private final static int FIELD_SIZE = 3;
    private final Figure[][] figures = new Figure[FIELD_SIZE][FIELD_SIZE];


    public XOField(final XOField field) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            System.arraycopy(field.figures[i], 0, figures[i], 0, 3);
        }
    }

    public XOField(){}

    public void setFigures(final int x, final int y, final Figure figure) {
        figures[x][y] = figure;
    }

    public static int getFieldSize() {
        return FIELD_SIZE;
    }

    public Figure getFigure(final int x, final int y) {
        return figures[x][y];
    }

    @Override
    public boolean equals(Object obj) {
       if(this == obj) return true;
       if(obj == null || getClass() != obj.getClass()) return false;
       XOField xoField = (XOField) obj;
       return Arrays.deepEquals(figures, xoField.figures);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(figures);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                sb.append(figures[j][i] != null ? figures[j][i] : " ");
                if(j != 2) {
                    sb.append("|");
                }
                else {
                    sb.append("\n");
                }
            }
            if(i != 2) {
                sb.append("-----\n");
            }
        }
        return sb.toString();
    }
}
