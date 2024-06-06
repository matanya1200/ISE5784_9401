package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

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

    // משתנים של נקודות וויאקטורים לבדיקות
    private final Point p0 = new Point(0, 0, 0);
    private final Point p1 = new Point(1, 0, 0);
    private final Point p2 = new Point(0, 1, 0);
    private final Point p3 = new Point(0, 0, 1);
    private final Vector v001 = new Vector(0, 0, 1);
    private final Vector v100 = new Vector(1, 0, 0);
    private final Vector v111 = new Vector(1, 1, 1);

    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(p0, v001);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray starts outside the plane and intersects it
        Ray ray1 = new Ray(p3, new Vector(0, 1, -1));
        var result1 = plane.findIntersections(ray1);
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(p2, result1.get(0), "Ray intersects the plane");

        // TC02: Ray starts outside the plane and does not intersect it
        Ray ray2 = new Ray(p3, v111);
        assertNull(plane.findIntersections(ray2), "Ray does not intersect the plane");

        // =============== Boundary Values Tests ==================
        // TC03: Ray is parallel to the plane and outside
        Ray ray3 = new Ray(p3, v100);
        assertNull(plane.findIntersections(ray3), "Ray is parallel and outside the plane");

        // TC04: Ray is parallel to the plane and inside
        Ray ray4 = new Ray(p1, v100);
        assertNull(plane.findIntersections(ray4), "Ray is parallel and inside the plane");

        // TC05: Ray is orthogonal to the plane and starts before the plane
        Ray ray5 = new Ray(p3, v001.scale(-1));
        var result5 = plane.findIntersections(ray5);
        assertEquals(1, result5.size(), "Wrong number of points");
        assertEquals(p0, result5.get(0), "Ray is orthogonal and intersects the plane");

        // TC06: Ray is orthogonal to the plane and starts in the plane
        Ray ray6 = new Ray(p0, v001);
        assertNull(plane.findIntersections(ray6), "Ray is orthogonal and starts in the plane");

        // TC07: Ray is orthogonal to the plane and starts after the plane
        Ray ray7 = new Ray(new Point(0, 0, -1), v001.scale(-1));
        assertNull(plane.findIntersections(ray7), "Ray is orthogonal and starts after the plane");

        // TC08: Ray is not parallel and not orthogonal to the plane and starts inside the plane
        Ray ray8 = new Ray(p1, v111);
        assertNull(plane.findIntersections(ray8), "Ray starts inside the plane");

        // TC09: Ray starts exactly at the plane's reference point
        Ray ray9 = new Ray(p0, v111);
        assertNull(plane.findIntersections(ray9), "Ray starts at the plane's reference point");
    }
}
