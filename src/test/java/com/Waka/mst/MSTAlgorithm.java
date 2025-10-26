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
}