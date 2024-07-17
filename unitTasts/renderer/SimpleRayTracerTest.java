package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.AmbientLight;
import primitives.*;
import scene.Scene;

/**
 * Test rendering a basic image with anti-aliasing
 */
public class SimpleRayTracerTest {

    /**
     * Scene of the tests
     */
    private final Scene scene = new Scene("Test scene");
    /**
     * Camera builder of the tests
     */
    private final Camera.Builder camera = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(scene))
            .setLocation(Point.ZERO).setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVpDistance(100)
            .setVpSize(500, 500);

    /**
     * Produce a scene with basic 3D model and render it into a png image with anti-aliasing
     */
    @Test
    public void renderWithAntiAliasingTest() {
        scene.setAmbientLight(new AmbientLight(new Color(0, 0, 0), Double3.ONE))
                .setBackground(new Color(BLACK));
        scene.geometries.add( //
                new Sphere(new Point(0, 0, -100), 50).setEmission(new Color(50, 50, 50)),
                // up left
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))
                        .setEmission(new Color(GREEN)),
                // down left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
                        .setEmission(new Color(RED)),
                // down right
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
                        .setEmission(new Color(BLUE)));

        Camera camera1 =
                camera.setImageWriter(new ImageWriter("color render test With AA", 1000, 1000))
                        .build();
        camera1.renderImageWithAntiAliasing(80);  // מספר הקרניים לדגימה
        camera1.printGrid(100, new Color(WHITE));
        camera1.writeToImage();
    }

    /**
     * Produce a scene with basic 3D model and render it into a png image without anti-aliasing
     */
    @Test
    public void renderWithoutAntiAliasingTest() {
        scene.setAmbientLight(new AmbientLight(new Color(0, 0, 0), Double3.ONE))
                .setBackground(new Color(BLACK));
        scene.geometries.add( //
                new Sphere(new Point(0, 0, -100), 50).setEmission(new Color(50, 50, 50)),
                // up left
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))
                        .setEmission(new Color(GREEN)),
                // down left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
                        .setEmission(new Color(RED)),
                // down right
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
                        .setEmission(new Color(BLUE)));

        Camera camera1 =
                camera.setImageWriter(new ImageWriter("color render test WithOut AA", 1000, 1000))
                        .build();
        camera1.renderImage();
        camera1.printGrid(100, new Color(WHITE));
        camera1.writeToImage();
    }
}
