package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Test file for the Station class.
 */
public class TestStation {

    Station s;

    @Before     // indicates that the method should be executed before each test
    public void setup() {
        // creation of an object to test
        s = new Station("ma Station");
    }

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


    @Test   // indicates that this method is a test case
    public void testName() {
        // an observation that will obviously succeed
        Assert.assertTrue(s != null);
        // an observation that will cause the test to fail:
        Assert.assertEquals("ma Station", s.getName());
    }

    @Test   // indicates that this method is a test case
    public void testAddLine() {

        double dist = 10;
        int num = 1;
        s.addLine("ma ligne", num, dist);

        // an observation that will obviously succeed
        Assert.assertTrue(s.getLines() != null);

        // an observation that will cause the test to fail:
        Assert.assertEquals(num, s.getNumberForLine("ma ligne"));

        // an observation that will cause the test to fail:
        Assert.assertEquals(dist, s.getDistanceForLine("ma ligne"),0.0);
    }

    @Test   // indicates that this method is a test case
    public void testRemoveLine() {

        double dist = 10;
        int num = 1;
        s.addLine("ma ligne", num, dist);

        // an observation that will obviously succeed
        Assert.assertTrue(s.getLines() != null);

        // an observation that will cause the test to fail:
        Assert.assertEquals(num, s.getNumberForLine("ma ligne"));

        // an observation that will cause the test to fail:
        Assert.assertEquals(dist, s.getDistanceForLine("ma ligne"),0.0);

        s.removeLine("autre ligne");

        // an observation that will obviously succeed
        Assert.assertTrue(s.getLines() != null);

        // an observation that will cause the test to fail:
        Assert.assertEquals(num, s.getNumberForLine("ma ligne"));

        // an observation that will cause the test to fail:
        Assert.assertEquals(dist, s.getDistanceForLine("ma ligne"),0.0);

        s.removeLine("ma ligne");

        // an observation that will obviously succeed
        Assert.assertTrue(s.getLines().contains("ma ligne") == false);
    }

    @Test   // indicates that this method is a test case
    public void testGetName() {
        // an observation that will obviously succeed
        Assert.assertTrue(s != null);
        // an observation that will cause the test to fail:
        Assert.assertEquals("ma Station", s.getName());
    }

    @Test   // indicates that this method is a test case
    public void testHashCode() {

        Station s2 = new Station("autre Station");

        Station s3 = new Station("ma Station");

        Station s4 = new Station(null);

        // an observation that will obviously succeed
        Assert.assertTrue(s != null);

        // an observation that will cause the test to fail:
        Assert.assertTrue(s.hashCode() != s2.hashCode());

        // an observation that will cause the test to fail:
        Assert.assertEquals(s.hashCode(), s3.hashCode());

        // an observation that will cause the test to fail:
        Assert.assertTrue(s.hashCode() != s4.hashCode());
    }

    @Test   // indicates that this method is a test case
    public void testEquals() {

        Station s2 = new Station("autre Station");

        Station s3 = new Station("ma Station");

        Object s4 = new Object();

        // an observation that will obviously succeed
        Assert.assertTrue(s != null);

        // an observation that will obviously succeed
        Assert.assertTrue(s2 != null);

        // an observation that will cause the test to fail:
        Assert.assertEquals("ma Station", s.getName());

        // an observation that will cause the test to fail:
        Assert.assertTrue(s.equals(s2) != true);

        // an observation that will cause the test to fail:
        Assert.assertTrue(s.equals(s4) != true);

        // an observation that will cause the test to fail:
        Assert.assertTrue(s.equals(s3));
    }

}
