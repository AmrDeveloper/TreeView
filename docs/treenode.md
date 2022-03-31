# TreeNode

Every item on our Tree is a TreeNode, and you can use the default one or create your own custom tree node if needed.

To create a Default Tree Node

```java
TreeNode node = new TreeNode(value, R.layout.list_item);
```

Once it created it will be at level 0 with no children and no parent.

Maybe in some cases you want to create a custom TreeNode, all you need is to create a class that extend it 
and add your own functions and variables, please check the log example for practical example.

```java
public class CustomTreeNode extends TreeNode {
    
    public CustomTreeNode(Object value, int layoutId) {
        super(message, layoutId);
    }
}
```

TreeNode has many method to set and get values

To add new child

```java
treeNode.addChild(TreeNode child)
```

Change the value

```java
treeNode.setValue(Object value);
```

Get the current value

```java
Object value = treeNode.getValue();
```

Get the node parent

```java
TreeNode parent = treeNode.getParent();
```

To make the node expanded or collapsed

```java
treeNode.setExpanded(isExpanded);
```

Check if it expanded

```java
boolean isExpanded = treeNode.isExpanded();
```

Check if it selected or not

```java
boolean isSelected = treeNode.isSelected();
```

Get the node level

```java
int level = treeNode.getLevel();
```