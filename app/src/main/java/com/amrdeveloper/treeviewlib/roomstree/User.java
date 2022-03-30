package com.amrdeveloper.treeviewlib.roomstree;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeviewlib.R;

public class User extends TreeNode {

    private final UserState state;
    private final int icon;

    public User(Object value, UserState state, int icon) {
        super(value, R.layout.list_item_user);
        this.state = state;
        this.icon = icon;
    }

    public UserState getState() {
        return state;
    }

    public int getIcon() {
        return icon;
    }
}
