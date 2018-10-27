import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreadPart extends Thread {
    private ThreadPart next;
    private ThreadPart prev;

    private int[][] generalMap;
    private int[][] map;
    private int left;
    private int right;
    private int generation;
    private CyclicBarrier cyclicBarrier;

    private static final int FOR_BORN = 3;
    private static final int FOR_LIVE = 2;

    public ThreadPart(int[][] map, int left, int right, int generation) {
        this.map = new int[map.length][right - left + 1];
        this.left = left;
        this.right = right;
        this.generalMap = map;
        doNodeMap(map);
        this.generation = generation;

    }

    public void doNodeMap(int[][] map) {
        for (int i = 0; i < map.length; i++)
            for (int j = this.left, k = 0; j <= this.right; j++, k++)
                this.map[i][k] = map[i][j];
    }


    public ThreadPart getNext() {
        return next;
    }

    public void setNext(ThreadPart next) {
        this.next = next;
    }

    public ThreadPart getPrev() {
        return prev;
    }

    public void setPrev(ThreadPart prev) {
        this.prev = prev;
    }

    public int getValue(int i, int j) {
        return map[i][j];
    }

    public int[][] getMap() {
        return map;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    private int getValueOfCell(int i, int j) {

        try {
            return map[i][j];
        } catch (ArrayIndexOutOfBoundsException e) {

            if (i < 0 && j < 0)
                return this.getPrev().getValue(map.length - 1, map[0].length - 1);

            if (i >= map.length && j >= map[0].length)
                return this.getNext().getValue(0, 0);

            if (i >= map.length && j < 0)
                return this.getPrev().getValue(0, map[0].length - 1);

            if (i < 0 && j >= map[0].length)
                return this.getNext().getValue(map.length - 1, 0);


            if (i < 0 && j < 0 && i != 0)
                return this.getPrev().getValue(i - 1, 0);

            if (i >= map.length && j >= map[0].length && i != map.length - 1)
                return this.getNext().getValue(i + 1, 0);

            if (i < 0 && j >= map[0].length && i != 0)
                return this.getNext().getValue(i - 1, 0);

            if (i < 0 && j >= map[0].length && i != map.length - 1)
                return this.getPrev().getValue(i + 1, 0);

            if (i < 0)
                return map[map.length - 1][j];
            if (i >= map.length)
                return map[0][j];

            if (j < 0)
                return this.getPrev().getValue(i, map[0].length - 1);
            if (j >= map[0].length)
                return this.getNext().getValue(i, 0);
        }
        return 9;
    }

    private int neighbors(int i, int j) {
        int neighbors = 0;
        neighbors += getValueOfCell(i - 1, j - 1);
        neighbors += getValueOfCell(i - 1, j);
        neighbors += getValueOfCell(i - 1, j + 1);

        neighbors += getValueOfCell(i, j - 1);
        neighbors += getValueOfCell(i, j + 1);

        neighbors += getValueOfCell(i + 1, j - 1);
        neighbors += getValueOfCell(i + 1, j);
        neighbors += getValueOfCell(i + 1, j + 1);
        return neighbors;
    }

    private boolean isDied(int i, int j) {
        int n = neighbors(i, j);
        return !((n == FOR_BORN) || (n == FOR_LIVE));
    }

    private boolean isBorn(int i, int j) {
        return neighbors(i, j) == FOR_BORN;
    }


    public void doWork() {
        int[][] buffer = new int[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {

                if (map[i][j] == 0 && isBorn(i, j)) {
                    buffer[i][j] = 1;
                } else if (map[i][j] == 1 && isDied(i, j))
                    buffer[i][j] = 0;
                else
                    buffer[i][j] = map[i][j];
            }
        }
        updateGeneralMap(buffer);
    }

    private void updateGeneralMap(int[][] buffer) {
        for (int i = 0; i < buffer.length; i++) {
            for (int j = 0; j < buffer[i].length; j++) {
                generalMap[i][j + left] = buffer[i][j];
            }
        }
    }


    public void doSynchronized(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void run() {
        for (int i = 0; i < generation; i++) {
            doWork();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            doNodeMap(generalMap);
        }

    }
}

