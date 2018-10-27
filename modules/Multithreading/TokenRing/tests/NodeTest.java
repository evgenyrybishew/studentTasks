import data.Data;
import node.Node;
import node.TokenRingNode;
import org.junit.Assert;
import org.junit.Test;
import utility.TokenRingTopology;
import utility.Topology;

import java.lang.reflect.Field;
import java.util.concurrent.BlockingQueue;

public class NodeTest {

    @Test
    public void nodeGetTest() throws NoSuchFieldException, IllegalAccessException {
        Topology topology = new TokenRingTopology();
        int size = 5;
        String name = "test";
        String content = "/" + name + "/";
        Node node = new TokenRingNode(size, topology);

        Field queueField = node.getClass().getSuperclass().getDeclaredField("queue");
        queueField.setAccessible(true);
        BlockingQueue<Data> queue = (BlockingQueue<Data>) queueField.get(node);
        queue.add(new Data(name, content));

        Data fromNode = node.get();
        Assert.assertTrue(fromNode.getName().equals(name));
        Assert.assertTrue(fromNode.getContent().split("/")[1].trim().equals(name));
    }


    public class ThreadFornodePushTest extends Thread {

        private Node node;
        private int datasForPoll;

        @Override
        public void run() {

            Field queueField;
            try {
                queueField = node.getClass().getSuperclass().getDeclaredField("queue");
                queueField.setAccessible(true);
                BlockingQueue<Data> queue = (BlockingQueue<Data>) queueField.get(node);
                Thread.sleep(1000);
                for (int i = 0; i < datasForPoll + 1; i++) {
                    Thread.sleep(500);
                    System.out.println("take" + " " + queue.take().getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public ThreadFornodePushTest(Node node, int datasForPoll){
            this.node = node;
            this.datasForPoll = datasForPoll;
        }
    }



    @Test
    public void nodePushTest() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        Topology topology = new TokenRingTopology();
        int size = 5;
        int datas = 8;
        String name = "test";
        String content = "/" + name + "/";
        Node node = new TokenRingNode(size, topology);

        new ThreadFornodePushTest(node, size).start();

        for(int i = 0; i < datas; i++){
            Thread.sleep(500);
            Data data = new Data(name + i, content);
            System.err.println("\nadd" + " " + data.getName());
            node.push(data);
        }
        Thread.sleep(3000);

        Field queueField = node.getClass().getDeclaredField("queue");
        queueField.setAccessible(true);
        BlockingQueue<Data> queue = (BlockingQueue<Data>) queueField.get(node);

        Assert.assertEquals(queue.toArray().length, 2);
        Assert.assertTrue(queue.peek().getName().equals("test6"));

    }

}
