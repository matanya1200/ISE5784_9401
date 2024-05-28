package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class VectorTests {

    @Test
    void testCreation(){
        assertThrows(IllegalArgumentException.class,() ->new Vector(0d,0d,0d),"ERROR: zero vector throws wrong exception");
    }
    // ============ Equivalence Partitions Tests ==============
    @Test
    void testAdd() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, 5, 6);
        Vector result = v1.add(v2);
        assertEquals(new Vector(5, 7, 9), result, "Add method is incorrect");
    }

    // ============ Equivalence Partitions Tests ==============
    @Test
    void testScale() {
        Vector v = new Vector(1, 2, 3);
        Vector result = v.scale(2);
        assertEquals(new Vector(2, 4, 6), result, "Scale method is incorrect");
    }

    // ============ Equivalence Partitions Tests ==============
    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, 5, 6);
        double result = v1.dotProduct(v2);
        assertEquals(32, result, 0.00001, "DotProduct method is incorrect");
    }

    // ============ Equivalence Partitions Tests ==============
    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 1, -1);
        Vector result = v1.crossProduct(v2);
        Vector expected = new Vector(-5, 1, 1);
        assertEquals(expected, result, "CrossProduct method is incorrect");

    }

    // =============== Boundary Values Tests ==================
    @Test
    void testExtremeValues() {
        //Vector with very large coordinates
        Vector v1 = new Vector(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        Vector result = v1.scale(2);
        assertNotNull(result, "Scale method with extreme values should not return null");

        //Cross product with parallel vectors
        Vector v2 = new Vector(1, 1, 1);
        Vector v3 = new Vector(2, 2, 2);
        assertThrows(IllegalArgumentException.class, () -> v2.crossProduct(v3),
                "Cross product of parallel vectors should throw an exception");

        //Dot product with orthogonal vectors
        Vector v4 = new Vector(1, 0, 0);
        Vector v5 = new Vector(0, 1, 0);
        double dotProduct = v4.dotProduct(v5);
        assertEquals(0, dotProduct, 0.00001, "Dot product of orthogonal vectors should be zero");
    }

    // ============ Equivalence Partitions Tests ==============
    @Test
    void testLengthSquared() {
        Vector v = new Vector(1, 2, 2);
        double result = v.lengthSquared();
        assertEquals(9, result, 0.00001, "LengthSquared method is incorrect");
    }

    // ============ Equivalence Partitions Tests ==============
    @Test
    void testLength() {
        Vector v = new Vector(1, 2, 2);
        double result = v.length();
        assertEquals(3, result, 0.00001, "Length method is incorrect");
    }

    // ============ Equivalence Partitions Tests ==============
    @Test
    void testNormalize() {
        Vector v = new Vector(1, 2, 2);
        Vector result = v.normalize();
        assertEquals(new Vector(1/3.0, 2/3.0, 2/3.0), result, "Normalize method is incorrect");
        assertEquals(1, result.length(), 0.00001, "Normalize method should return a vector of length 1");
    }

    // ============ Equivalence Partitions Tests ==============
    @Test
    void testTestEquals() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(1, 2, 3);
        Vector v3 = new Vector(3, 2, 1);
        assertEquals(v1, v2, "Equals method is incorrect");
        assertNotEquals(v1, v3, "Equals method is incorrect");
    }

    // ============ Equivalence Partitions Tests ==============
    @Test
    void testTestToString() {
        Vector v = new Vector(1, 2, 3);
        assertEquals("Vector(1.0, 2.0, 3.0)", v.toString(), "ToString method is incorrect");
    }
}