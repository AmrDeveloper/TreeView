package com.amrdeveloper.treeviewlib.logtree;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeViewHolder;
import com.amrdeveloper.treeviewlib.R;

public class LogViewHolder extends TreeViewHolder {

    private final TextView logName;
    private final ImageView logStateIcon;
    private final ImageView logTypeIcon;

    public LogViewHolder(@NonNull View itemView) {
        super(itemView);

        this.logName = itemView.findViewById(R.id.log_name);
        this.logStateIcon = itemView.findViewById(R.id.log_state_icon);
        this.logTypeIcon = itemView.findViewById(R.id.log_type_icon);
    }

    @Override
    public void bindTreeNode(TreeNode node) {
        super.bindTreeNode(node);

        Log currentLog = (Log) node;

        logName.setText(currentLog.getValue().toString());

        logTypeIcon.setImageResource(currentLog.getLogType().getLogIconId());

        if (node.getChildren().isEmpty()) {
            logStateIcon.setVisibility(View.INVISIBLE);
        } else {
            logStateIcon.setVisibility(View.VISIBLE);
            int stateIcon = node.isExpanded() ? R.drawable.ic_arrow_down : R.drawable.ic_arrow_right;
            logStateIcon.setImageResource(stateIcon);
        }
    }
}
