package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TriangleTests {

    // משתנים של נקודות וויאקטורים לבדיקות
    private final Point p1 = new Point(0, 0, 0);
    private final Point p2 = new Point(1, 0, 0);
    private final Point p3 = new Point(0, 1, 0);

    @Test
    void testGetNormal() {
        // Create a triangle
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Triangle triangle = new Triangle(p1, p2, p3);

        // Calculate the expected normal
        Vector expectedNormal = new Vector(0, 0, 1).normalize();

        // Test the normal at any point on the triangle
        Vector normal = triangle.getNormal(p1);
        assertEquals(expectedNormal, normal, "Triangle's normal is incorrect");

        normal = triangle.getNormal(p2);
        assertEquals(expectedNormal, normal, "Triangle's normal is incorrect");

        normal = triangle.getNormal(p3);
        assertEquals(expectedNormal, normal, "Triangle's normal is incorrect");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Triangle triangle = new Triangle(p1, p2, p3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Point of intersection is inside the triangle
        Ray ray1 = new Ray(new Point(0, 0, 1), new Vector(0.25, 0.25, -1));
        var result1 = triangle.findIntersections(ray1);
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(new Point(0.25, 0.25, 0), result1.get(0), "Point is inside the triangle");

        // TC02: Point of intersection is outside the triangle, near one side
        Ray ray2 = new Ray(new Point(0, 0, 1), new Vector(-0.5, 0.5, -1));
        assertNull(triangle.findIntersections(ray2), "Point is outside the triangle, near one side");

        // TC03: Point of intersection is outside the triangle, near one vertex
        Ray ray3 = new Ray(new Point(0, 0, 1), new Vector(2, -0.5, -1));
        assertNull(triangle.findIntersections(ray3), "Point is outside the triangle, near one vertex");

        // =============== Boundary Values Tests ==================
        // TC04: Point of intersection is on one side of the triangle
        Ray ray4 = new Ray(new Point(0, 0, 1), new Vector(0.5, 0, -1));
        assertNull(triangle.findIntersections(ray4), "Point is on the side of the triangle");
//        var result4 = triangle.findIntersections(ray4);
//        assertEquals(1, result4.size(), "Wrong number of points");
//        assertEquals(new Point(0.5, 0, 0), result4.get(0), "Point is on the side of the triangle");

        // TC05: Point of intersection is on one vertex of the triangle
        Ray ray5 = new Ray(new Point(0, 0, 1), new Vector(0, 0, -1));
        assertNull(triangle.findIntersections(ray5), "Point is on the vertex of the triangle");
//        var result5 = triangle.findIntersections(ray5);
//        assertEquals(1, result5.size(), "Wrong number of points");
//        assertEquals(p1, result5.get(0), "Point is on the vertex of the triangle");

        // TC06: Point of intersection is on the continuation of one side of the triangle
        Ray ray6 = new Ray(new Point(0, 0, 1), new Vector(2, -1, -1));
        assertNull(triangle.findIntersections(ray6), "Point is on the continuation of one side of the triangle");
    }
}