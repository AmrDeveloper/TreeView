package com.amrdeveloper.treeviewlib.roomstree;

import com.amrdeveloper.treeviewlib.R;

public enum UserState {

    ON(R.drawable.ic_green_circle),
    OFF(R.drawable.ic_red_circle);

    private final int stateIcon;

    UserState(int icon) {
        this.stateIcon = icon;
    }

    public int getStateIcon() {
        return stateIcon;
    }
}
