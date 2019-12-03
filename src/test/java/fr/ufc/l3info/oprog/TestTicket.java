package fr.ufc.l3info.oprog;

import fr.ufc.l3info.oprog.Ticket;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTicket {

    Ticket t;

    @Before     // indicates that the method should be executed before each test
    public void setup() {
        // creation of an object to test
        t = new Ticket(true, 1);
    }

    @Test   // indicates that this method is a test case
    public void testConstructeur() {
        // an observation that will obviously succeed
        Assert.assertTrue(t != null);

        Assert.assertTrue(t.getAmount() == 1);

        Assert.assertTrue(t.isChild() == true);

        Ticket t2 = new Ticket(false, -2);

        Assert.assertTrue(t2.isChild() == false);

        Assert.assertTrue(t2.getAmount() == 0);

    }

    @Test   // indicates that this method is a test case
    public void testIsChild() {
        Ticket t2 = new Ticket(false, -2);

        Assert.assertTrue(t.isChild());

        Assert.assertFalse(t2.isChild());
    }

    @Test   // indicates that this method is a test case
    public void testGetAmount() {
        Ticket t2 = new Ticket(false, -2);

        Assert.assertTrue(t.getAmount() == 1);

        Assert.assertTrue(t2.getAmount() == 0);
    }

    @Test   // indicates that this method is a test case
    public void testAdjustFare() {
        t.adjustFare(5);
        Assert.assertTrue(t.getAmount() == 5 + 1);

    }

    @Test   // indicates that this method is a test case
    public void testAdjustFare1() {
        Ticket t2 = new Ticket(false, -2);
        int a = 5;
        t.adjustFare(5);
        Assert.assertFalse(t.getAmount() == a + 0);

    }

    @Test
    public void testAdjustFare2() {
        t.invalidate();
        t.adjustFare(5);
        int a = 5;
        Assert.assertFalse(t.getAmount() == a + 1);
    }

    @Test
    public void testAdjustFare3() {
        Ticket t2 = new Ticket(false, -2);
        int a = 5;
        t2.invalidate();

        t.adjustFare(a);
        Assert.assertFalse(t.getAmount() == a + 0);

    }


    @Test   // indicates that this method is a test case
    public void testEntering() {
        String str = "BONJOUR";

        Assert.assertTrue(t.entering(str) == true);
    }

    @Test   // indicates that this method is a test case
    public void testEntering1() {
        String str = "BONJOUR";
        t.invalidate();
        Assert.assertFalse(t.entering(str) == true);
    }

    @Test   // Je m'attendais à récupérer la valeur false, le ticket est valide mais le parametre de la fonction entering est null.
    public void testEntering2_KO() {
        Assert.assertFalse(t.entering(null));
    }

    @Test   // indicates that this method is a test case
    public void testEntering3() {
        t.invalidate();
        Assert.assertFalse(t.entering(null) == true);
    }

    @Test   // Le ticket est valide mais le parametre est null
    public void testEntering4() {

        Assert.assertFalse(t.entering(""));
    }

    @Test   // indicates that this method is a test case
    public void testEntering5() {
        t.invalidate();
        Assert.assertFalse(t.entering(""));
    }

    @Test   // indicates that this method is a test case
    public void testInvalidate() {
        t.invalidate();
        Assert.assertFalse(t.isValid());
    }

    @Test   // indicates that this method is a test case
    public void testInvalidate1() {
        Ticket t2 = new Ticket(false, -2);
        t2.invalidate();
        t.invalidate();
        Assert.assertEquals(t2.isValid(), t.isValid());
    }

    @Test   // indicates that this method is a test case
    public void testInvalidate2() {
        Ticket t2 = new Ticket(false, -2);
        t2:
        t.invalidate();
        Assert.assertNotEquals(t2.isValid(), t.isValid());
    }

    @Test   // indicates that this method is a test case
    public void testGetEntryStation() {
        t.entering("BONJOUR");

        Assert.assertTrue(t.getEntryStation() == "BONJOUR");
    }

    @Test   // indicates that this method is a test case
    public void testGetEntryStation1() {
        t.entering("");

        Assert.assertFalse(t.getEntryStation() == "BONJOUR");
        Assert.assertFalse(t.getEntryStation() == "");
    }

    @Test   // indicates that this method is a test case
    public void testGetEntryStation3() {
        t.entering("BONJOUR");
        t.invalidate();
        Assert.assertFalse(t.getEntryStation() == "BONJOUR");
    }

    @Test   // indicates that this method is a test case
    public void testIsValid() {
        Assert.assertTrue(t.isValid());
    }

    @Test   // indicates that this method is a test case
    public void testIsValid1() {
        t.invalidate();

        Assert.assertFalse(t.isValid());
    }

    @Test   // indicates that this method is a test case
    public void testIsValid2() {
        t.entering("BONJOUR");

        Assert.assertTrue(t.isValid());
    }

    @Test   // indicates that this method is a test case
    public void testIsValid3() {
        t.entering("");

        Assert.assertFalse(t.isValid());
    }
}
