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

    /**
     * Manager class for TreeNodes to easily apply operations on them
     * and to make it easy for testing and extending
     */
    private final TreeNodeManager treeNodeManager;

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
        this.treeNodeManager = new TreeNodeManager();
    }

    /**
     * Constructor used to accept user custom TreeNodeManager class
     * @param factory a View Holder Factory mapped with layout id's
     * @param manager a custom tree node manager class
     */
    public TreeViewAdapter(TreeViewHolderFactory factory, TreeNodeManager manager) {
        this.treeViewHolderFactory = factory;
        this.treeNodeManager = manager;
    }

    @NonNull
    @Override
    public TreeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int layoutId) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return treeViewHolderFactory.getTreeViewHolder(view, layoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull TreeViewHolder holder, int position) {
        TreeNode currentNode = treeNodeManager.get(position);
        holder.bindTreeNode(currentNode);

        holder.itemView.setOnClickListener(v -> {
            // Handle node selection
            currentNode.setSelected(true);
            if (currentSelectedNode != null) currentSelectedNode.setSelected(false);
            currentSelectedNode = currentNode;

            // Handle node expand and collapse event
            if (!currentNode.getChildren().isEmpty()) {
                boolean isNodeExpanded = currentNode.isExpanded();
                if (isNodeExpanded) collapseNode(currentNode);
                else expandNode(currentNode);
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
        return treeNodeManager.get(position).getLayoutId();
    }

    @Override
    public int getItemCount() {
        return treeNodeManager.size();
    }

    /**
     * Collapsing node and all of his children
     * @param node The node to collapse it
     */
    public void collapseNode(TreeNode node) {
        int position = treeNodeManager.collapseNode(node);
        if (position != -1) {
            notifyDataSetChanged();
        }
    }

    /**
     * Expanding node and all of his children
     * @param node The node to expand it
     */
    public void expandNode(TreeNode node) {
        int position = treeNodeManager.expandNode(node);
        if (position != -1) {
            notifyDataSetChanged();
        }
    }

    /**
     * Collapsing full node branches
     * @param node The node to collapse it
     */
    public void collapseNodeBranch(TreeNode node) {
        treeNodeManager.collapseNodeBranch(node);
        notifyDataSetChanged();
    }

    /**
     * Expanding node full branches
     * @param node The node to expand it
     */
    public void expandNodeBranch(TreeNode node) {
        treeNodeManager.expandNodeBranch(node);
        notifyDataSetChanged();
    }

    /**
     * Expanding one node branch to until specific level
     * @param node to expand branch of it until level
     * @param level to expand node branches to it
     */
    public void expandNodeToLevel(TreeNode node, int level) {
        treeNodeManager.expandNodeToLevel(node, level);
        notifyDataSetChanged();
    }

    /**
     * Expanding all tree nodes branches to until specific level
     * @param level to expand all nodes branches to it
     */
    public void expandNodesAtLevel(int level) {
        treeNodeManager.expandNodesAtLevel(level);
        notifyDataSetChanged();
    }

    /**
     * Collapsing all nodes in the tree with their children
     */
    public void collapseAll() {
        treeNodeManager.collapseAll();
        notifyDataSetChanged();
    }

    /**
     * Expanding all nodes in the tree with their children
     */
    public void expandAll() {
        treeNodeManager.expandAll();
        notifyDataSetChanged();
    }

    /**
     * Update the list of tree nodes
     * @param treeNodes The new tree nodes
     */
    public void updateTreeNodes(List<TreeNode> treeNodes) {
        treeNodeManager.updateNodes(treeNodes);
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
}
