package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVHandler {
    ArrayList<GraphNodeAL<Location>> landMarks = new ArrayList<GraphNodeAL<Location>>();
    ArrayList<GraphNodeAL<Location>> junctions = new ArrayList<GraphNodeAL<Location>>();
    ArrayList<String> landMarkNames = new ArrayList<>();

    /**
     * Loads everything from CSV into files
     */
    public void load() {
        loadLandMarks();
        loadJunctions();
        connections();
        buildArrayListOfNames();
    }

    /**
     * loads from landmarks.csv into arrayList
     */
    private void loadLandMarks() {
        String path = "src/sample/landmarks.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Location landMark = new Location(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]));
                GraphNodeAL<Location> graphNodeAL = new GraphNodeAL(landMark);
                landMarks.add(graphNodeAL);


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * loads from junctions.csv into arrayList
     */
    private void loadJunctions() {
        String path = "src/sample/junctions.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Location junction = new Location(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]));
                GraphNodeAL<Location> graphNodeAL = new GraphNodeAL(junction);
                junctions.add(graphNodeAL);


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<GraphNodeAL<Location>> getJunctions() {
        return junctions;
    }

    public ArrayList<GraphNodeAL<Location>> getLandMarks() {
        return landMarks;
    }

    public ArrayList<String> getLandMarkNames() {
        return landMarkNames;
    }

    /**
     * Hardcoded in connections
     */
    public void connections() {
        junctions.get(34).connectToNodeUndirected(junctions.get(0), calculateDistance(junctions.get(34), junctions.get(0)));
        landMarks.get(0).connectToNodeUndirected(junctions.get(0), calculateDistance(landMarks.get(0), junctions.get(0)));
        junctions.get(34).connectToNodeUndirected(junctions.get(33), calculateDistance(junctions.get(34), junctions.get(33)));
        junctions.get(6).connectToNodeUndirected(junctions.get(7), calculateDistance(junctions.get(6), junctions.get(7)));
        junctions.get(5).connectToNodeUndirected(junctions.get(6), calculateDistance(junctions.get(5), junctions.get(6)));
        junctions.get(32).connectToNodeUndirected(junctions.get(33), calculateDistance(junctions.get(32), junctions.get(33)));
        junctions.get(32).connectToNodeUndirected(junctions.get(0), calculateDistance(junctions.get(32), junctions.get(0)));
        junctions.get(32).connectToNodeUndirected(junctions.get(31), calculateDistance(junctions.get(32), junctions.get(31)));
        junctions.get(37).connectToNodeUndirected(junctions.get(31), calculateDistance(junctions.get(37), junctions.get(31)));
        junctions.get(36).connectToNodeUndirected(junctions.get(37), calculateDistance(junctions.get(36), junctions.get(37)));
        junctions.get(35).connectToNodeUndirected(junctions.get(36), calculateDistance(junctions.get(35), junctions.get(36)));
        junctions.get(23).connectToNodeUndirected(junctions.get(35), calculateDistance(junctions.get(23), junctions.get(35)));
        junctions.get(22).connectToNodeUndirected(junctions.get(23), calculateDistance(junctions.get(22), junctions.get(23)));
        junctions.get(11).connectToNodeUndirected(junctions.get(22), calculateDistance(junctions.get(11), junctions.get(22)));
        junctions.get(10).connectToNodeUndirected(junctions.get(44), calculateDistance(junctions.get(10), junctions.get(44)));
        junctions.get(44).connectToNodeUndirected(junctions.get(11), calculateDistance(junctions.get(44), junctions.get(11)));
        junctions.get(9).connectToNodeUndirected(junctions.get(10), calculateDistance(junctions.get(9), junctions.get(10)));
        landMarks.get(6).connectToNodeUndirected(junctions.get(10), calculateDistance(landMarks.get(6), junctions.get(10)));
        landMarks.get(5).connectToNodeUndirected(junctions.get(9), calculateDistance(landMarks.get(5), junctions.get(9)));
        junctions.get(11).connectToNodeUndirected(junctions.get(12), calculateDistance(junctions.get(11), junctions.get(12)));
        junctions.get(12).connectToNodeUndirected(junctions.get(18), calculateDistance(junctions.get(12), junctions.get(18)));
        junctions.get(19).connectToNodeUndirected(junctions.get(18), calculateDistance(junctions.get(19), junctions.get(18)));
        junctions.get(19).connectToNodeUndirected(junctions.get(2), calculateDistance(junctions.get(19), junctions.get(2)));
        landMarks.get(2).connectToNodeUndirected(junctions.get(2), calculateDistance(landMarks.get(2), junctions.get(2)));
        landMarks.get(2).connectToNodeUndirected(junctions.get(21), calculateDistance(landMarks.get(2), junctions.get(21)));
        landMarks.get(2).connectToNodeUndirected(junctions.get(1), calculateDistance(landMarks.get(2), junctions.get(1)));
        junctions.get(21).connectToNodeUndirected(junctions.get(20), calculateDistance(junctions.get(21), junctions.get(20)));
        junctions.get(20).connectToNodeUndirected(junctions.get(19), calculateDistance(junctions.get(20), junctions.get(19)));
        junctions.get(19).connectToNodeUndirected(junctions.get(24), calculateDistance(junctions.get(19), junctions.get(24)));
        junctions.get(24).connectToNodeUndirected(junctions.get(25), calculateDistance(junctions.get(24), junctions.get(25)));
        junctions.get(25).connectToNodeUndirected(junctions.get(26), calculateDistance(junctions.get(25), junctions.get(26)));
        junctions.get(26).connectToNodeUndirected(junctions.get(27), calculateDistance(junctions.get(26), junctions.get(27)));
        junctions.get(36).connectToNodeUndirected(junctions.get(25), calculateDistance(junctions.get(36), junctions.get(25)));
        junctions.get(27).connectToNodeUndirected(junctions.get(28), calculateDistance(junctions.get(27), junctions.get(28)));
        junctions.get(27).connectToNodeUndirected(junctions.get(31), calculateDistance(junctions.get(27), junctions.get(31)));
        junctions.get(28).connectToNodeUndirected(junctions.get(29), calculateDistance(junctions.get(28), junctions.get(29)));
        junctions.get(29).connectToNodeUndirected(junctions.get(32), calculateDistance(junctions.get(29), junctions.get(32)));
        landMarks.get(1).connectToNodeUndirected(junctions.get(30), calculateDistance(landMarks.get(1), junctions.get(30)));
        junctions.get(30).connectToNodeUndirected(junctions.get(1), calculateDistance(junctions.get(30), junctions.get(1)));
        landMarks.get(7).connectToNodeUndirected(junctions.get(1), calculateDistance(landMarks.get(7), junctions.get(1)));
        landMarks.get(7).connectToNodeUndirected(junctions.get(21), calculateDistance(landMarks.get(7), junctions.get(21)));
        junctions.get(2).connectToNodeUndirected(junctions.get(3), calculateDistance(junctions.get(2), junctions.get(3)));
        junctions.get(3).connectToNodeUndirected(junctions.get(4), calculateDistance(junctions.get(3), junctions.get(4)));
        junctions.get(4).connectToNodeUndirected(junctions.get(38), calculateDistance(junctions.get(4), junctions.get(38)));
        landMarks.get(8).connectToNodeUndirected(junctions.get(4), calculateDistance(landMarks.get(8), junctions.get(4)));
        junctions.get(38).connectToNodeUndirected(junctions.get(39), calculateDistance(junctions.get(38), junctions.get(39)));
        junctions.get(39).connectToNodeUndirected(junctions.get(40), calculateDistance(junctions.get(39), junctions.get(40)));
        junctions.get(5).connectToNodeUndirected(junctions.get(3), calculateDistance(junctions.get(5), junctions.get(3)));
        junctions.get(25).connectToNodeUndirected(junctions.get(20), calculateDistance(junctions.get(25), junctions.get(20)));
        junctions.get(40).connectToNodeUndirected(junctions.get(45), calculateDistance(junctions.get(40), junctions.get(45)));
        junctions.get(45).connectToNodeUndirected(junctions.get(16), calculateDistance(junctions.get(45), junctions.get(16)));
        junctions.get(17).connectToNodeUndirected(junctions.get(16), calculateDistance(junctions.get(17), junctions.get(16)));
        junctions.get(17).connectToNodeUndirected(junctions.get(7), calculateDistance(junctions.get(17), junctions.get(7)));
        junctions.get(7).connectToNodeUndirected(junctions.get(46), calculateDistance(junctions.get(7), junctions.get(46)));
        junctions.get(8).connectToNodeUndirected(junctions.get(46), calculateDistance(junctions.get(8), junctions.get(46)));
        landMarks.get(4).connectToNodeUndirected(junctions.get(8), calculateDistance(landMarks.get(4), junctions.get(8)));
        junctions.get(15).connectToNodeUndirected(junctions.get(41), calculateDistance(junctions.get(15), junctions.get(41)));
        junctions.get(16).connectToNodeUndirected(junctions.get(41), calculateDistance(junctions.get(16), junctions.get(41)));
        junctions.get(15).connectToNodeUndirected(junctions.get(40), calculateDistance(junctions.get(15), junctions.get(40)));
        junctions.get(15).connectToNodeUndirected(junctions.get(14), calculateDistance(junctions.get(15), junctions.get(14)));
        junctions.get(13).connectToNodeUndirected(junctions.get(43), calculateDistance(junctions.get(13), junctions.get(43)));
        junctions.get(14).connectToNodeUndirected(junctions.get(43), calculateDistance(junctions.get(14), junctions.get(43)));
        landMarks.get(1).connectToNodeUndirected(junctions.get(0), calculateDistance(landMarks.get(1), junctions.get(0)));
        landMarks.get(3).connectToNodeUndirected(junctions.get(34), calculateDistance(landMarks.get(3), junctions.get(34)));
        junctions.get(29).connectToNodeUndirected(junctions.get(30), calculateDistance(junctions.get(29), junctions.get(30)));
        junctions.get(12).connectToNodeUndirected(junctions.get(42), calculateDistance(junctions.get(12), junctions.get(42)));
        landMarks.get(6).connectToNodeUndirected(junctions.get(64), calculateDistance(landMarks.get(6), junctions.get(64)));
        junctions.get(64).connectToNodeUndirected(junctions.get(63), calculateDistance(junctions.get(64), junctions.get(63)));
        junctions.get(62).connectToNodeUndirected(junctions.get(63), calculateDistance(junctions.get(62), junctions.get(63)));
        junctions.get(62).connectToNodeUndirected(junctions.get(61), calculateDistance(junctions.get(62), junctions.get(61)));
        junctions.get(60).connectToNodeUndirected(junctions.get(61), calculateDistance(junctions.get(60), junctions.get(61)));
        junctions.get(60).connectToNodeUndirected(junctions.get(59), calculateDistance(junctions.get(60), junctions.get(59)));
        junctions.get(58).connectToNodeUndirected(junctions.get(59), calculateDistance(junctions.get(58), junctions.get(59)));
        junctions.get(58).connectToNodeUndirected(junctions.get(57), calculateDistance(junctions.get(58), junctions.get(57)));
        junctions.get(56).connectToNodeUndirected(junctions.get(79), calculateDistance(junctions.get(56), junctions.get(79)));
        junctions.get(56).connectToNodeUndirected(junctions.get(55), calculateDistance(junctions.get(56), junctions.get(55)));
        junctions.get(54).connectToNodeUndirected(junctions.get(55), calculateDistance(junctions.get(54), junctions.get(55)));
        junctions.get(54).connectToNodeUndirected(junctions.get(53), calculateDistance(junctions.get(54), junctions.get(53)));
        junctions.get(47).connectToNodeUndirected(junctions.get(65), calculateDistance(junctions.get(47), junctions.get(65)));
        junctions.get(53).connectToNodeUndirected(junctions.get(65), calculateDistance(junctions.get(53), junctions.get(65)));
        junctions.get(47).connectToNodeUndirected(junctions.get(48), calculateDistance(junctions.get(47), junctions.get(48)));
        junctions.get(49).connectToNodeUndirected(junctions.get(48), calculateDistance(junctions.get(49), junctions.get(48)));
        junctions.get(49).connectToNodeUndirected(junctions.get(50), calculateDistance(junctions.get(49), junctions.get(50)));
        junctions.get(51).connectToNodeUndirected(junctions.get(50), calculateDistance(junctions.get(51), junctions.get(50)));
        junctions.get(51).connectToNodeUndirected(junctions.get(52), calculateDistance(junctions.get(51), junctions.get(52)));
        junctions.get(37).connectToNodeUndirected(junctions.get(66), calculateDistance(junctions.get(37), junctions.get(66)));
        junctions.get(66).connectToNodeUndirected(junctions.get(52), calculateDistance(junctions.get(66), junctions.get(52)));
        junctions.get(67).connectToNodeUndirected(junctions.get(66), calculateDistance(junctions.get(67), junctions.get(66)));
        junctions.get(67).connectToNodeUndirected(junctions.get(68), calculateDistance(junctions.get(67), junctions.get(68)));
        junctions.get(74).connectToNodeUndirected(junctions.get(68), calculateDistance(junctions.get(74), junctions.get(68)));
        junctions.get(69).connectToNodeUndirected(junctions.get(78), calculateDistance(junctions.get(69), junctions.get(23)));
        junctions.get(59).connectToNodeUndirected(junctions.get(70), calculateDistance(junctions.get(59), junctions.get(70)));
        junctions.get(71).connectToNodeUndirected(junctions.get(70), calculateDistance(junctions.get(71), junctions.get(70)));
        junctions.get(71).connectToNodeUndirected(junctions.get(72), calculateDistance(junctions.get(71), junctions.get(72)));
        junctions.get(73).connectToNodeUndirected(junctions.get(72), calculateDistance(junctions.get(73), junctions.get(72)));
        junctions.get(73).connectToNodeUndirected(junctions.get(74), calculateDistance(junctions.get(73), junctions.get(74)));
        junctions.get(69).connectToNodeUndirected(junctions.get(74), calculateDistance(junctions.get(69), junctions.get(74)));
        junctions.get(36).connectToNodeUndirected(junctions.get(74), calculateDistance(junctions.get(36), junctions.get(74)));
        landMarks.get(9).connectToNodeUndirected(junctions.get(47), calculateDistance(landMarks.get(9), junctions.get(47)));
        junctions.get(42).connectToNodeUndirected(junctions.get(13), calculateDistance(junctions.get(42), junctions.get(13)));
        junctions.get(75).connectToNodeUndirected(junctions.get(60), calculateDistance(junctions.get(75), junctions.get(60)));
        junctions.get(75).connectToNodeUndirected(junctions.get(76), calculateDistance(junctions.get(75), junctions.get(76)));
        junctions.get(77).connectToNodeUndirected(junctions.get(76), calculateDistance(junctions.get(77), junctions.get(76)));
        junctions.get(77).connectToNodeUndirected(junctions.get(78), calculateDistance(junctions.get(77), junctions.get(78)));
        junctions.get(35).connectToNodeUndirected(junctions.get(78), calculateDistance(junctions.get(35), junctions.get(78)));
        junctions.get(77).connectToNodeUndirected(junctions.get(73), calculateDistance(junctions.get(77), junctions.get(73)));
        junctions.get(78).connectToNodeUndirected(junctions.get(23), calculateDistance(junctions.get(78), junctions.get(23)));
        junctions.get(79).connectToNodeUndirected(junctions.get(57), calculateDistance(junctions.get(79), junctions.get(57)));


    }

    /**
     * Distance formula from coordinate geometry
     *
     * @param first a point on the map
     * @param last  another point on the map
     * @return
     */
    public int calculateDistance(GraphNodeAL<Location> first, GraphNodeAL<Location> last) {
        double xSquared = first.getData().getPosX() + last.getData().getPosX();
        xSquared = xSquared * xSquared;
        double ySquared = first.getData().getPosY() + last.getData().getPosY();
        ySquared = ySquared * ySquared;
        return (int) Math.sqrt(xSquared + ySquared);
    }

    /**
     * Builds landmarksName arrayList for using entering into the choiceBox in controller
     */
    private void buildArrayListOfNames() {
        for (int i = 0; i < landMarks.size(); i++) {
            landMarkNames.add(landMarks.get(i).getData().getName());
        }
    }
}
