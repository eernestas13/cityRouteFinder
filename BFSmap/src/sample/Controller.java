package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    ImageView coloredMap;
    @FXML
    ImageView originalMap;

    WritableImage writableImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = null;
        Image image1 = null;
        try {
            image = new Image(getClass().getResource("/resources/blackwhitemap.png").toURI().toString(), 692, 352, false, false);
            image1 = new Image(getClass().getResource("/resources/waterfordcity.png").toURI().toString(), 692, 352, false, false);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        originalMap.setImage(image1);
        coloredMap.setImage(image);
        enterPixelsIntoArray(image);
        imageToWritable(image);
        mouseClick();
    }

    ArrayList<Integer> points = new ArrayList<>();

    /**
     * creates a mouseClick mouse event for coloredMap that logs clicks on the map into an arrayList of points
     */
    public void mouseClick() {
        coloredMap.setOnMouseClicked(e -> {
            int point = (int) e.getY() * (int) coloredMap.getImage().getWidth() + (int) e.getX();
            points.add(point);
        });
    }

    /**
     * Simple Image to writableImage method
     * @param image
     */
    public void imageToWritable(Image image) {
        PixelReader pixelReader = image.getPixelReader();
        WritableImage wImage = new WritableImage(pixelReader, (int) image.getWidth(), (int) image.getHeight());
        writableImage = wImage;
    }

    /**
     * Controls the "Test" button on gui, which is the BFS() method
     *
     */
    public void testButton(ActionEvent event) throws URISyntaxException, InterruptedException {
        BFS();
    }

    /**
     * Controls the "build" button, that builds a pathList from the pathBuilder() method
     * It then colors in the pixels represented in this pathList;
     */
    public void pathBuildButton(ActionEvent event) throws URISyntaxException, InterruptedException {
        pathBuilder(points.get(1), points.get(0));
        constructMap();
        System.out.println("Path length in pixel units: " + pixelArray[points.get(1)]);
    }


    int[] pixelArray;

    /**
     * Loads pixels from image into an array where black pixels are represented by -1 and white as 0
     * @param image
     */
    public void enterPixelsIntoArray(Image image) {
        int[] tempArray = new int[(int) image.getWidth() * (int) image.getHeight()];
        Image tempImage = image;
        PixelReader pixelReader = tempImage.getPixelReader();
        for (int c = 0; c < tempImage.getWidth(); c++) {
            for (int r = 0; r < tempImage.getHeight(); r++) {
                if (pixelReader.getColor(c, r).getBrightness() != 0) {
                    tempArray[r * (int) tempImage.getWidth() + c] = 0;
                } else {
                    tempArray[r * (int) tempImage.getWidth() + c] = -1;
                }

            }
        }
        pixelArray = tempArray;
    }

    /**
     * BFS that looks to adjacent pixels from its start if the pixels are white they get added to the agenda list to be
     * dealt with. It changes the values adjacent to +1 of the current location. This accomplishes two tasks as it makes
     * an easy path back for a pathList to be created and it ensure that no pixel can be considered twice as if it has
     * been dealt with already its value is now no longer == 0
     */
    public void BFS() {
        ArrayList<Integer> agendaList = new ArrayList<>();
        int imageWidth = (int) coloredMap.getImage().getWidth();
        agendaList.add(points.get(0));
        pixelArray[points.get(0)] = 1;
        int lookingFor = points.get(1);
        boolean found = false;
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        int hue = 0;
        while (!found) {
            hue++;
            if (hue > 360) {
                hue = 0;
            }
            if (agendaList.isEmpty()) {
                System.out.println("Failed");
                break;
            }
            int currentNode = agendaList.get(0);
            agendaList.remove(0);

            if (currentNode == lookingFor) {
                System.out.println("Worked!");
                found = true;
            }
            int currentValue = pixelArray[currentNode];
            //left
            if (currentNode - 1 >= 0) {
                if (pixelArray[currentNode - 1] == 0) {
                    agendaList.add(currentNode - 1);
                    pixelArray[currentNode - 1] = currentValue + 1;
                }
            }
            //right
            if (currentNode + 1 < pixelArray.length) {
                if (pixelArray[currentNode + 1] == 0) {
                    agendaList.add(currentNode + 1);
                    pixelArray[currentNode + 1] = currentValue + 1;
                }
            }
            //up
            if (currentNode - imageWidth >= 0) {
                if (pixelArray[currentNode - imageWidth] == 0) {
                    agendaList.add(currentNode - imageWidth);
                    pixelArray[currentNode - imageWidth] = currentValue + 1;
                }
            }
            //down
            if (currentNode + imageWidth < pixelArray.length) {
                if (pixelArray[currentNode + imageWidth] == 0) {
                    agendaList.add(currentNode + imageWidth);
                    pixelArray[currentNode + imageWidth] = currentValue + 1;
                }
            }
            Color color = Color.hsb(hue, 1, 1);
            pixelWriter.setColor(currentNode % imageWidth, currentNode / imageWidth, color);
            coloredMap.setImage(writableImage);
        }

    }

    ArrayList<Integer> donePathList = new ArrayList<Integer>();

    /**
     * works its way back from the goal node of BFS() to the start node of BFS(). It look to adjacent pixels and see if
     * they are -1 of its position. If it is that pixel can now be considered.
     * @param startingNode
     * @param destinationNode
     */
    public void pathBuilder(int startingNode, int destinationNode) {
        boolean found = false;
        int imageWidth = (int) coloredMap.getImage().getWidth();
        int currentNode = startingNode;
        ArrayList<Integer> pathList = new ArrayList<>(100000);
        while (!found) {
            pathList.add(currentNode);
            if (currentNode == destinationNode) {
                System.out.println("Found");
                found = true;
            }
            int currentValue = pixelArray[currentNode];
            if (pixelArray[currentNode - 1] == currentValue - 1) {
                currentNode = currentNode - 1;

            }
            if (pixelArray[currentNode + 1] == currentValue - 1) {
                currentNode = currentNode + 1;


            }
            if (pixelArray[currentNode - imageWidth] == currentValue - 1) {
                currentNode = currentNode - imageWidth;


            }
            if (pixelArray[currentNode + imageWidth] == currentValue - 1) {
                currentNode = currentNode + imageWidth;


            }
        }
        donePathList = pathList;
    }

    /**
     * makes pixels in the pathList blue to show the path from start to finish
     */
    public void constructMap() {
        PixelReader pixelReader = originalMap.getImage().getPixelReader();
        WritableImage writableImage = new WritableImage(pixelReader, (int) originalMap.getImage().getWidth(), (int) originalMap.getImage().getHeight());
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int i = 0; i < donePathList.size(); i++) {
            int curX = donePathList.get(i) % (int) originalMap.getImage().getWidth();
            int curY = donePathList.get(i) / (int) originalMap.getImage().getWidth();
            pixelWriter.setColor(curX, curY, Color.BLUE);
        }
        originalMap.setImage(writableImage);
    }
}
