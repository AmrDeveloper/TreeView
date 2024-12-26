package com.amrdeveloper.treeviewlib.todotree;

import com.amrdeveloper.treeview.TreeNode;

public class TodoNode extends TreeNode  {

    private boolean isChecked;

    public TodoNode(Object value, int layoutId) {
        super(value, layoutId);
        this.isChecked = false;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
