package ru.edu.java.tasks;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TreeNodeImp implements TreeNode {

    TreeNode root;
    Set<TreeNode>children;
    Object data;


    public TreeNodeImp(Object data) {
        this.root = null;
        this.children = new HashSet<>();
        this.data = data;
    }

    public TreeNodeImp() {
        this.root = null;
        this.children = new HashSet<>();
        this.data = null;
    }


    public TreeNodeImp(Object data, TreeNode... nodes){
        this.root = null;
        this.children = new HashSet<>();
        this.data = data;
        for(TreeNode node : nodes)
            this.children.add(node);
    }


    @Override
    public TreeNode getParent() {
        return root;
    }

    @Override
    public void setParent(TreeNode parent) {
        if(parent == this)
            return;

        if(parent == this.getParent())
            return;

        this.root = parent;
        this.root.addChild(this);
    }

    @Override
    public TreeNode getRoot() {
        if(this.root == null)
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
        this.children.add(child);
        child.setParent(this);

    }

    @Override
    public boolean removeChild(TreeNode child) {

        if(this.children.contains(child)){
            child.setParent(null);
            return this.children.remove(child);
        }
        return false;
    }

    @Override
    public boolean isExpanded() {
        return false;
    }

    @Override
    public void setExpanded(boolean expanded) {

    }

    @Override
    public Object getData() {
        return this.data;
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public String getTreePath() {
        return null;
    }

    @Override
    public TreeNode findParent(Object data) {
        return null;
    }

    @Override
    public TreeNode findChild(Object data) {
        return null;
    }
}
