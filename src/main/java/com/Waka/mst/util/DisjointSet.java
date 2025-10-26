package com.Waka.mst.util;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class DisjointSet {
    private Map<String, String> parent = new HashMap<>();
    private Map<String, Integer> rank = new HashMap<>();
    private long operationCount = 0;

    public DisjointSet(List<String> nodes) {
        for (String node : nodes) {
            parent.put(node, node);
            rank.put(node, 0);
            operationCount += 2;
        }
    }

    public String find(String node) {
        operationCount++;
        if (node.equals(parent.get(node))) {
            return node;
        }
        String root = find(parent.get(node));
        parent.put(node, root);
        operationCount++;
        return root;
    }

    public boolean union(String node1, String node2) {
        String root1 = find(node1);
        String root2 = find(node2);

        operationCount++;
        if (root1.equals(root2)) {
            return false;
        }

        operationCount += 2;
        if (rank.get(root1) < rank.get(root2)) {
            parent.put(root1, root2);
        } else if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1);
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank.get(root1) + 1);
            operationCount++;
        }
        operationCount++;
        return true;
    }

    public long getOperationCount() {
        return operationCount;
    }
}