package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTests {
    // ============ Equivalence Partitions Tests ==============

    @Test
    void testGetNormalWithPoint() {
        // Create three points that form a plane
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);

        // Create a plane using the three points constructor
        Plane plane = new Plane(p1, p2, p3);

        // Test the normal at a point on the plane
        Point pointOnPlane = new Point(0, 0, 0);
        Vector normal = plane.getNormal(pointOnPlane);

        // Calculate the expected normal
        Vector expectedNormal = new Vector(0, 0, 1).normalize();

        // Since the normal can be in either direction, we check both possibilities
        assertTrue(normal.equals(expectedNormal) || normal.equals(expectedNormal.scale(-1)),
                "Plane's normal at point is incorrect");
    }

    @Test
    void testGetNormal() {
        // Create three points that form a plane
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);

        // Create a plane using the three points constructor
        Plane plane = new Plane(p1, p2, p3);

        // Test the normal of the plane
        Vector normal = plane.getNormal();

        // Calculate the expected normal
        Vector expectedNormal = new Vector(0, 0, 1).normalize();

        // Since the normal can be in either direction, we check both possibilities
        assertTrue(normal.equals(expectedNormal) || normal.equals(expectedNormal.scale(-1)),
                "Plane's normal is incorrect");
    }

    // =============== Boundary Values Tests ==================
    @Test
    void testConstructorWithCollinearPoints() {
        // Create three collinear points
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(2, 0, 0);

        // Expect IllegalArgumentException due to collinear points
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p3),
                "Expected IllegalArgumentException for collinear points");
    }

    @Test
    void testConstructorWithSamePoints() {
        // Create three identical points
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(0, 0, 0);
        Point p3 = new Point(0, 0, 0);

        // Expect IllegalArgumentException due to identical points
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p3),
                "Expected IllegalArgumentException for identical points");
    }
}
