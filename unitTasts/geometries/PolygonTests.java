package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Polygons
 * @author Dan
 */
public class PolygonTests {
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private final double DELTA = 0.000001;

    /** Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}. */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        assertDoesNotThrow(() -> new Polygon(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(-1, 1, 1)),
                "Failed constructing a correct polygon");

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertix on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertice on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertice on a side");

    }

    /** Test method for {@link geometries.Polygon#getNormal(primitives.Point)}. */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point[] pts =
                { new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1) };
        Polygon pol = new Polygon(pts);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
        // generate the test result
        Vector result = pol.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), DELTA, "Polygon's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertEquals(0d, result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1])), DELTA,
                    "Polygon's normal is not orthogonal to one of the edges");
    }


    // משתנים של נקודות וויאקטורים לבדיקות
    private final Point p1 = new Point(0, 0, 0);
    private final Point p2 = new Point(1, 0, 0);
    private final Point p3 = new Point(1, 1, 0);
    private final Point p4 = new Point(0, 1, 0);

    /**
     * Test method for {@link geometries.Polygon#findIntersections(Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Polygon polygon = new Polygon(p1, p2, p3, p4);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Point of intersection is inside the polygon
        Ray ray1 = new Ray(new Point(0.5, 0.5, 1), new Vector(0, 0, -1));
        var result1 = polygon.findIntersections(ray1);
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(new Point(0.5, 0.5, 0), result1.get(0), "Point is inside the polygon");

        // TC02: Point of intersection is outside the polygon, near one side
        Ray ray2 = new Ray(new Point(1.5, 0.5, 1), new Vector(0, 0, -1));
        assertNull(polygon.findIntersections(ray2), "Point is outside the polygon, near one side");

        // TC03: Point of intersection is outside the polygon, near one vertex
        Ray ray3 = new Ray(new Point(1.5, 1.5, 1), new Vector(0, 0, -1));
        assertNull(polygon.findIntersections(ray3), "Point is outside the polygon, near one vertex");

        // =============== Boundary Values Tests ==================
        // TC04: Point of intersection is on one side of the polygon
        Ray ray4 = new Ray(new Point(0.5, 0, 1), new Vector(0, 0, -1));
//        var result4 = polygon.findIntersections(ray4);
//        assertEquals(1, result4.size(), "Wrong number of points");
//        assertEquals(new Point(0.5, 0, 0), result4.get(0), "Point is on the side of the polygon");
        assertNull(polygon.findIntersections(ray4), "Point is on the side of the polygon");

        // TC05: Point of intersection is on one vertex of the polygon
        Ray ray5 = new Ray(new Point(0, 0, 1), new Vector(0, 0, -1));
//        var result5 = polygon.findIntersections(ray5);
//        assertEquals(1, result5.size(), "Wrong number of points");
//        assertEquals(p1, result5.get(0), "Point is on the vertex of the polygon");
        assertNull(polygon.findIntersections(ray5), "Point is on the vertex of the polygon");

        // TC06: Point of intersection is on the continuation of one side of the polygon
        Ray ray6 = new Ray(new Point(2, 0, 1), new Vector(0, 0, -1));
        assertNull(polygon.findIntersections(ray6), "Point is on the continuation of one side of the polygon");
    }
}
