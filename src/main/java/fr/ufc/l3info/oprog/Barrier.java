package fr.ufc.l3info.oprog;

import java.util.Map;
import java.util.Set;

import java.lang.Math;

public class Barrier {

    Network net;
    String station;
    Map<Double, Integer> prices;

    private Barrier(Network net, String station, Map<Double, Integer> prices) {
        this.net = net;
        this.station = station;
        this.prices = prices;
    }

    public static Barrier build(Network n, String s, Map<Double, Integer> p) {

        if (n == null || s == null || p == null) {
            return null;
        }

        if (n.getLines().size() == 0) {
            return null;
        }

        if (!n.getStationByName(s).getName().equals(s)) {
            return null;
        }

        if (p.isEmpty() == true) {
            return null;
        }

        for (Double d : p.keySet()) {
            if (d < 0.0) {
                return null;
            }
        }

        for (Integer i : p.values()) {
            if (i <= 0) {
                return null;
            }
        }

        if (!p.keySet().contains(0.0)) {
            return null;
        }

        Barrier b = new Barrier(n, s, p);
        return b;
    }

    public boolean enter(Ticket t) {
        if (t.isValid() && t.getEntryStation() == null && t.getAmount() > 0) {
            return true;
        }
        return false;
    }

    public boolean exit(Ticket t) {
        if (t.isValid()) {
            if (getShortestDistanceTo(t.getEntryStation()) > 0) {
                double distMin = getShortestDistanceTo(t.getEntryStation());
                int valMin = this.prices.get(distMin).intValue();
                if (t.getAmount() >= valMin || t.isChild() && t.getAmount() >= valMin / 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public double getShortestDistanceTo(String s) {
        // Deux stations sur la meme ligne isolée
        /*
        Station from = net.getStationByName(station);
        Station to = net.getStationByName(s);

        Set<String> linesFrom = from.getLines();
        Set<String> linesTo = to.getLines();

        for (String lineFrom : linesFrom) {
            for (String lineTo : linesTo) {
                if (lineFrom.equals(lineTo)) {
                    return Math.abs(from.getDistanceForLine(lineFrom) - to.getDistanceForLine(lineFrom));
                }
            }
        }*/

        // Deux stations sur deux lignes differentes qui se croisent


        return -1.0;
    }
}
