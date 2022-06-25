package ru.stqa.pft.sandbox;
import org.testng.annotations.Test;
public class PointTests {

    @Test

    public void testDistance(){
        Point a1 = new Point(3, 0);
        Point a2 = new Point(0, 0);
        assert a2.distance(a1) == 25;
    }
}
