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
import java.util.List;

/**
 * Manager class for TreeNodes to easily apply operations on them
 * and to make it easy for testing and extending
 */
public class TreeNodeManager {

    /**
     * Collection to save the current tree nodes
     */
    private final LinkedList<TreeNode> rootsNodes;

    /**
     * Simple constructor
     */
    public TreeNodeManager() {
        this.rootsNodes = new LinkedList<>();
    }

    /**
     * Get TreeNode from the current nodes by index
     * @param index of node to get it
     * @return TreeNode from by index from current tree nodes if exists
     */
    public TreeNode get(int index) {
        return rootsNodes.get(index);
    }

    /**
     * Add new node to the current tree nodes
     * @param node to add it to the current tree nodes
     * @return true of this node is added
     */
    public boolean addNode(TreeNode node) {
        return rootsNodes.add(node);
    }

    /**
     * Clear the current nodes and insert new nodes
     * @param newNodes to update the current nodes with them
     */
    public void updateNodes(List<TreeNode> newNodes) {
        rootsNodes.clear();
        rootsNodes.addAll(newNodes);
    }

    /**
     * Delete one node from the visible nodes
     * @param node to delete it from the current nodes
     * @return true of this node is deleted
     */
    public boolean removeNode(TreeNode node) {
        return rootsNodes.remove(node);
    }

    /**
     * Clear the current nodes
     */
    public void clearNodes() {
        rootsNodes.clear();
    }

    /**
     * Get the current number of visible nodes
     * @return the size of visible nodes
     */
    public int size() {
        return rootsNodes.size();
    }

    /**
     * Collapsing node and all of his children
     * @param node The node to collapse it
     * @return the index of this node if it exists in the list
     */
    public int collapseNode(TreeNode node) {
        int position = rootsNodes.indexOf(node);
        if (position != -1 && node.isExpanded()) {
            node.setExpanded(false);
            LinkedList<TreeNode> deletedParents = new LinkedList<>(node.getChildren());
            rootsNodes.removeAll(node.getChildren());
            for (int i = position + 1; i < rootsNodes.size(); i++) {
                TreeNode iNode = rootsNodes.get(i);
                if (deletedParents.contains(iNode.getParent())) {
                    deletedParents.add(iNode);
                    deletedParents.addAll(iNode.getChildren());
                }
            }
            rootsNodes.removeAll(deletedParents);
        }
        return position;
    }

    /**
     * Expanding node and all of his children
     * @param node The node to expand it
     * @return the index of this node if it exists in the list
     */
    public int expandNode(TreeNode node) {
        int position = rootsNodes.indexOf(node);
        if (position != -1 && !node.isExpanded()) {
            node.setExpanded(true);
            rootsNodes.addAll(position + 1, node.getChildren());
            for (TreeNode child : node.getChildren()) {
                if (child.isExpanded()) updateExpandedNodeChildren(child);
            }
        }
        return position;
    }

    /**
     * Update the list for expanded node
     * to expand any child of his children that is already expanded before
     * @param node that just expanded now
     */
    private void updateExpandedNodeChildren(TreeNode node) {
        int position = rootsNodes.indexOf(node);
        if (position != -1 && node.isExpanded()) {
            rootsNodes.addAll(position + 1, node.getChildren());
            for (TreeNode child : node.getChildren()) {
                if (child.isExpanded()) updateExpandedNodeChildren(child);
            }
        }
    }

    /**
     *
     * @param  node The node to collapse the branch of it
     * @return the index of this node if it exists in the list
     */
    public int collapseNodeBranch(TreeNode node) {
        int position = rootsNodes.indexOf(node);
        if (position != -1 && node.isExpanded()) {
            node.setExpanded(false);
            for (TreeNode child : node.getChildren()) {
                if (!child.getChildren().isEmpty()) collapseNodeBranch(child);
                rootsNodes.remove(child);
            }
        }
        return position;
    }

    /**
     * Expanding node full branches
     * @param  node The node to expand the branch of it
     * @return the index of this node if it exists in the list
     */
    public int expandNodeBranch(TreeNode node) {
        int position = rootsNodes.indexOf(node);
        if (position != -1 && !node.isExpanded()) {
            node.setExpanded(true);
            int index = position + 1;
            for (TreeNode child : node.getChildren()) {
                int before = rootsNodes.size();
                rootsNodes.add(index, child);
                expandNodeBranch(child);
                int after = rootsNodes.size();
                int diff = after - before;
                index += diff;
            }
        }
        return position;
    }

    /**
     * Expanding one node branch to until specific level
     * @param node to expand branch of it until level
     * @param level to expand node branches to it
     */
    public void expandNodeToLevel(TreeNode node, int level) {
        if (node.getLevel() <= level) expandNode(node);
        for (TreeNode child : node.getChildren()) {
            expandNodeToLevel(child, level);
        }
    }

    /**
     * Expanding all tree nodes branches to until specific level
     * @param level to expand all nodes branches to it
     */
    public void expandNodesAtLevel(int level) {
        for (int i = 0; i < rootsNodes.size() ; i++) {
            TreeNode node = rootsNodes.get(i);
            expandNodeToLevel(node, level);
        }
    }

    /**
     * Collapsing all nodes in the tree with their children
     */
    public void collapseAll() {
        List<TreeNode> treeNodes = new LinkedList<>();
        for (int i = 0; i < rootsNodes.size(); i++) {
            TreeNode root = rootsNodes.get(i);
            if (root.getLevel() == 0) {
                collapseNodeBranch(root);
                treeNodes.add(root);
            } else {
                root.setExpanded(false);
            }
        }
        updateNodes(treeNodes);
    }

    /**
     * Expanding all nodes in the tree with their children
     */
    public void expandAll() {
        for (int i = 0; i < rootsNodes.size(); i++) {
            TreeNode root = rootsNodes.get(i);
            expandNodeBranch(root);
        }
    }
}
