package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point a1 = new Point(3, 0);
        Point a2 = new Point(0, 0);

        Assert.assertEquals(a2.distance(a1), 3.0);

    }

    @Test
    public void testDistanc1() {
        Point a1 = new Point(0, 0);
        Point a2 = new Point(0, 0);


        Assert.assertEquals(a2.distance(a1), 0.0);
    }

    @Test
    public void testDistance2() {
        Point a1 = new Point(3, 0);
        Point a2 = new Point(-3, 0);

        Assert.assertEquals(a2.distance(a1), 6.0);
    }

    @Test
    public void testDistance3() {
        Point a1 = new Point(0, 1);
        Point a2 = new Point(0, -1);

        Assert.assertEquals(a2.distance(a1), 2.0);

    }
}
