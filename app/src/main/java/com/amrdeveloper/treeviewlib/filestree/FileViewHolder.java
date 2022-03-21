package com.amrdeveloper.treeviewlib.filestree;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeViewHolder;
import com.amrdeveloper.treeviewlib.R;

public class FileViewHolder extends TreeViewHolder {

    private TextView fileName;
    private ImageView fileStateIcon;
    private ImageView fileTypeIcon;

    public FileViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        fileName = itemView.findViewById(R.id.file_name);
        fileStateIcon = itemView.findViewById(R.id.file_state_icon);
        fileTypeIcon = itemView.findViewById(R.id.file_type_icon);
    }

    @Override
    public void bindTreeNode(TreeNode node) {
        super.bindTreeNode(node);

        String fileNameStr = node.getValue().toString();
        fileName.setText(fileNameStr);

        int dotIndex = fileNameStr.indexOf('.');
        if (dotIndex == -1) {
            fileTypeIcon.setImageResource(R.drawable.ic_folder);
        } else {
            String extension = fileNameStr.substring(dotIndex);
            int extensionIcon = ExtensionTable.getExtensionIcon(extension);
            fileTypeIcon.setImageResource(extensionIcon);
        }

        if (node.getChildren().isEmpty()) {
            fileStateIcon.setVisibility(View.INVISIBLE);
        } else {
            fileStateIcon.setVisibility(View.VISIBLE);
            int stateIcon = node.isExpanded() ? R.drawable.ic_arrow_down : R.drawable.ic_arrow_right;
            fileStateIcon.setImageResource(stateIcon);
        }
    }
}

