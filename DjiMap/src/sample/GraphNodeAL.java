package sample;

import java.util.ArrayList;
import java.util.List;

public class GraphNodeAL<T> {
    private T data;
    private List<GraphLinkAL> adjList = new ArrayList<>();
    private int nodeValue = Integer.MAX_VALUE;


    public GraphNodeAL(T data) {
        this.data = data;
    }

    public void connectToNodeDirected(GraphNodeAL<Location> destNode, int cost) {
        adjList.add(new GraphLinkAL((GraphNodeAL<sample.Location>) destNode, cost));
    }

    public void connectToNodeUndirected(GraphNodeAL<Location> destNode, int cost) {
        adjList.add(new GraphLinkAL((GraphNodeAL<sample.Location>) destNode, cost));
        destNode.adjList.add(new GraphLinkAL((GraphNodeAL<sample.Location>) this, cost));
    }

    public void setNodeValue(int nodeValue) {
        this.nodeValue = nodeValue;
    }

    public int getNodeValue() {
        return nodeValue;
    }

    public T getData() {
        return data;
    }

    public List<GraphLinkAL> getAdjList() {
        return adjList;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setAdjList(List<GraphLinkAL> adjList) {
        this.adjList = adjList;
    }
}