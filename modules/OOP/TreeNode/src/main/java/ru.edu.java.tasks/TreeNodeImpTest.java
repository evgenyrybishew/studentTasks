package ru.edu.java.tasks;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Set;

import static org.junit.Assert.*;

public class TreeNodeImpTest {

    @Test
    public void setGetParentTest() {

        TreeNode node = new TreeNodeImp("node");
        TreeNode parent = new TreeNodeImp("parent");
        node.setParent(parent);
        Assert.assertTrue(node.getParent() == parent);
    }

    @Test
    public void getRootTest() {

        TreeNode one = new TreeNodeImp("one");
        TreeNode two = new TreeNodeImp("two");
        TreeNode three = new TreeNodeImp("three");
        TreeNode four = new TreeNodeImp("four");

        one.setParent(two);
        two.setParent(three);
        three.setParent(four);

        Assert.assertTrue(four.getRoot() == four);
        Assert.assertTrue(three.getRoot() == four);
        Assert.assertTrue(two.getRoot() == four);
        Assert.assertTrue(one.getRoot() == four);
    }

    @Test
    public void isLeafTest() {

        TreeNode root = new TreeNodeImp("root");
        Assert.assertTrue(root.isLeaf());

        root = new TreeNodeImp("root", new TreeNodeImp("one"), new TreeNodeImp("two"));
        Assert.assertFalse(root.isLeaf());
    }

    @Test
    public void getChildCountTest() {
        TreeNode root = new TreeNodeImp("root", new TreeNodeImp("one"), new TreeNodeImp("two"));
        Assert.assertEquals(2, root.getChildCount());
    }

    @Test
    public void getChildrenIteratorTest() {

        TreeNode root = new TreeNodeImp("root");
        Assert.assertFalse(root.getChildrenIterator().hasNext());
        root = new TreeNodeImp("root", new TreeNodeImp("one"));
        Assert.assertTrue(root.getChildrenIterator().hasNext());
    }

    @Test
    public void addChildTest() throws NoSuchFieldException, IllegalAccessException {
        TreeNode root = new TreeNodeImp("root", new TreeNodeImp("one"), new TreeNodeImp("two"));

        Field field = root.getClass().getDeclaredField("children");
        field.setAccessible(true);
        Set<TreeNode> temp = (Set<TreeNode>) field.get(root);

        Assert.assertEquals(2, temp.size());
    }

    @Test
    public void removeChildTest() throws NoSuchFieldException, IllegalAccessException {

        TreeNode one = new TreeNodeImp("one");
        TreeNode two = new TreeNodeImp("two");
        TreeNode three = new TreeNodeImp("three");

        TreeNode root = new TreeNodeImp("root", one, two, three);

        root.removeChild(one);

        Field field = root.getClass().getDeclaredField("children");
        field.setAccessible(true);
        Set<TreeNode> temp = (Set<TreeNode>) field.get(root);
        Assert.assertEquals(2, temp.size());


        field = one.getClass().getDeclaredField("root");
        field.setAccessible(true);
        TreeNode node = (TreeNode) field.get(one);
        Assert.assertNull(node);

    }

    @Test
    public void isExpandedTest() {
        TreeNode one = new TreeNodeImp("one");
        Assert.assertFalse(one.isExpanded());
    }

    @Test
    public void setExpandedTest() throws NoSuchFieldException, IllegalAccessException {


        TreeNode zero = new TreeNodeImp("zero");
        TreeNode one = new TreeNodeImp("one", zero);
        TreeNode two = new TreeNodeImp("two");
        TreeNode three = new TreeNodeImp("three");

        TreeNode root = new TreeNodeImp("root", one, two, three);

        root.setExpanded(true);

        Field field = root.getClass().getDeclaredField("isExpanded");
        field.setAccessible(true);
        boolean result = (boolean) field.get(root);

        Assert.assertTrue(result);


        result = (boolean) field.get(one);
        Assert.assertTrue(result);
        result = (boolean) field.get(zero);
        Assert.assertTrue(result);

        result = (boolean) field.get(two);
        Assert.assertTrue(result);
        result = (boolean) field.get(three);
        Assert.assertTrue(result);
    }

    @Test
    public void getDataTest() {
        TreeNode root = new TreeNodeImp("root");
        Assert.assertTrue(root.getData().equals("root"));
    }

    @Test
    public void setData() throws NoSuchFieldException, IllegalAccessException{

        TreeNode node = new TreeNodeImp();
        node.setData("new data");

        Field field = node.getClass().getDeclaredField("data");
        field.setAccessible(true);
        String result = (String)field.get(node);

        Assert.assertTrue(result.equals("new data"));

        node.setData(12345);
        int resultInt = (int)field.get(node);
        Assert.assertEquals(resultInt, 12345);

    }

    @Test
    public void getTreePath() {


        TreeNode one = new TreeNodeImp();
        TreeNode two = new TreeNodeImp("two");
        TreeNode three = new TreeNodeImp("three");
        TreeNode four = new TreeNodeImp("four");
        TreeNode five = new TreeNodeImp("five");


        one.setParent(two);
        two.setParent(three);
        three.setParent(four);
        four.setParent(five);

        Assert.assertTrue(one.getTreePath().equals("five->four->three->two->empty"));

    }

    @Test
    public void findParent() {
        TreeNode one = new TreeNodeImp("one");
        TreeNode two = new TreeNodeImp("two");
        TreeNode three = new TreeNodeImp("three");
        TreeNode four = new TreeNodeImp("four");
        TreeNode five = new TreeNodeImp();


        one.setParent(two);
        two.setParent(three);
        three.setParent(four);
        four.setParent(five);


        Assert.assertTrue(one.findParent("four").equals(four));
        Assert.assertTrue(one.findParent("one").equals(one));
        Assert.assertTrue(one.findParent(null).equals(five));


    }

    @Test
    public void findChild() {

        TreeNode one1 = new TreeNodeImp("111");
        TreeNode one2 = new TreeNodeImp("112");


        TreeNode one = new TreeNodeImp("11", one1, one2);
        TreeNode two = new TreeNodeImp("12");
        TreeNode three = new TreeNodeImp("13");

        TreeNode root = new TreeNodeImp("root", one, two, three);

        Assert.assertTrue(root.findChild("111").equals(one1));
        Assert.assertTrue(root.findChild("13").equals(three));

    }
}