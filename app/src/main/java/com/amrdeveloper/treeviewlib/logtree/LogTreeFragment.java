package com.amrdeveloper.treeviewlib.logtree;

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

public class LogTreeFragment extends Fragment {

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

        TreeViewHolderFactory factory = (v, layout) -> new LogViewHolder(v);

        treeViewAdapter = new TreeViewAdapter(factory);
        recyclerView.setAdapter(treeViewAdapter);

        List<TreeNode> logRoots = new ArrayList<>();

        Log errorsLog = new Log("Errors", LogType.LOG_ERROR);
        errorsLog.addChild(new Log("Error (0:1) : NullPointerException", LogType.LOG_ERROR));
        errorsLog.addChild(new Log("Error (2:1) : Invalid number or arguments", LogType.LOG_ERROR));
        errorsLog.addChild(new Log("Error (3:1) : NullPointerException", LogType.LOG_ERROR));
        logRoots.add(errorsLog);

        Log warnsLog = new Log("Warns", LogType.LOG_WARN);
        warnsLog.addChild(new Log("Error (2:1) : Variable x is not used", LogType.LOG_WARN));
        logRoots.add(warnsLog);

        Log todosLog = new Log("Todos", LogType.LOG_TODO);
        todosLog.addChild(new Log("Error (1:1) : Write unit test for this function", LogType.LOG_TODO));
        logRoots.add(todosLog);

        treeViewAdapter.updateTreeNodes(logRoots);

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
