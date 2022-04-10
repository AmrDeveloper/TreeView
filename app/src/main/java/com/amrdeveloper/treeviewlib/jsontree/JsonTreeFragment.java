package com.amrdeveloper.treeviewlib.jsontree;

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

import com.amrdeveloper.treeview.TreeViewAdapter;
import com.amrdeveloper.treeview.TreeViewHolderFactory;
import com.amrdeveloper.treeviewlib.R;
import com.amrdeveloper.treeviewlib.filestree.FileViewHolder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonTreeFragment extends Fragment {

    private TreeViewAdapter treeViewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logs_tree, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.logs_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setNestedScrollingEnabled(false);

        TreeViewHolderFactory factory = (v, layout) -> new FileViewHolder(v);

        treeViewAdapter = new TreeViewAdapter(factory);
        recyclerView.setAdapter(treeViewAdapter);

        final String jsonData = "[\n" +
                "  {\n" +
                "     \"id\": 1,\n" +
                "     \"value\": \"JavaFiles\",\n" +
                "     \"layout\": \"list_item_file\",\n" +
                "     \"parentId\": -1\n" +
                "  },\n" +
                "  {\n" +
                "     \"id\": 2,\n" +
                "     \"value\": \"CppFiles\",\n" +
                "     \"layout\": \"list_item_file\",\n" +
                "     \"parentId\": -1\n" +
                "  },\n" +
                "  {\n" +
                "     \"id\": 3,\n" +
                "     \"value\": \"Src\",\n" +
                "     \"layout\": \"list_item_file\",\n" +
                "     \"parentId\": 1\n" +
                "  },\n" +
                "  {\n" +
                "     \"id\": 4,\n" +
                "     \"value\": \"File2.cpp\",\n" +
                "     \"layout\": \"list_item_file\",\n" +
                "     \"parentId\": 2\n" +
                "  },\n" +
                "  {\n" +
                "     \"id\": 5,\n" +
                "     \"value\": \"File1.java\",\n" +
                "     \"layout\": \"list_item_file\",\n" +
                "     \"parentId\": 3\n" +
                "  }\n" +
                "]";

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(TreeNodes.class, new TreeNodeParser())
                .create();

        TreeNodes treeNodes = gson.fromJson(jsonData, TreeNodes.class);

        treeViewAdapter.updateTreeNodes(treeNodes.getTreeNodes());

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
        else if (menuId == R.id.expand_selected_branch_action) {
            treeViewAdapter.expandNodeBranch(treeViewAdapter.getSelectedNode());
        }
        else if (menuId == R.id.collapse_selected_branch_action) {
            treeViewAdapter.collapseNodeBranch(treeViewAdapter.getSelectedNode());
        }
        else if (menuId == R.id.expand_selected_level_action) {
            treeViewAdapter.expandNodesAtLevel(2);
        }
        return super.onOptionsItemSelected(item);
    }
}
