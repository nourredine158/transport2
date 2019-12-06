package fr.ufc.l3info.oprog;

// Mockito
import org.mockito.junit.MockitoJUnitRunner;

// JUnit
import org.junit.runner.RunWith;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;


@RunWith(MockitoJUnitRunner.Silent.class)
public class TestBarrierIntegration {


    //Pour les gros tests
    Network n;
    Ticket t;
    Barrier b;
    String s;
    Map<Double,Integer> prices;
    String Path;

    Barrier bGlobal;
    Network net;
    @Before
    public void setup() throws IOException, FileFormatException, InvalidNetworkException {

        n = new Network();
        prices = new HashMap<>();
        prices.put(0.0, 200);
        prices.put(3.0, 250);
        prices.put(7.0, 290);
        prices.put(11.0, 320);
        prices.put(15.0, 350);
        prices.put(19.0, 370);

        Path = "./target/classes/csv/";  //: real path "

        String str = "Sapporo";
        net = new Network();
        net.initialize(new File(Path + "sapporo.csv"));
        bGlobal =  Barrier.build(net, str, prices);
    }
    private void initialize_forDijkstra(String nameStation) {
        s = nameStation;
        bGlobal =  Barrier.build(net, s, prices);
    }

    @Test
    public void testBuildNullNetwork() {
        Network nNull = null;
        s = "Sapporo";
        b = Barrier.build(nNull, s, prices);
        Assert.assertEquals(null, b);
    }
    @Test
    public void testBuildNullStation() throws IOException, FileFormatException, InvalidNetworkException {
        String sNull = null;
        n.initialize(new File(Path + "sapporo.csv"));
        b = Barrier.build(n, sNull, prices);
        Assert.assertEquals(null, b);
    }
    @Test
    public void testBuildNullPrices() throws IOException, FileFormatException, InvalidNetworkException {
        Map<Double,Integer> pNull = null;
        n.initialize(new File(Path + "sapporo.csv"));
        s = "Sapporo";
        b = Barrier.build(n, s, pNull);
        Assert.assertEquals(null, b);
    }
    @Test
    public void testBuildContainsZeroLines() {
        Network nNoLines = new Network();
        s = "Sapporo";
        b = Barrier.build(nNoLines, s, prices);
        Assert.assertEquals(null, b);
    }

    @Test
    public void testBuildGetStationByNameNull() throws IOException, FileFormatException, InvalidNetworkException {
        s = "Jesuispasdanslenewtork";
        n.initialize(new File(Path + "sapporo.csv"));
        b = Barrier.build(n, s, prices);
        Assert.assertEquals(null, b);
    }
    @Test
    public void testBuild_pIsEmpty() throws IOException, FileFormatException, InvalidNetworkException {
        Map<Double,Integer> pEmpty = new HashMap<>();
        s = "Sapporo";
        n.initialize(new File(Path + "sapporo.csv"));
        b = Barrier.build(n, s, pEmpty);
        Assert.assertEquals(null, b);
    }

    @Test
    public void testBuild_pDontContains0() throws IOException, FileFormatException, InvalidNetworkException {
        Map<Double,Integer> pDontContains0 = new HashMap<>();
        pDontContains0.put(1.5, 1);
        s = "Sapporo";
        n.initialize(new File(Path + "sapporo.csv"));
        b = Barrier.build(n, s, pDontContains0);
        Assert.assertEquals(null, b);
    }
    @Test
    public void testBuild_DistanceNegative() throws IOException, FileFormatException, InvalidNetworkException {
        Map<Double,Integer> price = new HashMap<>();
        price.put(0.0, 1);
        price.put(-1.0, 3);
        s = "Sapporo";
        n.initialize(new File(Path + "sapporo.csv"));
        b = Barrier.build(n, s, price);
        Assert.assertEquals(null, b);
    }
    @Test
    public void testBuild_MontantNegatif() throws IOException, FileFormatException, InvalidNetworkException {
        Map<Double,Integer> price = new HashMap<>();
        price.put(0.0, -1);
        s = "Sapporo";
        n.initialize(new File(Path + "sapporo.csv"));
        b = Barrier.build(n, s, price);
        Assert.assertEquals(null, b);
    }
    @Test
    public void testBuildOk() throws IOException, FileFormatException, InvalidNetworkException {
        s = "Sapporo";
        n.initialize(new File(Path + "sapporo.csv"));
        b = Barrier.build(n, s, prices);
        Assert.assertNotEquals(null, b);
    }
    @Test public void testEnter_tNotValid() throws IOException, FileFormatException, InvalidNetworkException {
        t = new Ticket(false, 10);
        t.invalidate();
        Assert.assertEquals(false, bGlobal.enter(t));
    }
    @Test public void testEnter_NullEntryStation() throws IOException, FileFormatException, InvalidNetworkException {
        t = new Ticket(false, 10);
        Assert.assertEquals(true,t.isValid());
        t.entering(null);
        Assert.assertEquals(false, bGlobal.enter(t));
    }

    @Test public void testEnter_NegativeOrZeroAmount() throws IOException, FileFormatException, InvalidNetworkException {
        t = new Ticket(false, 0);
        t.entering(null);
        Assert.assertEquals(false, bGlobal.enter(t));
        t = new Ticket(false, -1);
        t.entering(null);
        Assert.assertEquals(false, bGlobal.enter(t));
    }
    @Test public void testEnterOk() throws IOException, FileFormatException, InvalidNetworkException {
        t = new Ticket(false, 10);
        Assert.assertEquals(true,t.isValid());
        Assert.assertEquals(true, bGlobal.enter(t));
    }

    @Test
    public void testExit_Null_getEntryStation() throws IOException, FileFormatException, InvalidNetworkException {
        t = new Ticket(true, 129);
        Assert.assertEquals(false, bGlobal.exit(t));
    }

    @Test //Between Asabu and Fukuzimi
    public void testExitInvalidate_Child_EnoughMoney() throws IOException, FileFormatException, InvalidNetworkException {
        t = new Ticket(true, 180);
        t.entering("Asabu");
        t.invalidate();
        initialize_forDijkstra("Fukuzumi");
        Assert.assertEquals(false, bGlobal.exit(t));
    }
   @Test //Between Fukuzimi and Asabu
    public void testExitInvalidate_Child_NoEnoughMoney() throws IOException, FileFormatException, InvalidNetworkException {
       t = new Ticket(true, 129);
       t.entering("Fukuzumi");
       t.invalidate();
       initialize_forDijkstra("Asabu");
       Assert.assertEquals(false, bGlobal.exit(t));
    }
    @Test //Between Makomanai and Shin-Sapporo
    public void testExitInvalidate_Adult_NoEnoughMoney() throws IOException, FileFormatException, InvalidNetworkException {
        t = new Ticket(false, 360);
        t.entering("Makomanai");
        initialize_forDijkstra("Shin-Sapporo");
        Assert.assertEquals(false, bGlobal.exit(t));
    }
    @Test //Between Shin-Sapporo and Makomanai
    public void testExitInvalidate_Adult_EnoughMoney() throws IOException, FileFormatException, InvalidNetworkException {
        t = new Ticket(false, 400);
        t.entering("Makomanai");
        initialize_forDijkstra("Shin-Sapporo");
        Assert.assertEquals(true, bGlobal.exit(t));
    }
    @Test //Between Asabu and Fukuzimi
    public void testExit_Child_EnoughMoney() throws IOException, FileFormatException, InvalidNetworkException {
        t = new Ticket(true, 160);
        t.entering("Asabu");
        initialize_forDijkstra("Fukuzumi");
        Assert.assertEquals(true, bGlobal.exit(t));

    }
    @Test //Between Fukuzimi and Asabu
    public void testExit_Child_NoEnoughMoney() throws IOException, FileFormatException, InvalidNetworkException {
        t = new Ticket(true, 129);
        t.entering("Asabu");
        initialize_forDijkstra("Fukuzumi");
        Assert.assertEquals(false, bGlobal.exit(t));
    }

    @Test //Between Makomanai and Shin-Sapporo
    public void testExit_Adult_NoEnoughMoney() throws IOException, FileFormatException, InvalidNetworkException {
        t = new Ticket(false, 185);
        t.entering("Makomanai");
        initialize_forDijkstra("Shin-Sapporo");
        Assert.assertEquals(false, bGlobal.exit(t));
    }
    @Test //Between Shin-Sapporo and Makomanai
    public void testExit_Adult_EnoughMoney() throws IOException, FileFormatException, InvalidNetworkException {
        t = new Ticket(false, 400);
        t.entering("Shin-Sapporo");
        initialize_forDijkstra("Makomanai");
        Assert.assertEquals(true, bGlobal.exit(t));
    }

    @Test //Between Sapporo and Sapporo
    public void testExit_SameStations_EnoughMoney() throws IOException, FileFormatException, InvalidNetworkException {
        t = new Ticket(false, 200);
        t.entering("Sapporo");
        initialize_forDijkstra("Sapporo");
        Assert.assertEquals(true, bGlobal.exit(t));
    }
    @Test //Between Sapporo and Sapporo
    public void testExit_SameStations_NoEnoughMoney() throws IOException, FileFormatException, InvalidNetworkException {
        t = new Ticket(false, 199);
        t.entering("Sapporo");
        initialize_forDijkstra("Sapporo");
        Assert.assertEquals(false, bGlobal.exit(t));
    }

}










