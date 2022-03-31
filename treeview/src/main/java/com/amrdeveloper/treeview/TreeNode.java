/*
 * MIT License
 *
 * Copyright (c) 2022 AmrDeveloper (Amr Hesham)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.amrdeveloper.treeview;

import java.util.LinkedList;

/**
 * TreeNode is a container for the value to represent a node on the TreeView
 */
public class TreeNode {

    private Object value;
    private TreeNode parent;
    private LinkedList<TreeNode> children;
    private int layoutId;
    private int level;
    private boolean isExpanded;
    private boolean isSelected;

    public TreeNode(Object value, int layoutId) {
        this.value = value;
        this.parent = null;
        this.children = new LinkedList<>();
        this.layoutId = layoutId;
        this.level = 0;
        this.isExpanded = false;
        this.isSelected = false;
    }

    public void addChild(TreeNode child) {
        child.setParent(this);
        child.setLevel(level + 1);
        children.add(child);
        updateNodeChildrenDepth(child);
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }

    public LinkedList<TreeNode> getChildren() {
        return children;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    private void updateNodeChildrenDepth(TreeNode node) {
        if (node.getChildren().isEmpty()) return;
        for (TreeNode child : node.getChildren()) {
            child.setLevel(node.getLevel() + 1);
        }
    }
}
