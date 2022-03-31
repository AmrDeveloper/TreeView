# Add to XML

TreeView didn't require any custom views or custom setup all you need is just a Recyclerview

```xml
<androidx.recyclerview.widget.RecyclerView 
    android:id="@+id/recycler_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

If you want to support 2D Scrolling you should add your RecyclerView in HorizontalScrollView

```xml
<HorizontalScrollView
    android:fillViewport="true"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scrollbars="vertical" />
</HorizontalScrollView>
```

You can Change the scroll bar color and size like any other RecyclerView