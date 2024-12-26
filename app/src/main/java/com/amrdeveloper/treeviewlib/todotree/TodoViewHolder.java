package com.amrdeveloper.treeviewlib.todotree;

import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeViewHolder;
import com.amrdeveloper.treeviewlib.R;

public class TodoViewHolder extends TreeViewHolder  {

    private final TextView todoTitle;
    private final ImageView todoStateIcon;
    private final CheckBox todoCheckBox;

    public TodoViewHolder(@NonNull View itemView) {
        super(itemView);

        this.todoTitle = itemView.findViewById(R.id.todo_title);
        this.todoStateIcon = itemView.findViewById(R.id.todo_state_icon);
        this.todoCheckBox = itemView.findViewById(R.id.todo_check_box);
    }

    @Override
    public void bindTreeNode(TreeNode node) {
        super.bindTreeNode(node);

        TodoNode todoNode = (TodoNode) node;

        String todoTitleStr = node.getValue().toString();
        todoTitle.setText(todoTitleStr);

        if (node.isSelected()) {
            itemView.setBackgroundColor(Color.DKGRAY);
            todoTitle.setTextColor(Color.WHITE);
        } else {
            itemView.setBackgroundColor(Color.WHITE);
            todoTitle.setTextColor(Color.DKGRAY);
        }

        if (node.getChildren().isEmpty()) {
            todoStateIcon.setVisibility(View.INVISIBLE);
        } else {
            todoStateIcon.setVisibility(View.VISIBLE);
            int stateIcon = node.isExpanded() ? R.drawable.ic_arrow_down : R.drawable.ic_arrow_right;
            todoStateIcon.setImageResource(stateIcon);
        }

        // Here you can implement features like select all Children if parent is selected
        todoCheckBox.setChecked(todoNode.isChecked());
        todoCheckBox.setOnClickListener(view -> {
            todoNode.setChecked(todoCheckBox.isChecked());
        });
    }
}
