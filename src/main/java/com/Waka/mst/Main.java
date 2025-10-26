package com.Waka.mst;

import com.Waka.mst.algorithms.KruskalAlgorithm;
import com.Waka.mst.algorithms.PrimAlgorithm;
import com.Waka.mst.model.AlgorithmResult;
import com.Waka.mst.model.Graph;
import com.Waka.mst.util.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String INPUT_FILE = "src/main/resources/ass_3_input.json";
    private static final String OUTPUT_FILE = "ass_3_output.json";

    public static void main(String[] args) {
        try {
            List<Graph> graphs = JsonUtil.readGraphs(INPUT_FILE);

            PrimAlgorithm prim = new PrimAlgorithm();
            KruskalAlgorithm kruskal = new KruskalAlgorithm();

            Map<String, Object> outputData = new LinkedHashMap<>();
            List<Map<String, Object>> resultsList = new ArrayList<>();
            outputData.put("results", resultsList);

            for (Graph graph : graphs) {
                System.out.println("Processing Graph ID: " + graph.getId());
                Map<String, Object> graphResult = new LinkedHashMap<>();
                graphResult.put("graph_id", graph.getId());

                Map<String, Integer> inputStats = new LinkedHashMap<>();
                inputStats.put("vertices", graph.getVertexCount());
                inputStats.put("edges", graph.getEdgeCount());
                graphResult.put("input_stats", inputStats);

                AlgorithmResult primResult = prim.run(graph);
                graphResult.put("prim", primResult);

                AlgorithmResult kruskalResult = kruskal.run(graph);
                graphResult.put("kruskal", kruskalResult);

                resultsList.add(graphResult);
            }

            JsonUtil.writeResults(outputData, OUTPUT_FILE);
            System.out.println("Successfully processed " + graphs.size() + " graphs.");
            System.out.println("Results saved to " + OUTPUT_FILE);

        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}