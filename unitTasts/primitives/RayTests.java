package primitives;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTests {

    @Test
    void testGetPoint() {
        Ray ray = new Ray(new Point(1, 2, 3),
                new Vector(1, 0, 0));

        // Test with positive distance
        assertEquals(new Point(2, 2, 3), ray.getPoint(1), "Wrong point for positive distance");

        // Test with zero distance
        assertEquals(new Point(1, 2, 3), ray.getPoint(0), "Wrong point for zero distance");

        // Test with negative distance
        assertEquals(new Point(0, 2, 3), ray.getPoint(-1), "Wrong point for negative distance");
    }

    @Test
    void findClosestPoint(){
        List<Point> points = List.of(new Point(1,0,0),new Point(2,0,0),new Point(3,0,0));
        List<Point> empty = new ArrayList<>();
        Vector dir = new Vector(1,0,0);

        // ============ Equivalence Partitions Tests ==============

        Ray ray1 = new Ray(new Point(1.75,0,0),dir);
        assertEquals(new Point(2,0,0),ray1.findClosestPoint(points),"closest point is in the middle of the list");

        // =============== Boundary Values Tests ==================
        //empty list

        assertNull(ray1.findClosestPoint(empty),"the list is empty");

        //first point

        Ray ray2 = new Ray(new Point(0.5,0,0),dir);
        assertEquals(new Point(1,0,0),ray2.findClosestPoint(points),"closest point is in the first of the list");

        //last point

        Ray ray3 = new Ray(new Point(4,0,0),dir);
        assertEquals(new Point(3,0,0),ray3.findClosestPoint(points),"closest point is in the last of the list");
    }
}