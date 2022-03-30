package com.amrdeveloper.treeviewlib.roomstree;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeViewHolder;
import com.amrdeveloper.treeviewlib.R;

public class RoomViewHolder extends TreeViewHolder {

    private TextView roomName;
    private ImageView roomStateIcon;

    public RoomViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        roomName = itemView.findViewById(R.id.room_name);
        roomStateIcon = itemView.findViewById(R.id.room_state_icon);
    }

    @Override
    public void bindTreeNode(TreeNode node) {
        super.bindTreeNode(node);

        String roomNameStr = node.getValue().toString();
        roomName.setText(roomNameStr);

        if (node.getChildren().isEmpty()) {
            roomStateIcon.setVisibility(View.INVISIBLE);
        } else {
            roomStateIcon.setVisibility(View.VISIBLE);
            int stateIcon = node.isExpanded() ? R.drawable.ic_arrow_down : R.drawable.ic_arrow_right;
            roomStateIcon.setImageResource(stateIcon);
        }
    }
}
