# Build TreeView from Json Data

In Some cases you want to receive the tree nodes from API or from local files and you need to build a nested TreeView,
you can easily do that with GSON library.

For example if you want to build a nested nodes for example node with value First will have one child,
which is Second and Second will have Third, and your response look like this, you can define different schema.

```json
[
  {
     "id": 1,
     "value": "First",
     "layout": "list_item_node",
     "parentId": -1
  },
  {
     "id": 2,
     "value": "Second",
     "layout": "list_item_node",
     "parentId": 1
  },
  {
     "id": 3,
     "value": "Third",
     "layout": "list_item_node",
     "parentId": 2
  }
]
```

First we will create a class type called TreeNodes which will save a list of tree nodes.

```java
public class TreeNodes {
    private List<TreeNode> treeNodes;

    public TreeNodes(List<TreeNode> nodes) {
        this.treeNodes = nodes;
    }

    public List<TreeNode> getTreeNodes() {
        return treeNodes;
    }
}
```

then we need to create a custom JsonDeserializer that can parse our response

```java
public class TreeNodeParser implements JsonDeserializer<TreeNodes> {

    @Override
    public TreeNodes deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<TreeNode> roots = new ArrayList<>();
        List<TreeNode> allNodes = new ArrayList<>();
        Map<Integer, Integer> parentIndexMap = new HashMap<>();
        JsonArray jsonArray = json.getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject treeNodeObject = (JsonObject) jsonArray.get(i);
            Object value = treeNodeObject.get("value").getAsString();
            int nodeId = treeNodeObject.get("id").getAsInt();
            int parentId = treeNodeObject.get("parentId").getAsInt();

            // You can easily support dynamic layout id by et layout value
            TreeNode treeNode = new TreeNode(value, R.layout.list_item_file);

            Integer index = parentIndexMap.get(parentId);
            if (index != null) {
                allNodes.get(index).addChild(treeNode);
            } else {
                roots.add(treeNode);
            }
            allNodes.add(treeNode);
            parentIndexMap.put(nodeId, allNodes.size() - 1);
        }
        return new TreeNodes(roots);
    }
}
```

Then you can use Gson Library here to get TreeNodes instance and pass it to the TreeViewAdapter

```java
Gson gson = new GsonBuilder()
        .registerTypeAdapter(TreeNodes.class, new TreeNodeParser())
        .create();

TreeNodes treeNodes = gson.fromJson(jsonData, TreeNodes.class);
treeViewAdapter.updateTreeNodes(treeNodes.getTreeNodes());
```

This is a simple example you can customize it and support many features.