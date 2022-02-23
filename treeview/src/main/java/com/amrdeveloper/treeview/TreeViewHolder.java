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

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Default RecyclerView.ViewHolder for the TreeView the default behaviour is to manage the padding,
 * user should create custom one for each different layout and override bindTreeNode
 */
public class TreeViewHolder extends RecyclerView.ViewHolder {

    /**
     * The default padding value for the TreeNode item
     */
    private int nodePadding = 50;

    public TreeViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    /**
     * Bind method that provide padding and bind TreeNode to the view list item
     * @param node the current TreeNode
     */
    public void bindTreeNode(TreeNode node) {
        int padding = node.getLevel() * nodePadding;
        itemView.setPadding(
                padding,
                itemView.getPaddingTop(),
                itemView.getPaddingRight(),
                itemView.getPaddingBottom());
    }

    /**
     * Modify the current node padding value
     * @param padding the new padding value
     */
    public void setNodePadding(int padding) {
        this.nodePadding = padding;
    }

    /**
     * Return the current TreeNode padding value
     * @return The current padding value
     */
    public int getNodePadding() {
        return nodePadding;
    }
}
