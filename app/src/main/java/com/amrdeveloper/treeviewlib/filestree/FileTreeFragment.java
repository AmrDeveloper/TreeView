package com.amrdeveloper.treeviewlib.filestree;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeViewAdapter;
import com.amrdeveloper.treeview.TreeViewHolderFactory;
import com.amrdeveloper.treeviewlib.R;

import java.util.ArrayList;
import java.util.List;

public class FileTreeFragment extends Fragment {

    private TreeViewAdapter treeViewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file_tree, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.files_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        TreeViewHolderFactory factory = (v, layout) -> new FileViewHolder(v);

        treeViewAdapter = new TreeViewAdapter(factory);
        recyclerView.setAdapter(treeViewAdapter);

        TreeNode rootDirectory = new TreeNode("Root", R.layout.list_item_file);
        rootDirectory.addChild(new TreeNode("Folder1.txt", R.layout.list_item_file));
        rootDirectory.addChild(new TreeNode("Folder2.txt", R.layout.list_item_file));
        rootDirectory.addChild(new TreeNode("Folder3.txt", R.layout.list_item_file));

        TreeNode rootDirectory2 = new TreeNode("KotlinRoot", R.layout.list_item_file);
        rootDirectory2.addChild(new TreeNode("FolderK1.kt", R.layout.list_item_file));
        rootDirectory2.addChild(new TreeNode("Folderk2.kt", R.layout.list_item_file));
        rootDirectory2.addChild(new TreeNode("FolderK3.kt", R.layout.list_item_file));

        TreeNode rootDirectory3 = new TreeNode("JavaRoot", R.layout.list_item_file);
        rootDirectory3.addChild(new TreeNode("FolderJ1.java", R.layout.list_item_file));
        rootDirectory3.addChild(new TreeNode("FolderJ2.java", R.layout.list_item_file));
        rootDirectory3.addChild(new TreeNode("FolderJ3.java", R.layout.list_item_file));

        rootDirectory.addChild(rootDirectory2);

        List<TreeNode> fileRoots = new ArrayList<>();
        fileRoots.add(rootDirectory);
        fileRoots.add(rootDirectory3);

        treeViewAdapter.updateTreeNodes(fileRoots);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int menuId = item.getItemId();
        if (menuId == R.id.expand_all_action) {
            treeViewAdapter.expandAll();
        }
        else if (menuId == R.id.collapse_all_action) {
            treeViewAdapter.collapseAll();
        }
        else if (menuId == R.id.expand_selected_action) {
            treeViewAdapter.expandNode(treeViewAdapter.getSelectedNode());
        }
        else if (menuId == R.id.collapse_selected_action) {
            treeViewAdapter.collapseNode(treeViewAdapter.getSelectedNode());
        }
        return super.onOptionsItemSelected(item);
    }
}

