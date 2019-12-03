package fr.ufc.l3info.oprog;


import java.util.HashMap;
import java.util.Set;


/**
 * Class representing a station.
 */
public class Station {

    public class StationInformation {

        private int number;

        private double distance;

        public StationInformation(int number, double distance){
            this.number = number;
            this.distance = distance;
        }

        public double getDistance() {
            return distance;
        }

        public int getNumber() {
            return number;
        }

    }


    private String name;
    private HashMap<String, StationInformation> line;

    /** Builds a station associated to no lines.
     * @param _name the name of the station.
     */
    public Station(String _name) {

        name = _name;
        HashMap<String, StationInformation> line = new HashMap<>();
        this.line = line;
    }

    /**
     * Builds a station, initially associated to a given line with a given number.
     * @param _name the name of the station
     * @param _line the name of the line associated to the station
     * @param _number the number of the station on the considered line
     * @param _dist the distance of the station on the considered line
     */
    public Station(String _name, String _line, int _number, double _dist) {
        name = _name;
        StationInformation st = new StationInformation(_number, _dist);
        HashMap<String, StationInformation> line = new HashMap<>();
        this.line = line;
        this.line.put(_line, st);
    }


    /**
     * Adds a line to the current station, with the appropriate parameters.
     * If the line already exists, the previous information are overwritten.
     * @param _line the name of the line associated to the station
     * @param _number the number of the station on the considered line
     * @param _dist the distance of the station on the considered line
     */
    public void addLine(String _line, int _number, double _dist) {
        StationInformation st = new StationInformation(_number, _dist);
        line.put(_line, st);
    }


    /**
     * Removes a line from the station.
     * @param _line the line to remove.
     */
    public void removeLine(String _line) {
        if (line.containsKey(_line)) {
            line.remove(_line);
        }
    }


    /**
     * Retrieves the name of the station.
     * @return the name of the station
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the number of the station on a given line.
     * @param l The name of the line
     * @return the # of the station for the given line,
     *         or 0 if the line does not exist at the station.
     */
    public int getNumberForLine(String l) {
        int num;

        num = line.get(l).getNumber();

        return num;
    }


    /**
     * Returns the distance of the station on a given line.
     * @param l The name of the line.
     * @return the distance of the station w.r.t. the beginning of the line.
     */
    public double getDistanceForLine(String l) {
        double dist;

        dist = line.get(l).getDistance();

        return dist;
    }

    /**
     * Computes the set of lines associated to the station.
     * @return a set containing the names of the lines that cross the station.
     */
    public Set<String> getLines() {
        return line.keySet();
    }


    @Override
    public int hashCode() {
        if (name == null){
            return 0;
        }
        return name.hashCode() + name.length();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Station){
            Station other = (Station) o;
            if (other.name.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
