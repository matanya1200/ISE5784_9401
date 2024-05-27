package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class SphereTests {

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
}
