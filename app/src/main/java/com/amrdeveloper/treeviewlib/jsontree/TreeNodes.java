package com.amrdeveloper.treeviewlib.jsontree;

import com.amrdeveloper.treeview.TreeNode;

import java.util.List;

public class TreeNodes {

    private final List<TreeNode> treeNodes;

    public TreeNodes(List<TreeNode> treeNodes) {
        this.treeNodes = treeNodes;
    }

    public List<TreeNode> getTreeNodes() {
        return treeNodes;
    }
}
