# TreeView Adapter

TreeView come with it own Adapter to easily provide tree view features on any RecyclerView.

TreeViewAdapter class can be created with 2 options, first with ViewHoldersFactory and it will use the default TreeNodeManager

```java
TreeViewAdapter adapter = new TreeViewAdapter(factory);
```

The other option is used if you have a custom TreeNodeManager.

```java
TreeViewAdapter adapter = new TreeViewAdapter(factory, customTreeNodeManager);
```

Update the list of tree nodes

```java
public void updateTreeNodes(List<TreeNode> treeNodes)
```

Delete all tree nodes

```java
public void clearTreeNodes()
```

Collapsing node and all of his children

```java
public void collapseNode(TreeNode node)
```

Expanding node and all of his children

```java
public void expandNode(TreeNode node)
```

Collapsing full node branches

```java
public void collapseNodeBranch(TreeNode node)
```

Expanding node full branches

```java
public void expandNodeBranch(TreeNode node)
```

Expanding one node branch to until specific level

```java
public void expandNodeToLevel(TreeNode node, int level)
```

Expanding all tree nodes branches to until specific level

```java
public void expandNodesAtLevel(int level)
```

Collapsing all nodes in the tree with their children

```java
public void collapseAll()
```

Expanding all nodes in the tree with their children

```java
public void expandAll()
```

Set the current visible tree nodes and notify all data changed

```java
public void setTreeNodes(List<TreeNode> treeNodes)
```

Get the Current visible Tree nodes

```java
public List<TreeNode> getTreeNodes()
```

Get the current selected tree node, null of no tree node selected

```java
public TreeNode getSelectedNode()
```

Register a callback to be invoked when this TreeNode is clicked

```java
public void setTreeNodeClickListener(OnTreeNodeClickListener listener)
```

or using Lambda

```java
treeViewAdapter.setTreeNodeClickListener((treeNode, nodeView) -> {
    
});
```

Register a callback to be invoked when this TreeNode is clicked and held

```java
public void setTreeNodeLongClickListener(OnTreeNodeLongClickListener listener)
```

or using Lambda

```java
treeViewAdapter.setTreeNodeLongClickListener((treeNode, nodeView) -> {
    return true;
});
```