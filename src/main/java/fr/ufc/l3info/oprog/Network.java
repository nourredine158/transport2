package fr.ufc.l3info.oprog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.lang.String;

public class Network {

    /**
     * Exception class describing file format exception.
     * Thrown during Network initialization.
     */
    public class FileFormatException extends Exception {

        /**
         * Line of the file in which the error is located. Starts at 1.
         */
        int line;

        /**
         * Constructor for the exception.
         *
         * @param f the file whose format is incorrect.
         * @param l the line in the file that is incorrect.
         */
        public FileFormatException(File f, int l) {
            super("Format error in CSV file " + f + " on line " + l);
            this.line = l;
        }

        /**
         * Getter for the line number.
         *
         * @return an integer representing the line number.
         */
        public int getLineNumber() {
            return line;
        }
    }

    /**
     * Exception class describing that an invalid network is detected.
     * Thrown during Network initialization.
     */
    public class InvalidNetworkException extends Exception {

        // Does nothing special. Just signals an error of this kind.

    }

    protected HashSet<Station> reseau;

    public Network() {
        HashSet<Station> reseau = new HashSet<>();
        this.reseau = reseau;
    }

    public void initialize(File csv) throws IOException, FileFormatException, InvalidNetworkException {
        BufferedReader br = new BufferedReader(new FileReader(csv));
        String line;

        int numLigneFichier = 1;

        while ((line = br.readLine()) != null) {


            line.trim();
            if (line.length() == 0) {
                continue;
            }

            char[] chaine = line.toCharArray();
            int nbrVirgule = 0;
            for (char output : chaine) {
                if (output == ',') {
                    nbrVirgule++;
                }
            }


            if (nbrVirgule != 3) {
                throw new InvalidNetworkException();
            }


            line = line.replaceAll("\\s", "");


            int longueurChaine = line.length();
            int indexPremier = line.indexOf(',');


            //TEST Type!!!!!


            String station = line.substring(0, indexPremier);

            if (station.equals("")) {
                throw new FileFormatException(csv, numLigneFichier);
            }

            String numeroS = line.substring(indexPremier + 1, longueurChaine);
            int indexSecond = numeroS.indexOf(',');
            numeroS = numeroS.substring(0, indexSecond);

            if (numeroS.equals("")) {
                throw new FileFormatException(csv, numLigneFichier);
            }

            int numero;
            try {
                numero = Integer.parseInt(numeroS);
            } catch (NumberFormatException e) {
                throw new NumberFormatException();
            }


            String ligne = line.substring(indexPremier + indexSecond + 2, longueurChaine);


            int indexTroisieme = ligne.indexOf(',');

            //TEST Type!!!!!
            if (ligne.equals("") || ligne.substring(0, 1).contains(",")) {
                throw new FileFormatException(csv, numLigneFichier);
            }


            ligne = ligne.substring(0, indexTroisieme);


            String kilometreS = line.substring(indexPremier + indexSecond + indexTroisieme + 3, longueurChaine);

            if (kilometreS.equals("")) {
                throw new FileFormatException(csv, numLigneFichier);
            }

            double kilometre;
            try {
                kilometre = Double.parseDouble(kilometreS);
            } catch (NumberFormatException e) {
                throw new NumberFormatException();
            }

            Station s = new Station(station, ligne, numero, kilometre);

            boolean ajoute = true;

            for (Station st : reseau) {
                if (s.equals(st)) {
                    if (!st.getLines().contains(ligne)) {
                        System.out.println("ligne : -" + station + " " + ligne + " " + numero + " " + kilometre);
                        st.addLine(ligne, numero, kilometre);
                        ajoute = false;
                    } else {
                        ajoute = false;
                    }
                }
            }
            if (ajoute == true) {
                System.out.println("ligne : -" + station + " " + ligne + " " + numero + " " + kilometre);
                reseau.add(s);
            }
            numLigneFichier++;
        }
        br.close();
    }

    public Set<String> getLines() {
        Set<String> lines = new HashSet<>();

        if (!reseau.isEmpty()) {
            for (Station s : reseau) {
                lines.addAll(s.getLines());
            }
        }
        return lines;
    }

    public Station getStationByName(String name) {
        for (Station s : reseau) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    public Station getStationByLineAndNumber(String line, int number) {

        for (Station s : reseau) {
            for (String lineInKeySet : s.getLines()) {
                if (line.equals(lineInKeySet) && s.getNumberForLine(line) == number) {
                    return s;
                }
            }
        }
        return null;
    }
}