package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTests {

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
}