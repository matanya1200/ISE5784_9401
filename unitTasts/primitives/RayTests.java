package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTests {

    @Test
    void testGetPoint() {
        Ray ray = new Ray(new Point(1, 2, 3),
                new Vector(1, 0, 0));

        // Test with positive distance
        assertEquals(new Point(2, 2, 3), ray.getPoint(1), "Wrong point for positive distance");

        // Test with zero distance
        assertEquals(new Point(1, 2, 3), ray.getPoint(0), "Wrong point for zero distance");

        // Test with negative distance
        assertEquals(new Point(0, 2, 3), ray.getPoint(-1), "Wrong point for negative distance");
    }
}