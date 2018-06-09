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
    public void isExpanded() {
    }

    @Test
    public void setExpanded() {
    }

    @Test
    public void getData() {
    }

    @Test
    public void setData() {
    }

    @Test
    public void getTreePath() {
    }

    @Test
    public void findParent() {
    }

    @Test
    public void findChild() {
    }
}