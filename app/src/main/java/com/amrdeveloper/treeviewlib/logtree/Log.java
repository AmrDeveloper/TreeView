package com.amrdeveloper.treeviewlib.logtree;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeviewlib.R;

public class Log extends TreeNode {

    private final LogType logType;

    public Log(Object message, LogType logType) {
        super(message, R.layout.list_item_log);
        this.logType = logType;
    }

    public LogType getLogType() {
        return logType;
    }
}
