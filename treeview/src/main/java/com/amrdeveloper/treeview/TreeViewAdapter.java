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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

/**
 * Custom RecyclerView.Adapter used to provide a tree view features on any RecyclerView
 */
public class TreeViewAdapter extends RecyclerView.Adapter<TreeViewHolder> {

    /**
     * Interface definition for a callback to be invoked when a TreeNode has been clicked and held.
     */
    public interface OnTreeNodeClickListener {
        /**
         * Called when a TreeNode has been clicked.
         * @param treeNode The current clicked node
         * @param view The view that was clicked and held.
         */
        void onTreeNodeClick(TreeNode treeNode, View view);
    }

    /**
     * Interface definition for a callback to be invoked when a TreeNode has been clicked and held.
     */
    public interface OnTreeNodeLongClickListener {
        /**
         * Called when a TreeNode has been clicked and held.
         * @param treeNode The current clicked node
         * @param view The view that was clicked and held.
         * @return true if the callback consumed the long click, false otherwise.
         */
        boolean onTreeNodeLongClick(TreeNode treeNode, View view);
    }

    // private final LinkedList<TreeNode> roots = new LinkedList<>();
    /**
     * LinkedList of all the roots (Level 0) Nodes
     */
    private final LinkedList<TreeNode> rootsNodes = new LinkedList<>();

    /**
     * A ViewHolder Factory to get TreeViewHolder object that mapped with layout
     */
    private final TreeViewHolderFactory treeViewHolderFactory;

    /**
     * The current selected Tree Node
     */
    private TreeNode currentSelectedNode;

    /**
     * Custom OnClickListener to be invoked when a TreeNode has been clicked.
     */
    private OnTreeNodeClickListener treeNodeClickListener;

    /**
     * Custom OnLongClickListener to be invoked when a TreeNode has been clicked and hold.
     */
    private OnTreeNodeLongClickListener treeNodeLongClickListener;

    /**
     * Simple constructor
     * @param factory a View Holder Factory mapped with layout id's
     */
    public TreeViewAdapter(TreeViewHolderFactory factory) {
        this.treeViewHolderFactory = factory;
    }

    @NonNull
    @Override
    public TreeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int layoutId) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return treeViewHolderFactory.getTreeViewHolder(view, layoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull TreeViewHolder holder, int position) {
        TreeNode currentNode = rootsNodes.get(position);
        holder.bindTreeNode(currentNode);

        holder.itemView.setOnClickListener(v -> {
            // Handle node selection
            currentNode.setSelected(true);
            if (currentSelectedNode != null) currentSelectedNode.setSelected(false);
            currentSelectedNode = currentNode;

            // Handle node expand and collapse event
            if (!currentNode.getChildren().isEmpty()) {
                boolean isNodeExpanded = currentNode.isExpanded();
                if (isNodeExpanded) removeNodeBranch(currentNode);
                else insertNodeBranch(currentNode, position + 1);
                currentNode.setExpanded(!isNodeExpanded);
            }

            notifyDataSetChanged();

            // Handle TreeNode click listener event
            if (treeNodeClickListener != null)
                treeNodeClickListener.onTreeNodeClick(currentNode, v);
        });

        // Handle TreeNode long click listener event
        holder.itemView.setOnLongClickListener(v -> {
            if (treeNodeLongClickListener != null) {
                return treeNodeLongClickListener.onTreeNodeLongClick(currentNode, v);
            }
            return true;
        });
    }

    @Override
    public int getItemViewType(int position) {
        return rootsNodes.get(position).getLayoutId();
    }

    @Override
    public int getItemCount() {
        return rootsNodes.size();
    }

    /**
     * Collapsing node and all of his children
     * @param node The node to collapse it
     */
    public void collapseNode(TreeNode node) {
        int position = rootsNodes.indexOf(node);
        if (position != -1 && node.isExpanded() && node.getChildren().size() > 0) {
            node.setExpanded(false);
            for (TreeNode child : node.getChildren()) {
                rootsNodes.remove(child);
            }
            notifyItemChanged(position);
            notifyItemRangeRemoved(position + 1, node.getChildren().size());
        }
    }

    /**
     * Expanding node and all of his children
     * @param node The node to expand it
     */
    public void expandNode(TreeNode node) {
        int position = rootsNodes.indexOf(node);
        if (position != -1 && !node.isExpanded() && node.getChildren().size() > 0) {
            node.setExpanded(true);
            int oldSize = rootsNodes.size();
            int startPosition = position + 1;
            for (TreeNode child : node.getChildren()) {
                rootsNodes.add(startPosition++, child);
            }
            int diffSize = rootsNodes.size() - oldSize;
            notifyItemChanged(position);
            notifyItemRangeInserted(position + 1, diffSize);
        }
    }

    /**
     * Collapsing all nodes in the tree with their children
     */
    public void collapseAll() {
        for (int i = 0; i < rootsNodes.size(); i++) {
            TreeNode node = rootsNodes.get(i);
            if (node.getLevel() == 0) {
                removeNodeBranch(node);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * Expanding all nodes in the tree with their children
     */
    public void expandAll() {
        for (int i = 0; i < rootsNodes.size(); i++) {
            TreeNode node = rootsNodes.get(i);
            node.setExpanded(true);
            insertNodeBranch(node, i + 1);
        }
        notifyDataSetChanged();
    }

    /**
     * Update the list of tree nodes
     * @param treeNodes The new tree nodes
     */
    public void updateTreeNodes(List<TreeNode> treeNodes) {
        rootsNodes.clear();
        rootsNodes.addAll(treeNodes);

        notifyItemRangeInserted(0, treeNodes.size());
    }

    /**
     * Register a callback to be invoked when this TreeNode is clicked
     * @param listener The callback that will run
     */
    public void setTreeNodeClickListener(OnTreeNodeClickListener listener) {
        this.treeNodeClickListener = listener;
    }

    /**
     * Register a callback to be invoked when this TreeNode is clicked and held
     * @param listener The callback that will run
     */
    public void setTreeNodeLongClickListener(OnTreeNodeLongClickListener listener) {
        this.treeNodeLongClickListener = listener;
    }

    /**
     * @return The current selected TreeNode
     */
    public TreeNode getSelectedNode() {
        return currentSelectedNode;
    }

    private void insertNodeBranch(TreeNode node, int startPosition) {
        node.setExpanded(true);
        for (TreeNode child : node.getChildren()) {
            rootsNodes.add(startPosition++, child);
            if (child.isExpanded())
                insertNodeBranch(child, startPosition);
        }
    }

    private void removeNodeBranch(TreeNode node) {
        node.setExpanded(false);
        for (TreeNode child : node.getChildren()) {
            rootsNodes.remove(child);
            if (!child.getChildren().isEmpty()) removeNodeBranch(child);
        }
    }
}
