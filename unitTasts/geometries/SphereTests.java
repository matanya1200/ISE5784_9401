package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SphereTests {

    /**
     * Test method for {@link geometries.Sphere#findIntersections(Ray)}.
     */

    private final Point p100 = new Point(1, 0, 0);

    @Test
    void testGetNormal() {
        // Create a sphere
        Point center = new Point(0, 0, 0);
        Sphere sphere = new Sphere(center, 1);

        // ============ Equivalence Partitions Tests ==============

        // Test the normal of the sphere at a point on its surface
        Point surfacePoint = new Point(1, 0, 0);
        Vector expectedNormal = new Vector(1, 0, 0).normalize();
        Vector normal = sphere.getNormal(surfacePoint);
        assertEquals(expectedNormal, normal, "Sphere's normal is incorrect");

        // Additional tests for other points on the surface
        surfacePoint = new Point(0, 1, 0);
        expectedNormal = new Vector(0, 1, 0).normalize();
        normal = sphere.getNormal(surfacePoint);
        assertEquals(expectedNormal, normal, "Sphere's normal is incorrect");

        surfacePoint = new Point(0, 0, 1);
        expectedNormal = new Vector(0, 0, 1).normalize();
        normal = sphere.getNormal(surfacePoint);
        assertEquals(expectedNormal, normal, "Sphere's normal is incorrect");

        surfacePoint = new Point(-1, 0, 0);
        expectedNormal = new Vector(-1, 0, 0).normalize();
        normal = sphere.getNormal(surfacePoint);
        assertEquals(expectedNormal, normal, "Sphere's normal is incorrect");

        surfacePoint = new Point(0, -1, 0);
        expectedNormal = new Vector(0, -1, 0).normalize();
        normal = sphere.getNormal(surfacePoint);
        assertEquals(expectedNormal, normal, "Sphere's normal is incorrect");

        surfacePoint = new Point(0, 0, -1);
        expectedNormal = new Vector(0, 0, -1).normalize();
        normal = sphere.getNormal(surfacePoint);
        assertEquals(expectedNormal, normal, "Sphere's normal is incorrect");
    }

    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(p100, 1d);
        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final var exp = List.of(gp1, gp2);
        final Vector v310 = new Vector(3, 1, 0);
        final Vector v110 = new Vector(1, 1, 0);
        final Point p01 = new Point(-1, 0, 0);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        final var result1 = sphere.findIntersections(new Ray(p01, v310))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p01)))
                .toList();
        assertEquals(2, result1.size(), "Wrong number of points");
        assertEquals(exp, result1, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        final Point insidePoint = new Point(1.5, 0.5, 0);
        double sqrt2Over2 = Math.sqrt(2) / 2;
        final Point expectedPoint = new Point(1 + sqrt2Over2, 0 + sqrt2Over2, 0);
        var result2 = sphere.findIntersections(new Ray(insidePoint, new Vector(1, 1, 0)));
        assertEquals(1, result2.size(), "Wrong number of points");
        assertEquals(expectedPoint, result2.get(0), "Ray starts inside the sphere");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 1, 0), new Vector(1, 1, 0))),
                "Ray starts after the sphere");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 point)
        var result3 = sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(1, -1, 0)));
        assertEquals(1, result3.size(), "Wrong number of points");
        assertEquals(new Point(2, 0, 0), result3.get(0), "Ray starts at sphere and goes inside");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(-1, 1, 0))),
                "Ray starts at sphere and goes outside");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        var result4 = sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 0, 0)));
        assertEquals(2, result4.size(), "Wrong number of points");
        assertEquals(List.of(new Point(0, 0, 0), new Point(2, 0, 0)), result4, "Ray goes through the center");

        // TC14: Ray starts at sphere and goes inside (1 point)
        var result5 = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, result5.size(), "Wrong number of points");
        assertEquals(new Point(2, 0, 0), result5.get(0), "Ray starts at sphere and goes inside");

        // TC15: Ray starts inside (1 point)
        var result6 = sphere.findIntersections(new Ray(new Point(1.5, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, result6.size(), "Wrong number of points");
        assertEquals(new Point(2, 0, 0), result6.get(0), "Ray starts inside the sphere");

        // TC16: Ray starts at the center (1 point)
        var result7 = sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, result7.size(), "Wrong number of points");
        assertEquals(new Point(2, 0, 0), result7.get(0), "Ray starts at the center");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(-1, 0, 0))),
                "Ray starts at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))),
                "Ray starts after sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(0, 1, 0), new Vector(1, 0, 0))),
                "Ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(1, 0, 0))),
                "Ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2, 1, 0), new Vector(1, 0, 0))),
                "Ray starts after the tangent point");

        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(0, 1, 0))),
                "Ray is orthogonal to ray start to sphere's center line");
    }
}
