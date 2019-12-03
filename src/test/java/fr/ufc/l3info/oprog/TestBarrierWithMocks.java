package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.matchers.InstanceOf;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TestBarrierWithMocks {

    @Test
    public void testBuildWithNetworkNull() {
        String station = "";
        Map<Double, Integer> prices = new HashMap<>();

        Assert.assertEquals(Barrier.build(null, station, prices), null);
    }

    @Test
    public void testBuildWithStringNull() {
        Network net = new Network();
        Map<Double, Integer> prices = new HashMap<>();

        Assert.assertEquals(Barrier.build(net, null, prices), null);
    }

    @Test
    public void testBuildWithMapNull() {
        Network net = new Network();
        String station = "";

        Assert.assertEquals(Barrier.build(net, station, null), null);
    }

    @Test
    public void testBuildWithAllNull() {
        Assert.assertEquals(Barrier.build(null, null, null), null);
    }

    @Test
    public void testBuildWithEmptyNetwork() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        Set lines = new HashSet();
        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);
        Map<Double, Integer> prices = new HashMap<>();
        String station = "BONJOUR";

        Assert.assertEquals(null, Barrier.build(mockNetwork, station, prices));
    }

    @Test
    public void testBuildWithStationNotInNetwork() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "BONJOUR";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        prices.put(0.0,2);

        Barrier b = Barrier.build(mockNetwork, station, prices);

        Assert.assertEquals(null, b);
    }

    @Test
    public void testBuildWithPricesEmpty() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "AutreStation";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        //prices.put(0.0,2);

        Barrier b = Barrier.build(mockNetwork, station, prices);

        Assert.assertEquals(null, b);
    }

    @Test
    public void testBuildWithDistancesNegatives() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "AutreStation";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        prices.put(0.0,2);
        prices.put(-1.0,2);

        Barrier b = Barrier.build(mockNetwork, station, prices);

        Assert.assertEquals(null, b);
    }

    @Test
    public void testBuildWithPricesNegatives() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "AutreStation";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        prices.put(0.0,2);
        prices.put(1.0,-2);

        Barrier b = Barrier.build(mockNetwork, station, prices);

        Assert.assertEquals(null, b);
    }

    @Test
    public void testBuildWithPricesNul() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "AutreStation";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        prices.put(0.0,2);
        prices.put(1.0,0);

        Barrier b = Barrier.build(mockNetwork, station, prices);

        Assert.assertEquals(null, b);
    }

    @Test
    public void testBuildWithPricesBeginWithDistance0() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "AutreStation";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        prices.put(1.0,2);
        prices.put(2.0,3);

        Barrier b = Barrier.build(mockNetwork, station, prices);

        Assert.assertEquals(null, b);
    }

    @Test
    public void testBuildWithBasicCase() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "AutreStation";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        prices.put(0.0,2);
        prices.put(2.0,3);

        Barrier b = Barrier.build(mockNetwork, station, prices);

        Assert.assertTrue(b != null);
    }

    @Test
    public void testEnterWithInvalideTicket() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "AutreStation";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        prices.put(0.0,2);
        prices.put(2.0,3);

        Ticket mockTicket = Mockito.mock(Ticket.class);

        Mockito.when(mockTicket.isValid()).thenReturn(false);

        Barrier b = Barrier.build(mockNetwork, station, prices);

        Assert.assertTrue(b.enter(mockTicket) == false);
    }

    @Test
    public void testEnterWithEnteredTicket() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "AutreStation";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        prices.put(0.0,2);
        prices.put(2.0,3);

        Ticket mockTicket = Mockito.mock(Ticket.class);

        Mockito.when(mockTicket.isValid()).thenReturn(true);
        Mockito.when(mockTicket.getEntryStation()).thenReturn("STATION");
        Mockito.when(mockTicket.getAmount()).thenReturn(1);

        Barrier b = Barrier.build(mockNetwork, station, prices);

        Assert.assertTrue(b.enter(mockTicket) == false);
    }

    @Test
    public void testEnterWithAmountNul() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "AutreStation";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        prices.put(0.0,2);
        prices.put(2.0,3);

        Ticket mockTicket = Mockito.mock(Ticket.class);

        Mockito.when(mockTicket.isValid()).thenReturn(true);
        Mockito.when(mockTicket.getEntryStation()).thenReturn(null);

        Mockito.when(mockTicket.getAmount()).thenReturn(0);

        Barrier b = Barrier.build(mockNetwork, station, prices);

        Assert.assertTrue(b.enter(mockTicket) == false);
    }

    @Test
    public void testEnterBasicCase() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "AutreStation";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        prices.put(0.0,2);
        prices.put(2.0,3);

        Ticket mockTicket = Mockito.mock(Ticket.class);

        Mockito.when(mockTicket.isValid()).thenReturn(true);
        Mockito.when(mockTicket.getEntryStation()).thenReturn(null);

        Mockito.when(mockTicket.getAmount()).thenReturn(1);

        Barrier b = Barrier.build(mockNetwork, station, prices);

        Assert.assertTrue(b.enter(mockTicket) == true);
    }

    @Test
    public void testExitInvalidateTicket() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "AutreStation";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        prices.put(0.0,2);
        prices.put(2.0,3);

        prices.put(4.2,5);

        Ticket mockTicket = Mockito.mock(Ticket.class);

        Mockito.when(mockTicket.isValid()).thenReturn(false);
        Mockito.when(mockTicket.getAmount()).thenReturn(5);

        // instanciation du spy
        Barrier b = Mockito.spy(Barrier.build(mockNetwork, station, prices));
        Mockito.when(b.getShortestDistanceTo(ArgumentMatchers.anyString())).thenReturn(4.2);



        Assert.assertTrue(b.exit(mockTicket) == false);
    }

    @Test
    public void testExitUnreachableStation() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "AutreStation";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        prices.put(0.0,2);
        prices.put(2.0,3);

        prices.put(4.2,5);

        Ticket mockTicket = Mockito.mock(Ticket.class);

        Mockito.when(mockTicket.isValid()).thenReturn(true);
        Mockito.when(mockTicket.getAmount()).thenReturn(5);

        // instanciation du spy
        Barrier b = Mockito.spy(Barrier.build(mockNetwork, station, prices));
        Mockito.when(b.getShortestDistanceTo(ArgumentMatchers.anyString())).thenReturn(-1.0);


        Assert.assertTrue(b.exit(mockTicket) == false);
    }

    @Test
    public void testExitIsChild() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "AutreStation";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        prices.put(0.0,2);
        prices.put(2.0,3);

        prices.put(4.2,5);

        Ticket mockTicket = Mockito.mock(Ticket.class);

        Mockito.when(mockTicket.isValid()).thenReturn(true);
        Mockito.when(mockTicket.isChild()).thenReturn(true);

        Mockito.when(mockTicket.getAmount()).thenReturn(3);
        Mockito.when(mockTicket.getEntryStation()).thenReturn("STATION");

        // instanciation du spy
        Barrier b = Mockito.spy(Barrier.build(mockNetwork, station, prices));
        Mockito.when(b.getShortestDistanceTo(ArgumentMatchers.anyString())).thenReturn(4.2);


        Assert.assertTrue(b.exit(mockTicket) == true);
    }

    @Test
    public void testExitBasicCase() {
        // instanciation du mock
        Network mockNetwork = Mockito.mock(Network.class);
        String station = "AutreStation";
        Station s = new Station("AutreStation");

        Set<String> lines = new HashSet();
        lines.add(station);
        Mockito.when(mockNetwork.getLines()).thenReturn(lines);

        // définition du comportement attendu pour l'objet
        Mockito.when(mockNetwork.getStationByName(ArgumentMatchers.anyString())).thenReturn(s);
        Map<Double, Integer> prices = new HashMap<>();
        prices.put(0.0,2);
        prices.put(2.0,3);

        prices.put(4.2,5);

        Ticket mockTicket = Mockito.mock(Ticket.class);

        Mockito.when(mockTicket.isValid()).thenReturn(true);

        Mockito.when(mockTicket.getAmount()).thenReturn(5);
        Mockito.when(mockTicket.getEntryStation()).thenReturn("STATION");

        // instanciation du spy
        Barrier b = Mockito.spy(Barrier.build(mockNetwork, station, prices));
        Mockito.when(b.getShortestDistanceTo(ArgumentMatchers.anyString())).thenReturn(4.2);


        Assert.assertTrue(b.exit(mockTicket) == true);
    }



}
