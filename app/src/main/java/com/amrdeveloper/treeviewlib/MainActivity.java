package com.amrdeveloper.treeviewlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;

import com.amrdeveloper.treeviewlib.filestree.FileTreeFragment;
import com.amrdeveloper.treeviewlib.jsontree.JsonTreeFragment;
import com.amrdeveloper.treeviewlib.logtree.LogTreeFragment;
import com.amrdeveloper.treeviewlib.roomstree.RoomTreeFragment;
import com.amrdeveloper.treeviewlib.todotree.TodoTreeFragment;

public class MainActivity extends AppCompatActivity {

    private final static int NUMBER_OF_FRAGMENTS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private static class ViewPagerAdapter extends FragmentStateAdapter {

        public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return switch (position) {
                case 0 -> new FileTreeFragment();
                case 1 -> new LogTreeFragment();
                case 2 -> new TodoTreeFragment();
                case 3 -> new RoomTreeFragment();
                default -> new JsonTreeFragment();
            };
        }

        @Override
        public int getItemCount() {
            return NUMBER_OF_FRAGMENTS;
        }
    }
}