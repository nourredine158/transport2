package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**
 * Test file for the Station class.
 */
public class TestNetwork {

    private Network r;

    @Before     // indicates that the method should be executed before each test
    public void setup() {
        // creation of an object to test
        r = new Network();
    }

    @Test   // indicates that this method is a test case
    public void testNetwork() {
        // an observation that will obviously succeed
        Assert.assertTrue(r != null);
    }
/*
    @Test   // indicates that this method is a test case
    public void testStation2() {

        double dist = 10;
        int num = 1;
        Station s2 = new Station("autre Station","autre ligne", num, dist);

        // an observation that will obviously succeed
        Assert.assertTrue(s2 != null);

        // an observation that will cause the test to fail:
        Assert.assertEquals("autre Station", s2.getName());

        // an observation that will cause the test to fail:
        Assert.assertEquals("autre Station", s2.getName());

        // an observation that will cause the test to fail:
        Assert.assertTrue(s2.getLines().contains("autre ligne"));
    }
*/

    @Test(expected = Network.FileFormatException.class)  // indicates that this method is a test case
    public void testInitialize() throws Network.FileFormatException, Network.InvalidNetworkException, IOException {
        System.out.println("R");

        File f = new File("./target/classes/csv/test2StationAvecLignesIndentique.csv");
        r.initialize(f);

        System.out.println("R2");

        File f1 = new File("./target/classes/csv/test2StationCorrect.csv");
        Network r2 = new Network();
        r2.initialize(f);

        // an observation that will cause the test to fail:
        Assert.assertTrue(r.reseau.equals(r2.reseau));

        // an observation that will cause the test to fail:
        Assert.assertFalse(r.reseau.isEmpty());

        for (Station s : r.reseau) {
            Assert.assertTrue(s.getName() instanceof String);
            for (String line : s.getLines()) {
                Assert.assertTrue(line instanceof String);
            }
        }

        File f3 = new File("./target/classes/csv/testSansStation.csv");
        Network r3 = new Network();
        r3.initialize(f3);







    }

    @Test  // indicates that this method is a test case
    public void testInitialize1() throws Network.FileFormatException, Network.InvalidNetworkException, IOException {
        File f4 = new File("./target/classes/csv/testSansNumero.csv");
        Network r4 = new Network();
        try{
            r4.initialize(f4);
        }catch (Network.FileFormatException e){
            Assert.assertTrue(e.getLineNumber() == 1);
        }




    }

    @Test(expected = Network.FileFormatException.class)  // indicates that this method is a test case
    public void testInitialize2() throws Network.FileFormatException, Network.InvalidNetworkException, IOException {
        File f5 = new File("./target/classes/csv/testSansKilometre.csv");
        Network r5 = new Network();
        r5.initialize(f5);

    }

    @Test(expected = Network.FileFormatException.class)  // indicates that this method is a test case
    public void testInitialize3() throws Network.FileFormatException, Network.InvalidNetworkException, IOException {
        File f6 = new File("./target/classes/csv/testSansLigne.csv");
        Network r6 = new Network();
        r6.initialize(f6);
    }

    @Test(expected = Network.InvalidNetworkException.class)  // indicates that this method is a test case
    public void testInitialize4() throws Network.FileFormatException, Network.InvalidNetworkException, IOException {
        File f4 = new File("./target/classes/csv/testSansToutesValeurs.csv");
        Network r4 = new Network();
        r4.initialize(f4);
    }


    @Test   // indicates that this method is a test case
    public void testGetLines() throws Network.FileFormatException, Network.InvalidNetworkException, IOException {
        // an observation that will obviously succeed
        Assert.assertTrue(r.getLines() == null);

        File f = new File("./target/classes/csv/test1Ligne.csv");
        r.initialize(f);


        // an observation that will cause the test to fail:
        Assert.assertTrue(r.getLines().contains("Shindō-Higashi"));


    }

    @Test   // indicates that this method is a test case
    public void testGetStationByLineAndNumber() throws Network.FileFormatException, Network.InvalidNetworkException, IOException {
        File f = new File("./target/classes/csv/sapporo.csv");
        r.initialize(f);
        String n = "Asabu";
        int number = 1;

        Assert.assertTrue(r.reseau.contains(r.getStationByLineAndNumber(n, number)));

        Assert.assertFalse(r.reseau.contains(r.getStationByLineAndNumber(n, 20)));
    }

    @Test   // indicates that this method is a test case
    public void testGetStationByName() throws Network.FileFormatException, Network.InvalidNetworkException, IOException {
        File f = new File("./target/classes/csv/sapporo.csv");
        r.initialize(f);
        String n = "Namboku";

        Assert.assertTrue(r.reseau.contains(r.getStationByName(n)));

        Assert.assertFalse(r.reseau.contains(r.getStationByName("sdfgfsf")));
    }

    @Test(expected = NumberFormatException.class)  // indicates that this method is a test case
    public void testIntNumero() throws NumberFormatException, Network.InvalidNetworkException, IOException, Network.FileFormatException {
        File f4 = new File("./target/classes/csv/testNumeroString.csv");
        Network r4 = new Network();
        r4.initialize(f4);
    }

    @Test(expected = NumberFormatException.class)  // indicates that this method is a test case
    public void testDoubleKilo() throws NumberFormatException, Network.InvalidNetworkException, IOException, Network.FileFormatException {
        File f4 = new File("./target/classes/csv/testKilometreString.csv");
        Network r4 = new Network();
        r4.initialize(f4);
    }
}