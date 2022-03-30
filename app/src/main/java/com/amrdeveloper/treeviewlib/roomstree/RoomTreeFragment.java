package com.amrdeveloper.treeviewlib.roomstree;

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

public class RoomTreeFragment extends Fragment {

    private TreeViewAdapter treeViewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_tree, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rooms_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setNestedScrollingEnabled(false);

        TreeViewHolderFactory factory = (v, layout) -> {
            if (layout == R.layout.list_item_room) return new RoomViewHolder(v);
            else return new UserViewHolder(v);
        };

        treeViewAdapter = new TreeViewAdapter(factory);
        recyclerView.setAdapter(treeViewAdapter);

        TreeNode gameRoom = new TreeNode("Games", R.layout.list_item_room);
        gameRoom.addChild(new User("Gamer1", UserState.ON, R.drawable.ic_avatar_1));
        gameRoom.addChild(new User("Gamer2", UserState.ON, R.drawable.ic_avatar_2));
        gameRoom.addChild(new User("Gamer3", UserState.OFF, R.drawable.ic_avatar_3));

        TreeNode geeksRoom = new TreeNode("Geeks", R.layout.list_item_room);
        geeksRoom.addChild(new User("Geek1", UserState.ON, R.drawable.ic_avatar_3));
        geeksRoom.addChild(new User("Geek2", UserState.ON, R.drawable.ic_avatar_2));
        geeksRoom.addChild(new User("Geek3", UserState.OFF, R.drawable.ic_avatar_1));

        TreeNode booksRoom = new TreeNode("Books", R.layout.list_item_room);
        booksRoom.addChild(new User("Reader1", UserState.ON, R.drawable.ic_avatar_4));
        booksRoom.addChild(new User("Reader2", UserState.OFF, R.drawable.ic_avatar_2));
        booksRoom.addChild(new User("Reader3", UserState.OFF, R.drawable.ic_avatar_1));

        List<TreeNode> chatRooms = new ArrayList<>();
        chatRooms.add(geeksRoom);
        chatRooms.add(booksRoom);
        chatRooms.add(gameRoom);

        treeViewAdapter.updateTreeNodes(chatRooms);

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
