package geometries;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTests {
    @Test
    void testFindIntersections() {

        // יצירת גופים גיאומטריים
        Sphere sphere = new Sphere(new Point(1, 0, 0), 1);
        Plane plane = new Plane(new Point(0, 0, 1), new Vector(0, 0, 1));
        Triangle triangle = new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));

        // קרן לבדיקה
        Ray ray1 = new Ray(new Point(-1, 0, 0), new Vector(1, 0, 0));
        Ray ray2 = new Ray(new Point(0.5, -1, 2), new Vector(0, 1, 0));
        Ray ray3 = new Ray(new Point(0, 0, -1), new Vector(1, 1, 1));

        // אוסף גיאומטריות
        Geometries geometries = new Geometries(sphere, plane, triangle);

        // אוסף גופים ריק (BVA)
        Geometries emptyGeometries = new Geometries();
        assertNull(emptyGeometries.findIntersections(ray1), "No intersections expected for an empty collection");

        // אף צורה לא נחתכת (BVA)
        Ray rayOutside = new Ray(new Point(-3, 0, 0), new Vector(1, 1, 0));
        assertNull(geometries.findIntersections(rayOutside), "No intersections expected when no shape is intersected");

        // צורה אחת בלבד נחתכת (BVA)
        List<Point> intersectionsOne = geometries.findIntersections(ray1);
        assertNotNull(intersectionsOne);
        assertEquals(2, intersectionsOne.size(), "Expected 2 intersections with one shape (sphere)");

        // כמה צורות (אך לא כולן) נחתכות (EP)
        List<Point> intersectionsSome = geometries.findIntersections(ray2);
        assertNotNull(intersectionsSome);
        assertEquals(3, intersectionsSome.size(), "Expected 3 intersections with some shapes (triangle and plane)");

        // כל הצורות נחתכות (BVA)
        List<Point> intersectionsAll = geometries.findIntersections(ray3);
        assertNotNull(intersectionsAll);
        assertEquals(4, intersectionsAll.size(), "Expected 4 intersections with all shapes");
    }
}