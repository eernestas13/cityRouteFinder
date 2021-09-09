package sample;

public class GraphLinkAL {
    public GraphNodeAL<Location> destNode; //Could also store source node if required
    public int cost; //Other link attributes could be similarly stored

    public void setDestNode(GraphNodeAL<Location> destNode) {
        this.destNode = destNode;
    }

    public GraphNodeAL<Location> getDestNode() {
        return destNode;
    }

    public GraphLinkAL(GraphNodeAL<Location> destNode, int cost) {
        this.destNode = destNode;
        this.cost = cost;
    }
}

