package com.Waka.mst.extra;

import com.Waka.mst.model.Edge;
import com.Waka.mst.model.Graph;
import com.Waka.mst.util.JsonUtil;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GraphVisualizer {

    private static final String INPUT_FILE = "src/main/resources/ass_3_input.json";
    private static final String OUTPUT_DIR = "graph_images/";

    private static final String STYLESHEET =
            "node {" +
                    "   fill-color: #F9E79F; size: 15px; stroke-mode: plain; stroke-color: #F39C12;" +
                    "   text-size: 12px; text-alignment: at-right; text-offset: 5px, 0px;" +
                    "}" +
                    "edge {" +
                    "   fill-color: #5D6D7E; text-size: 10px; text-color: #283747; text-background-mode: plain;" +
                    "}";

    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");

        try {
            List<Graph> graphs = JsonUtil.readGraphs(INPUT_FILE);
            Path outputDir = Paths.get(OUTPUT_DIR);

            if (!Files.exists(outputDir)) {
                Files.createDirectories(outputDir);
            }

            System.out.println("Starting visualization for " + graphs.size() + " graphs...");

            for (Graph g : graphs) {
                org.graphstream.graph.Graph vizGraph = new SingleGraph("Graph" + g.getId());
                vizGraph.setAttribute("ui.stylesheet", STYLESHEET);
                vizGraph.setAttribute("ui.quality");
                vizGraph.setAttribute("ui.antialias");

                for (String nodeId : g.getNodes()) {
                    Node n = vizGraph.addNode(nodeId);
                    n.setAttribute("ui.label", nodeId);
                }

                for (Edge edge : g.getEdges()) {
                    String edgeId = edge.getFrom() + "-" + edge.getTo() + "_" + edge.getWeight();
                    org.graphstream.graph.Edge e = vizGraph.addEdge(edgeId, edge.getFrom(), edge.getTo(), false);
                    e.setAttribute("ui.label", String.valueOf(edge.getWeight()));
                }

                String filePath = OUTPUT_DIR + "graph_" + g.getId() + ".png";
                vizGraph.setAttribute("ui.screenshot", filePath);

                Viewer viewer = vizGraph.display(false);
                Thread.sleep(100);
                viewer.close();

                System.out.println("Saved " + filePath);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Visualization complete.");
        System.exit(0);
    }
}