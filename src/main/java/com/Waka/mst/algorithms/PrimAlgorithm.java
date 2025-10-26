package com.Waka.mst.algorithms;

import com.Waka.mst.model.AlgorithmResult;
import com.Waka.mst.model.Edge;
import com.Waka.mst.model.Graph;

import java.util.*;

public class PrimAlgorithm {

    public AlgorithmResult run(Graph graph) {
        AlgorithmResult result = new AlgorithmResult();
        result.startTimer();

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        long operationCount = 0;

        if (graph.getNodes() == null || graph.getNodes().isEmpty()) {
            result.stopTimer();
            result.setMst_edges(mstEdges);
            result.setTotal_cost(totalCost);
            result.setOperations_count(operationCount);
            return result;
        }

        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Map<String, List<Edge>> adj = new HashMap<>();

        for (String node : graph.getNodes()) {
            adj.put(node, new ArrayList<>());
            operationCount++;
        }
        for (Edge edge : graph.getEdges()) {
            adj.get(edge.getFrom()).add(edge);
            adj.get(edge.getTo()).add(new Edge(edge.getTo(), edge.getFrom(), edge.getWeight()));
            operationCount += 2;
        }

        String startNode = graph.getNodes().get(0);
        operationCount = addEdgesToQueue(startNode, visited, adj, pq, operationCount);

        while (!pq.isEmpty() && mstEdges.size() < graph.getVertexCount() - 1) {
            operationCount++;
            Edge minEdge = pq.poll();

            String from = minEdge.getFrom();
            String to = minEdge.getTo();
            String newNode = null;

            if (!visited.contains(to)) {
                newNode = to;
            } else if (!visited.contains(from)) {
                newNode = from;
            }

            operationCount++;
            if (newNode == null) {
                continue;
            }

            mstEdges.add(minEdge);
            totalCost += minEdge.getWeight();

            operationCount = addEdgesToQueue(newNode, visited, adj, pq, operationCount);
        }

        result.stopTimer();
        result.setMst_edges(mstEdges);
        result.setTotal_cost(totalCost);
        result.setOperations_count(operationCount);
        return result;
    }

    private long addEdgesToQueue(String node, Set<String> visited, Map<String, List<Edge>> adj,
                                 PriorityQueue<Edge> pq, long opCount) {
        visited.add(node);
        opCount++;
        for (Edge edge : adj.getOrDefault(node, Collections.emptyList())) {
            opCount++;
            if (!visited.contains(edge.getTo()) || !visited.contains(edge.getFrom())) {
                pq.add(edge);
                opCount++;
            }
        }
        return opCount;
    }
}