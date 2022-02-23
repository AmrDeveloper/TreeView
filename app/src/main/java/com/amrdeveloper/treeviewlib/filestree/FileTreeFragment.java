package com.amrdeveloper.treeviewlib.filestree;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeViewAdapter;
import com.amrdeveloper.treeview.ViewHolderBuilder;
import com.amrdeveloper.treeviewlib.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileTreeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file_tree, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.files_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        Map<Integer, ViewHolderBuilder> viewHolderBuilders = new HashMap<>();
        viewHolderBuilders.put(R.layout.list_item_file, FileViewHolder::new);

        TreeViewAdapter treeViewAdapter = new TreeViewAdapter(viewHolderBuilders);
        recyclerView.setAdapter(treeViewAdapter);

        TreeNode rootDirectory = new TreeNode("Root", R.layout.list_item_file);
        rootDirectory.addChild(new TreeNode("Folder1.txt", R.layout.list_item_file));
        rootDirectory.addChild(new TreeNode("Folder2.txt", R.layout.list_item_file));
        rootDirectory.addChild(new TreeNode("Folder3.txt", R.layout.list_item_file));

        TreeNode rootDirectory2 = new TreeNode("Root2", R.layout.list_item_file);
        rootDirectory2.addChild(new TreeNode("Folder21.txt", R.layout.list_item_file));
        rootDirectory2.addChild(new TreeNode("Folder22.txt", R.layout.list_item_file));
        rootDirectory2.addChild(new TreeNode("Folder23.txt", R.layout.list_item_file));

        rootDirectory.addChild(rootDirectory2);

        List<TreeNode> fileRoots = new ArrayList<>();
        fileRoots.add(rootDirectory);

        treeViewAdapter.updateTreeNodes(fileRoots);

        return view;
    }
}

