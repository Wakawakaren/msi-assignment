package com.Waka.mst.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.Waka.mst.model.Graph;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class JsonUtil {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Graph> readGraphs(String filePath) throws IOException {
        try (Reader reader = new FileReader(filePath)) {
            Graph.GraphInput input = gson.fromJson(reader, Graph.GraphInput.class);
            return input.graphs;
        }
    }

    public static void writeResults(Object results, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(results, writer);
        }
    }
}