package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTests {


    // =============== Boundary Values Tests ==================
    @Test
    void testExtremeValues() {
        //Points with very large coordinates
        Point p1 = new Point(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        Point p2 = new Point(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
        Vector result = p1.subtract(p2);
        assertNotNull(result, "Subtract method with extreme values should not return null");

        // Points with very small difference
        Point p3 = new Point(1, 1, 1);
        Point p4 = new Point(1 + 1e-10, 1, 1);
        double distance = p3.distance(p4);
        assertTrue(distance > 0, "Distance between almost identical points should be positive");
    }

    // ============ Equivalence Partitions Tests ==============
    @Test
    void testSubtract() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 5, 6);
        Vector result = p2.subtract(p1);
        assertEquals(new Vector(3, 3, 3), result, "Subtract method is incorrect");
    }

    // ============ Equivalence Partitions Tests ==============
    @Test
    void testAdd() {
        Point p = new Point(1, 2, 3);
        Vector v = new Vector(1, 1, 1);
        Point result = p.add(v);
        assertEquals(new Point(2, 3, 4), result, "Add method is incorrect");
    }

    // ============ Equivalence Partitions Tests ==============
    @Test
    void testDistanceSquared() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 6, 8);
        double result = p1.distanceSquared(p2);
        assertEquals(50, result, 0.00001, "DistanceSquared method is incorrect");
    }

    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============

        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 6, 8);
        double result = p1.distance(p2);
        assertEquals(Math.sqrt(50), result, 0.00001, "Distance method is incorrect");

        // =============== Boundary Values Tests ==================

        // Test distance between two identical points
        Point p4 = new Point(1, 2, 3);
        assertEquals(0, p1.distance(p4), "Distance between identical points should be 0");

        // ============ Equivalence Partitions Tests ==============

        // Test distance between two different points
        Point p5 = new Point(4, 5, 6);
        double expectedDistance = Math.sqrt(27); // sqrt((4-1)^2 + (5-2)^2 + (6-3)^2)
        assertEquals(expectedDistance, p1.distance(p5), "Distance between different points is incorrect");
    }

    // =============== Boundary Values Tests ==================
    @Test
    void testDistanceExtremeValues() {
        // Boundary Value Test: Points with very large coordinates
        Point p1 = new Point(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        Point p2 = new Point(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
        double expectedDistance = Math.sqrt(Math.pow(Double.MAX_VALUE - Double.MIN_VALUE, 2) * 3);
        assertEquals(expectedDistance, p1.distance(p2), "Distance with extreme values is incorrect");

        // Boundary Value Test: Points with very small coordinates
        Point p3 = new Point(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
        Point p4 = new Point(0, 0, 0);
        expectedDistance = Math.sqrt(Math.pow(Double.MIN_VALUE, 2) * 3);
        assertEquals(expectedDistance, p3.distance(p4), "Distance with small values is incorrect");
    }

    // ============ Equivalence Partitions Tests ==============
    @Test
    void testTestEquals() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(1, 2, 3);
        Point p3 = new Point(3, 2, 1);
        assertEquals(p1, p2, "Equals method is incorrect");
        assertNotEquals(p1, p3, "Equals method is incorrect");
    }

    // ============ Equivalence Partitions Tests ==============
    @Test
    void testTestToString() {
        Point p = new Point(1, 2, 3);
        assertEquals("Point(1.0, 2.0, 3.0)", p.toString(), "ToString method is incorrect");
    }
}