package com.Waka.mst.model;

import java.util.Objects;

public class Edge implements Comparable<Edge> {
    private String from;
    private String to;
    private int weight;

    public Edge() {}

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String getFrom() { return from; }
    public String getTo() { return to; }
    public int getWeight() { return weight; }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return from + " <-> " + to + " (" + weight + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return weight == edge.weight &&
                ((Objects.equals(from, edge.from) && Objects.equals(to, edge.to)) ||
                        (Objects.equals(from, edge.to) && Objects.equals(to, edge.from)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.min(from.hashCode(), to.hashCode()),
                Math.max(from.hashCode(), to.hashCode()),
                weight);
    }
}