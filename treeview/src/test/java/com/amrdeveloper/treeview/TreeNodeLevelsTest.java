package com.amrdeveloper.treeview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreeNodeLevelsTest {

    @Test
    public void testTreeNodeRoot() {
        TreeNode root = new TreeNode("Root", 0);
        assertEquals(root.getLevel(), 0);
    }

    @Test
    public void testTreeNodeLevelOne() {
        TreeNode root = new TreeNode("Root", 0);
        TreeNode child = new TreeNode("Child 1", 0);
        root.addChild(child);
        assertEquals(child.getLevel(), 1);
    }

    @Test
    public void testTreeNodeLevelTwo() {
        TreeNode root = new TreeNode("Root", 0);
        TreeNode child = new TreeNode("Child 1", 0);
        TreeNode childTwo = new TreeNode("Child 2", 0);
        child.addChild(childTwo);
        root.addChild(child);
        assertEquals(childTwo.getLevel(), 2);
    }

    @Test
    public void testTreeNodeLevelThree() {
        TreeNode root = new TreeNode("Root", 0);
        TreeNode child = new TreeNode("Child 1", 0);
        TreeNode childTwo = new TreeNode("Child 2", 0);
        TreeNode childThree = new TreeNode("Child 3", 0);
        childTwo.addChild(childThree);
        child.addChild(childTwo);
        root.addChild(child);
        assertEquals(childThree.getLevel(), 3);
    }

    @Test
    public void testTreeNodeLevelFour() {
        TreeNode root = new TreeNode("Root", 0);
        TreeNode child = new TreeNode("Child 1", 0);
        TreeNode childTwo = new TreeNode("Child 2", 0);
        TreeNode childThree = new TreeNode("Child 3", 0);
        TreeNode childFour = new TreeNode("Child 4", 0);
        childThree.addChild(childFour);
        childTwo.addChild(childThree);
        child.addChild(childTwo);
        root.addChild(child);
        assertEquals(childFour.getLevel(), 4);
    }
}
