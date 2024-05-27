package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

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

        // Test the normal of the cylinder at a point on its top base
        Point surfacePointTop = new Point(0.5, 0.5, 2);
        Vector expectedNormalTop = new Vector(0, 0, 1).normalize();
        Vector normalTop = cylinder.getNormal(surfacePointTop);
        assertEquals(expectedNormalTop, normalTop, "Cylinder's top base normal is incorrect");

        // Test the normal of the cylinder at a point on its bottom base
        Point surfacePointBottom = new Point(0.5, 0.5, 0);
        Vector expectedNormalBottom = new Vector(0, 0, -1).normalize();
        Vector normalBottom = cylinder.getNormal(surfacePointBottom);
        assertEquals(expectedNormalBottom, normalBottom, "Cylinder's bottom base normal is incorrect");

        // =============== Boundary Values Tests ==================

        // Boundary Value Test: Point exactly on the edge of the top base
        Point edgePointTop = new Point(1, 0, 2);
        Vector expectedNormalEdgeTop = new Vector(0, 0, 1).normalize();
        Vector normalEdgeTop = cylinder.getNormal(edgePointTop);
        assertEquals(expectedNormalEdgeTop, normalEdgeTop, "Cylinder's edge top base normal is incorrect");

        // Boundary Value Test: Point exactly on the edge of the bottom base
        Point edgePointBottom = new Point(1, 0, 0);
        Vector expectedNormalEdgeBottom = new Vector(0, 0, -1).normalize();
        Vector normalEdgeBottom = cylinder.getNormal(edgePointBottom);
        assertEquals(expectedNormalEdgeBottom, normalEdgeBottom, "Cylinder's edge bottom base normal is incorrect");

    }
}