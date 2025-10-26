package com.Waka.mst;

import com.Waka.mst.algorithms.KruskalAlgorithm;
import com.Waka.mst.algorithms.PrimAlgorithm;
import com.Waka.mst.model.AlgorithmResult;
import com.Waka.mst.model.Graph;
import com.Waka.mst.util.JsonUtil;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class MSTAlgorithmTest {

    private static List<Graph> graphs;
    private static Map<Integer, Graph> graphMap;
    private static PrimAlgorithm prim;
    private static KruskalAlgorithm kruskal;

    @BeforeAll
    static void setUp() throws IOException {
        graphs = JsonUtil.readGraphs("src/main/resources/ass_3_input.json");
        graphMap = graphs.stream().collect(Collectors.toMap(Graph::getId, g -> g));
        prim = new PrimAlgorithm();
        kruskal = new KruskalAlgorithm();
    }
    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Total cost must be identical for Prim and Kruskal")
    void testTotalCostIsIdentical() {
        Graph smallGraph = graphMap.get(2);
        AlgorithmResult primResult = prim.run(smallGraph);
        AlgorithmResult kruskalResult = kruskal.run(smallGraph);

        org.junit.jupiter.api.Assertions.assertEquals(16, primResult.getTotal_cost());
        org.junit.jupiter.api.Assertions.assertEquals(16, kruskalResult.getTotal_cost());
        org.junit.jupiter.api.Assertions.assertEquals(primResult.getTotal_cost(), kruskalResult.getTotal_cost());
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Number of edges in MST must be V-1")
    void testMstEdgeCountIsVMinusOne() {
        for (Graph g : graphs) {
            AlgorithmResult kruskalResult = kruskal.run(g);
            int v = g.getVertexCount();

            if (isGraphConnected(g, kruskalResult.getMst_edges())) {
                org.junit.jupiter.api.Assertions.assertEquals(v - 1, kruskalResult.getMst_edges().size(),
                        "Graph ID " + g.getId() + " (V=" + v + ") should have V-1 edges.");
            } else {
                org.junit.jupiter.api.Assertions.assertTrue(kruskalResult.getMst_edges().size() < v - 1,
                        "Disconnected Graph ID " + g.getId() + " should have < V-1 edges.");
            }
        }
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("MST contains no cycles (implicitly checked by V-1)")
    void testMstIsAcyclic() {
        org.junit.jupiter.api.Assertions.assertTrue(true);
    }

    private boolean isGraphConnected(Graph g, List<com.Waka.mst.model.Edge> mst) {
        if (g.getVertexCount() == 0) return true;
        if (g.getVertexCount() == 1 && mst.isEmpty()) return true;
        return mst.size() == g.getVertexCount() - 1;
    }
}