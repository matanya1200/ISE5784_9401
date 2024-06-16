package renderer;

import geometries.*;

import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Integration Tests for Camera intersection with geometries
 */
public class CameraGeometryIntegrationTests {
    private final Camera.Builder cameraBuilder = Camera.getBuilder().setLocation(Point.ZERO)
            .setDirection(new Vector(0,0,-1),new Vector(0,-1,0)).setVpDistance(10)
            .setVpSize(3,3);

    /**
     * Integration test method for constructing rays from camera and checking
     * intersections with geometries.
     */
    @Test
    void CameraGeometryIntegration() {
        Camera camera = cameraBuilder.build();
        // Sphere
        testRayIntersections(camera,new Sphere(new Point(0,0,-3),1),18);

        // Plane
        testRayIntersections(camera, new Plane(new Point(0, 0, -4), new Vector(0, 0, 1)),
                9);

        testRayIntersections(camera, new Plane(new Point(0, 0, -4), new Vector(0, 1, 1)),
                9);

        testRayIntersections(camera, new Plane(new Point(0, 0, -4), new Vector(0, -1, 1)),
                9);

        // Triangle
        testRayIntersections(camera,
                new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2)),
                9);

        testRayIntersections(camera,
                new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2)),
                9);
    }

    /**
     * Helper method to test the number of intersections of rays from the camera
     * with a given geometry.
     *
     * @param camera                the camera
     * @param geometry              the geometry to test intersections with
     * @param expectedIntersections the expected number of intersections
     */
    private void testRayIntersections(Camera camera, Intersectable geometry, int expectedIntersections) {
        int count = 0;
        int nX = 3, nY=3;

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                Ray ray = camera.constructRay(nX, nY, j, i);
                count += geometry.findIntersections(ray).size();
            }
        }

        assertEquals(expectedIntersections, count, "Wrong number of intersections");
    }
}
