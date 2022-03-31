# Full example

1 - Setup your XML and make it support 2D Scrolling

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

2 - Create a custom View Holder for your Tree Node

```java
public class CustomViewHolder extends TreeViewHolder {
    
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindTreeNode(TreeNode node) {
        super.bindTreeNode(node);
        // Here you can bind your node and check if it selected or not
    }
}
```

3 - Create View Holder Factory to bind list item layouts with their ViewHolders

```java
TreeViewHolderFactory factory = (v, layout) -> {
    if (layout == R.layout.list_item_one) return new CustomViewHolderOne(v);
    else if (layout == R.layout.list_item_two) return new CustomViewHolderTwo(v);
    else return new CustomViewHolderThree(v);
};
```

If you have only one view holder you can declare it in one line like this

```java
TreeViewHolderFactory factory = (v, layout) -> new CustomViewHolderOne(v);
```

4 - Create a TreeViewAdapter instance and set it to the recyclerview

```java
TreeViewAdapter treeViewAdapter = new TreeViewAdapter(factory);
recyclerView.setAdapter(treeViewAdapter);
```

5 - Build your Tree nodes and add it to the adapter

```java
TreeNode root1 = new TreeNode("Root1", R.layout.list_item_root);
root1.addChild(new TreeNode("Child1", R.layout.list_item_child));
root1.addChild(new TreeNode("Child2", R.layout.list_item_child));

List<TreeNode> roots = new ArrayList<>();
roots.add(root1);

treeViewAdapter.updateTreeNodes(roots);
```