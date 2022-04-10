package com.amrdeveloper.treeviewlib.jsontree;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeviewlib.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
