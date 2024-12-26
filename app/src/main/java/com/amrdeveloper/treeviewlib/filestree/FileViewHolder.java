package com.amrdeveloper.treeviewlib.filestree;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeViewHolder;
import com.amrdeveloper.treeviewlib.R;

public class FileViewHolder extends TreeViewHolder {

    private final TextView fileName;
    private final ImageView fileStateIcon;
    private final ImageView fileTypeIcon;

    public FileViewHolder(@NonNull View itemView) {
        super(itemView);

        this.fileName = itemView.findViewById(R.id.file_name);
        this.fileStateIcon = itemView.findViewById(R.id.file_state_icon);
        this.fileTypeIcon = itemView.findViewById(R.id.file_type_icon);
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

        if (node.isSelected()) {
            itemView.setBackgroundColor(Color.DKGRAY);
            fileName.setTextColor(Color.WHITE);
        } else {
            itemView.setBackgroundColor(Color.WHITE);
            fileName.setTextColor(Color.DKGRAY);
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

