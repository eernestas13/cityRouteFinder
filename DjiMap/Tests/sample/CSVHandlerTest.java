package sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CSVHandlerTest {
    Location location = new Location("Test", 10, 10);
    Location location1 = new Location("Test1", 20, 20);
    Location location2 = new Location("Test2",30,30);
    Location location3= new Location("Test3",40,40);
    GraphNodeAL<Location> graphNodeAL = new GraphNodeAL<>(location);
    GraphNodeAL<Location> graphNodeAL1 = new GraphNodeAL<>(location1);
    GraphNodeAL<Location> graphNodeAL2 = new GraphNodeAL<>(location2);
    GraphNodeAL<Location> graphNodeAL3 = new GraphNodeAL<>(location3);
    CSVHandler csvHandler = new CSVHandler();

    @BeforeEach
    void setUp() {
 graphNodeAL.connectToNodeUndirected(graphNodeAL1,10);
 graphNodeAL.connectToNodeUndirected(graphNodeAL2,15);
 graphNodeAL1.connectToNodeUndirected(graphNodeAL3,10);
 graphNodeAL2.connectToNodeUndirected(graphNodeAL3,10);
    }
    //  node0----node1----node3
    //         10      10        20
    // node0----node2-------node3
    //       15        10          25
    @Test
    void calculateDistance() {
        assertEquals(42, csvHandler.calculateDistance(graphNodeAL, graphNodeAL1));
    }
    void testDji(){
        CostedPath cp=Controller.findCheapestPathDijkstra(graphNodeAL,graphNodeAL3.getData());
        assertEquals(graphNodeAL1,cp.pathList.get(2));
        assertFalse(graphNodeAL2==cp.pathList.get(2));
    }
}