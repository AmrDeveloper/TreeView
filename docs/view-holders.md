# View Holders

To create a View Holder for your List item you should create a class that extend our TreeViewHolder

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

And you need to create a factory that provide view holder depend on the list item layout

Simple Factory with 1 View holder

```java
TreeViewHolderFactory factory = (v, layout) -> new CustomViewHolderOne(v);
```

Factory with 3 View Holders
```java
TreeViewHolderFactory factory = (v, layout) -> {
    if (layout == R.layout.list_item_one) return new CustomViewHolderOne(v);
    else if (layout == R.layout.list_item_two) return new CustomViewHolderTwo(v);
    else return new CustomViewHolderThree(v);
};
```

This factory instance will passed to the TreeViewAdapter when it created

```java
TreeViewAdapter treeViewAdapter = new TreeViewAdapter(factory);
```