package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    CSVHandler csvHandler = new CSVHandler();
    Image originalImage;
    @FXML
    ImageView imageView;
    @FXML
    ChoiceBox<String> toBox;
    @FXML
    ChoiceBox<String> fromBox;
    @FXML
    ChoiceBox<String> avoidBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = null;
        try {
            image = new Image(getClass().getResource("/resources/waterfordcity.png").toURI().toString(), 1039, 564, false, false);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        csvHandler.load();
        imageView.setImage(image);
        originalImage = image;
        propagateMap();
        buildBox();
    }

    /**
     * Draws lines from the pathList(n) node to pathList(n+1) node
     *
     * @param path
     */
    public void pathDraw(CostedPath path) {
        for (int i = 0; i < path.pathList.size(); i++) {
            if (i + 1 < path.pathList.size()) {
                Line line = new Line();
                line.setStartX(path.pathList.get(i).getData().getPosX());
                line.setStartY(path.pathList.get(i).getData().getPosY());
                line.setEndX(path.pathList.get(i + 1).getData().getPosX());
                line.setEndY(path.pathList.get(i + 1).getData().getPosY());
                ((Pane) imageView.getParent()).getChildren().add(line);
            }
        }
        System.out.println();
    }

    /**
     * Adds values to the choice box in GUI
     */
    public void buildBox() {
        toBox.getItems().addAll(csvHandler.getLandMarkNames());
        toBox.setValue("Train Station");
        fromBox.getItems().addAll(csvHandler.getLandMarkNames());
        fromBox.setValue("Train Station");
        avoidBox.getItems().addAll(csvHandler.getLandMarkNames());
    }

    /**
     * "Build" button that handles everything with Djikstras shortest path find
     *
     * @param e
     */
    public void shortestPathButton(ActionEvent e) {
        System.out.println("Hello there");
        GraphNodeAL<Location> startNode = null;
        GraphNodeAL<Location> endNode = null;
        GraphNodeAL<Location> nodeToAvoid = null;
        if (avoidBox != null) {
            for (int i = 0; i < csvHandler.getLandMarks().size(); i++) {
                if (csvHandler.getLandMarks().get(i).getData().getName().equals(avoidBox.getValue())) {
                    nodeToAvoid = csvHandler.getLandMarks().get(i);
                }
            }
            for (int i = 0; i < csvHandler.getJunctions().size(); i++) {
                for (int j = 0; j < csvHandler.getJunctions().get(i).getAdjList().size(); j++) {
                    if (csvHandler.getJunctions().get(i).getAdjList().get(j).getDestNode() == nodeToAvoid) {
                        csvHandler.getJunctions().get(i).getAdjList().remove(j);
                    }
                }
            }
        }
        for (int i = 0; i < csvHandler.getLandMarks().size(); i++) {
            if (csvHandler.getLandMarks().get(i).getData().getName().equals(fromBox.getValue())) {
                System.out.println("Start point added");
                startNode = csvHandler.getLandMarks().get(i);
            }
            if (csvHandler.getLandMarks().get(i).getData().getName().equals(toBox.getValue())) {
                System.out.println("End Point Added");
                endNode = csvHandler.getLandMarks().get(i);
            }
        }
        CostedPath path = findCheapestPathDijkstra(startNode, endNode.getData());
        for (int i = 0; i < path.pathList.size(); i++) {
            System.out.println(path.pathList.get(i).getData().name);
        }
        pathDraw(path);

    }

    public void showAllRoutes(ActionEvent event) {
        drawLines();
    }

    /**
     * Helper method just to show the connections between all the points
     */
    public void drawLines() {
        for (int i = 0; i < csvHandler.getJunctions().size(); i++) {
            for (int j = 0; j < csvHandler.getJunctions().get(i).getAdjList().size(); j++) {
                Line line = new Line();
                line.setStartX(csvHandler.getJunctions().get(i).getData().getPosX());
                line.setStartY(csvHandler.getJunctions().get(i).getData().getPosY());
                line.setEndX(csvHandler.getJunctions().get(i).getAdjList().get(j).destNode.getData().getPosX());
                line.setEndY(csvHandler.getJunctions().get(i).getAdjList().get(j).destNode.getData().getPosY());
                line.setStroke(Color.RED);
                ((Pane) imageView.getParent()).getChildren().add(line);
            }
        }
    }

    /**
     * Draws circle on the map for all locations on the map
     */
    public void propagateMap() {
        for (GraphNodeAL<Location> i : csvHandler.getLandMarks()) {
            Circle circle = new Circle();
            circle.setCenterX(i.getData().getPosX());
            circle.setCenterY(i.getData().getPosY());
            circle.setRadius(4);
            circle.setFill(Color.BLUE);
            Tooltip toolTip = new Tooltip("Name: " + i.getData().getName() + " X: " + i.getData().posX + "Y: " + i.getData().getPosY());
            Tooltip.install(circle, toolTip);
            ((Pane) imageView.getParent()).getChildren().add(circle);
        }
        for (GraphNodeAL<Location> i : csvHandler.getJunctions()) {
            Circle circle = new Circle();
            circle.setCenterX(i.getData().getPosX());
            circle.setCenterY(i.getData().getPosY());
            circle.setRadius(4);
            circle.setFill(Color.RED);
            Tooltip toolTip = new Tooltip("Name: " + i.getData().getName() + " X: " + i.getData().posX + " Y: " + i.getData().getPosY());
            Tooltip.install(circle, toolTip);
            ((Pane) imageView.getParent()).getChildren().add(circle);
        }
    }

    /**
     * Djikstra's slightly refactored from notes
     *
     * @param startNode  from node in GUI
     * @param lookingfor end node in GUI
     * @param <T>        Location object
     * @return CostedPath with a ArrayList of all the nodes the path visits
     */
    public static <T> CostedPath findCheapestPathDijkstra(GraphNodeAL<Location> startNode, T lookingfor) {
        CostedPath cp = new CostedPath();
        List<GraphNodeAL<Location>> encountered = new ArrayList<>(), unencountered = new ArrayList<>();
        startNode.setNodeValue(0);
        unencountered.add(startNode);
        GraphNodeAL<Location> currentNode;
        do {
            currentNode = unencountered.remove(0);
            encountered.add(currentNode);
            if (currentNode.getData().equals(lookingfor)) { //Found goal - assemble path list back to start and return it
                cp.pathList.add(currentNode); //Add the current (goal) node to the result list (only element)
                cp.pathCost = currentNode.getNodeValue(); //The total cheapest path cost is the node value of the current/goal node
                while (currentNode != startNode) { //While we're not back to the start node...
                    boolean foundPrevPathNode = false; //Use a flag to identify when the previous path node is identified
                    for (GraphNodeAL<Location> n : encountered) { //For each node in the encountered list...
                        for (GraphLinkAL e : n.getAdjList()) //For each edge from that node...
                            if (e.destNode == currentNode && currentNode.getNodeValue() - e.cost == n.getNodeValue()) { //If that edge links to the
//current node and the difference in node values is the cost of the edge -> found path node!
                                cp.pathList.add(0, n); //Add the identified path node to the front of the result list
                                currentNode = n; //Move the currentNode reference back to the identified path node
                                foundPrevPathNode = true; //Set the flag to break the outer loop
                                break; //We've found the correct previous path node and moved the currentNode reference
//back to it so break the inner loop
                            }
                        if (foundPrevPathNode)
                            break; //We've identified the previous path node, so break the inner loop to continue
                    }
                }
//Reset the node values for all nodes to (effectively) infinity so we can search again (leave no footprint!)
                for (GraphNodeAL<Location> n : encountered) n.setNodeValue(Integer.MAX_VALUE);
                for (GraphNodeAL<Location> n : unencountered) n.setNodeValue(Integer.MAX_VALUE);
                return cp; //The costed (cheapest) path has been assembled, so return it!
            }
//We're not at the goal node yet, so...
            for (GraphLinkAL e : currentNode.getAdjList()) //For each edge/link from the current node...
                if (!encountered.contains(e.destNode)) { //If the node it leads to has not yet been encountered (i.e. processed)
                    e.destNode.setNodeValue(Integer.min(e.destNode.getNodeValue(), currentNode.getNodeValue() + e.cost)); //Update the node value at the end
                    //of the edge to the minimum of its current value or the total of the current node's value plus the cost of the edge
                    unencountered.add(e.destNode);
                }
            Collections.sort(unencountered, (n1, n2) -> n1.getNodeValue() - n2.getNodeValue()); //Sort in ascending node value order
        } while (!unencountered.isEmpty());
        return null; //No path found, so return null
    }
}
