# TreeNode Manager

Almost all of the TreeNode manipulation features are implemented on TreeNodeManager, this help us
to test, extend and change the implementation for some features if it needed.

By Default TreeViewAdapter depend on the Default TreeNodeManager class but if you want a custom implementation,
you can easily create a new manager class that extend TreeNodeManager and pass it on TreeViewAdapter constructor

```java
TreeViewAdapter adapter = new TreeViewAdapter(factory, customTreeNodeManager);
```

The TreeNodeManager class has many method to help easily provide features.

Get TreeNode by index

```java
public TreeNode get(int index)
```

Add new TreeNode

```java
public boolean addNode(TreeNode node)
```

Clear the current TreeNodes and add new TreeNodes

```java
public void updateNodes(List<TreeNode> newNodes)
```

Remove TreeNode

```java
public boolean removeNode(TreeNode node)
```

Clear all TreeNodes

```java
public void clearNodes()
```

Get the current TreeNodes size

```java
public int size()
```

Collapsing TreeNode

```java
public int collapseNode(TreeNode node)
```

Expanding TreeNode

```java
public int expandNode(TreeNode node)
```

Collapsing TreeNode full branch

```java
public int collapseNodeBranch(TreeNode node)
```

Expanding TreeNode full branch

```java
public int expandNodeBranch(TreeNode node)
```

Expanding TreeNode branch until specific level

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

If you want more information about them, please check the javadoc.