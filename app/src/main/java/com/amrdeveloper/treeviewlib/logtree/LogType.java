package com.amrdeveloper.treeviewlib.logtree;

import com.amrdeveloper.treeviewlib.R;

public enum LogType {

    LOG_ERROR(R.drawable.ic_error),
    LOG_WARN(R.drawable.ic_warning),
    LOG_TODO(R.drawable.ic_check);

    private final int logIconId;

    LogType(int iconId) {
        this.logIconId = iconId;
    }

    public int getLogIconId() {
        return logIconId;
    }
}
