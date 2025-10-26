package com.Waka.mst.algorithms;

import com.Waka.mst.model.AlgorithmResult;
import com.Waka.mst.model.Edge;
import com.Waka.mst.model.Graph;
import com.Waka.mst.util.DisjointSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {

    public AlgorithmResult run(Graph graph) {
        AlgorithmResult result = new AlgorithmResult();
        result.startTimer();

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        long operationCount = 0;

        List<Edge> allEdges = new ArrayList<>(graph.getEdges());

        if (!allEdges.isEmpty()) {
            operationCount += (long) (allEdges.size() * (Math.log(allEdges.size()) / Math.log(2)));
        }
        Collections.sort(allEdges);

        DisjointSet dsu = new DisjointSet(graph.getNodes());

        int vertexCount = graph.getVertexCount();
        int edgesAdded = 0;

        for (Edge edge : allEdges) {
            operationCount++;

            if (dsu.union(edge.getFrom(), edge.getTo())) {
                mstEdges.add(edge);
                totalCost += edge.getWeight();
                edgesAdded++;
            }

            if (edgesAdded == vertexCount - 1) {
                break;
            }
        }

        result.stopTimer();
        result.setMst_edges(mstEdges);
        result.setTotal_cost(totalCost);
        result.setOperations_count(operationCount + dsu.getOperationCount());

        return result;
    }
}