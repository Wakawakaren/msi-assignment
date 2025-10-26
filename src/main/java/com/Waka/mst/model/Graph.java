package com.Waka.mst.model;

import java.util.List;

public class Graph {
    private int id;
    private List<String> nodes;
    private List<Edge> edges;

    public static class GraphInput {
        public List<Graph> graphs;
    }

    public int getId() { return id; }
    public List<String> getNodes() { return nodes; }
    public List<Edge> getEdges() { return edges; }

    public int getVertexCount() { return nodes.size(); }
    public int getEdgeCount() { return edges.size(); }
}