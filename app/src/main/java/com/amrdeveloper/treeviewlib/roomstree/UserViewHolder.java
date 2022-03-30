package com.amrdeveloper.treeviewlib.roomstree;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeViewHolder;
import com.amrdeveloper.treeviewlib.R;

public class UserViewHolder extends TreeViewHolder {

    private TextView userName;
    private ImageView userStateIcon;
    private ImageView userAvatarIcon;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        userName = itemView.findViewById(R.id.user_name);
        userStateIcon = itemView.findViewById(R.id.user_state_icon);
        userAvatarIcon = itemView.findViewById(R.id.user_avatar_icon);
    }

    @Override
    public void bindTreeNode(TreeNode node) {
        super.bindTreeNode(node);

        User currentUser = (User) node;
        userName.setText(currentUser.getValue().toString());

        userStateIcon.setImageResource(currentUser.getState().getStateIcon());

        userAvatarIcon.setImageResource(currentUser.getIcon());
    }
}
