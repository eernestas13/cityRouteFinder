package sample;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BFS {
    Image image;
    Image bwImage;
    WritableImage writableMap;
    WritableImage writableBW;
    int[] pixelArray;
    ArrayList<Integer> pathList = new ArrayList<>();
    int startNode;
    int endNode;
    public BFS(Image image,Image bwImage,int startNode,int endNode){
        this.image=image;
        this.bwImage=bwImage;
        this.startNode=startNode;
        this.endNode = endNode;
        this.writableBW=imageToWritable(bwImage);
        this.writableMap=imageToWritable(image);
        movePixelsIntoArray();
        breadthFirstSearch();
        pathBuilder(startNode,endNode);
    }
    private WritableImage imageToWritable(Image image){
        PixelReader pixelReader = image.getPixelReader();
        WritableImage wImage = new WritableImage(pixelReader,(int)image.getWidth(),(int)image.getHeight());
        return wImage;
    }
    private void movePixelsIntoArray(){
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
    public void breadthFirstSearch() {
        ArrayList<Integer> agendaList = new ArrayList<>();
        int imageWidth = (int) image.getWidth();
        agendaList.add(startNode);
        pixelArray[startNode] = 1;
        int lookingFor = endNode;
        boolean found = false;
        PixelWriter pixelWriter = writableBW.getPixelWriter();
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
            bwImage=writableBW;
        }

    }
    public void pathBuilder(int startingNode, int destinationNode) {
        boolean found = false;
        int imageWidth = (int) bwImage.getWidth();
        int currentNode = startingNode;
        ArrayList<Integer> tempPathList = new ArrayList<>(100000);
        while (!found) {
            tempPathList.add(currentNode);
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
                currentNode = currentNode = imageWidth;


            }
        }
        pathList=tempPathList;
    }
}
