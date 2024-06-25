package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CylinderTests {

    @Test
    void testGetNormal() {
        // Create a cylinder
        Ray axisRay = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        double radius = 1;
        double height = 2;
        Cylinder cylinder = new Cylinder(radius, axisRay, height);

        // ============ Equivalence Partitions Tests ==============

        // Test the normal of the cylinder at a point on its surface
        Point surfacePointSide = new Point(1, 0, 1);
        Vector expectedNormalSide = new Vector(1, 0, 0).normalize();
        Vector normalSide = cylinder.getNormal(surfacePointSide);
        assertEquals(expectedNormalSide, normalSide, "Cylinder's side normal is incorrect");

        // =============== Boundary Values Tests ==================

        // Boundary Value Test: Point such that the point-to-axis vector is perpendicular to the axis
        Point edgeCasePointTop = new Point(1, 0, 2);
        Vector expectedNormalEdgeCaseTop = new Vector(0, 0, 1).normalize();
        Vector normalEdgeCaseTop = cylinder.getNormal(edgeCasePointTop);
        assertEquals(expectedNormalEdgeCaseTop, normalEdgeCaseTop, "Cylinder's edge case top normal is incorrect");

        Point edgeCasePointBottom = new Point(1, 0, 0);
        Vector expectedNormalEdgeCaseBottom = new Vector(0, 0, -1).normalize();
        Vector normalEdgeCaseBottom = cylinder.getNormal(edgeCasePointBottom);
        assertEquals(expectedNormalEdgeCaseBottom, normalEdgeCaseBottom, "Cylinder's edge case bottom normal is incorrect");
    }
}