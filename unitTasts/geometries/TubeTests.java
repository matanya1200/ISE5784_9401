package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class TubeTests {

    @Test
    void testGetNormal() {
        // Create a tube
        Ray axisRay = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        double radius = 1;
        Tube tube = new Tube(radius, axisRay);

        // ============ Equivalence Partitions Tests ==============

        // This test checks the tube's normal at a point on its surface at the positive X side.
        Point surfacePoint = new Point(1, 0, 1);
        Vector expectedNormal = new Vector(1, 0, 0).normalize();
        Vector normal = tube.getNormal(surfacePoint);
        assertEquals(expectedNormal, normal, "Tube's normal is incorrect at (1,0,1)");

        // This test checks the tube's normal at a point on its surface at the positive Y side.
        surfacePoint = new Point(0, 1, 1);
        expectedNormal = new Vector(0, 1, 0).normalize();
        normal = tube.getNormal(surfacePoint);
        assertEquals(expectedNormal, normal, "Tube's normal is incorrect at (0,1,1)");

        //This test checks the tube's normal at a point on its surface at the negative X side.
        surfacePoint = new Point(-1, 0, 1);
        expectedNormal = new Vector(-1, 0, 0).normalize();
        normal = tube.getNormal(surfacePoint);
        assertEquals(expectedNormal, normal, "Tube's normal is incorrect at (-1,0,1)");

        //This test checks the tube's normal at a point on its surface at the negative Y side.
        surfacePoint = new Point(0, -1, 1);
        expectedNormal = new Vector(0, -1, 0).normalize();
        normal = tube.getNormal(surfacePoint);
        assertEquals(expectedNormal, normal, "Tube's normal is incorrect at (0,-1,1)");

        //This test checks the tube's normal at a point on its surface at the positive X side at the base of the tube.
        surfacePoint = new Point(1, 0, 0);
        expectedNormal = new Vector(1, 0, 0).normalize();
        normal = tube.getNormal(surfacePoint);
        assertEquals(expectedNormal, normal, "Tube's normal is incorrect at (1,0,0)");

        //This test checks the tube's normal at a point on its surface at the positive Y side at the base of the tube.
        surfacePoint = new Point(0, 1, 0);
        expectedNormal = new Vector(0, 1, 0).normalize();
        normal = tube.getNormal(surfacePoint);
        assertEquals(expectedNormal, normal, "Tube's normal is incorrect at (0,1,0)");

        //This test checks the tube's normal at a point on its surface at the negative X side at the base of the tube.
        surfacePoint = new Point(-1, 0, 0);
        expectedNormal = new Vector(-1, 0, 0).normalize();
        normal = tube.getNormal(surfacePoint);
        assertEquals(expectedNormal, normal, "Tube's normal is incorrect at (-1,0,0)");

        //This test checks the tube's normal at a point on its surface at the negative Y side at the base of the tube.
        surfacePoint = new Point(0, -1, 0);
        expectedNormal = new Vector(0, -1, 0).normalize();
        normal = tube.getNormal(surfacePoint);
        assertEquals(expectedNormal, normal, "Tube's normal is incorrect at (0,-1,0)");
    }
}
