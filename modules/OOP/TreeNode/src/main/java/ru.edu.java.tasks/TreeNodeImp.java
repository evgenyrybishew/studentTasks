package ru.edu.java.tasks;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TreeNodeImp implements TreeNode {

    private TreeNode root;
    private Set<TreeNode> children;
    private Object data;
    private boolean isExpanded;


    public TreeNodeImp(Object data) {
        this.root = null;
        this.children = new HashSet<>();
        this.data = data;
        isExpanded = false;
    }

    public TreeNodeImp() {
        this.root = null;
        this.children = new HashSet<>();
        this.data = null;
        isExpanded = false;
    }


    public TreeNodeImp(Object data, TreeNode... nodes) {
        this.root = null;
        this.children = new HashSet<>();
        this.data = data;
        for (TreeNode node : nodes)
            this.children.add(node);
        isExpanded = false;
    }


    @Override
    public TreeNode getParent() {
        return root;
    }

    @Override
    public void setParent(TreeNode parent) {
        if (parent == this || parent == this.getParent())
            return;
        this.root = parent;
        this.root.addChild(this);
    }

    @Override
    public TreeNode getRoot() {
        if (this.root == null)
            return this;

        return this.root.getRoot();
    }

    @Override
    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    @Override
    public int getChildCount() {
        return this.children.size();
    }

    @Override
    public Iterator<TreeNode> getChildrenIterator() {
        return this.children.iterator();
    }

    @Override
    public void addChild(TreeNode child) {
        if (this.children.contains(child))
            return;
        this.children.add(child);
        child.setParent(this);

    }

    @Override
    public boolean removeChild(TreeNode child) {

        if (this.children.contains(child)) {
            child.setParent(null);
            return this.children.remove(child);
        }
        return false;
    }

    @Override
    public boolean isExpanded() {
        return this.isExpanded;
    }

    @Override
    public void setExpanded(boolean expanded) {

        this.isExpanded = expanded;
        if (this.children.size() != 0)

            for (TreeNode child : this.children)
                child.setExpanded(expanded);
    }

    @Override
    public Object getData() {
        return this.data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String getTreePath() {

        String path;
        if (this.data == null)
            path = "empty";
        else
            path = this.data.toString();

        if (this.root != null)
            return root.getTreePath() + "->" + path;

        return path;
    }

    @Override
    public TreeNode findParent(Object data) {


        if (data == null && this.data == null)
            return this;


        if (this.data.equals(data))
            return this;

        return this.root.findParent(data);
    }

    @Override
    public TreeNode findChild(Object data) {

        if (this.data.equals(data))
            return this;

        if (this.children != null && this.children.size() != 0) {

            for (TreeNode node : this.children) {
                TreeNode temp = node.findChild(data);

                if (temp != null)
                    if (temp.getData().equals(data))
                        return temp;
            }
        }
        return null;
    }
}
