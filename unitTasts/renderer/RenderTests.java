package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.AmbientLight;
import primitives.*;
import renderer.*;
import scene.Scene;



/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class RenderTests {
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
     * Produce a scene with basic 3D model and render it into a png image with a
     * grid
     */
    @Test
    public void renderTwoColorTest() {
        scene.geometries.add(new Sphere(new Point(0, 0, -100), 50d),
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
                // left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
                // left
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
        scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), Double3.ONE))
                .setBackground(new Color(75, 127, 90));

        // right
        Camera camera1 =
                camera.setImageWriter(new ImageWriter("base render test", 1000, 1000))
                        .build();
        camera1.renderImage();
        camera1.printGrid(100, new Color(YELLOW));
        camera1.writeToImage();
    }

    // For stage 6 - please disregard in stage 5
    /**
     * Produce a scene with basic 3D model - including individual lights of the
     * bodies and render it into a png image with a grid
     */
    @Test
    public void basicRenderMultiColorTest() {
        scene.setAmbientLight(new AmbientLight(new Color(0, 0, 0), Double3.ONE))
                .setBackground(new Color(BLACK));
        scene.geometries.add( //
                new Sphere(new Point(0, 0, -100), 50).setEmission(new Color(50,50,50)),
                // up left
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))
                        .setEmission(new Color(GREEN)),
//                // up left
//                new Triangle(new Point(100, 0, -100), new Point(0, 100, -100), new Point(100, 100, -100))
//                        .setEmission(new Color(YELLOW)),
                // down left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
                        .setEmission(new Color(RED)),
                // down right
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
                        .setEmission(new Color(BLUE)));

        Camera camera1 =
                camera.setImageWriter(new ImageWriter("color render test", 1000, 1000))
                .build();
                camera1.renderImage();
                camera1.printGrid(100, new Color(WHITE));
                camera1.writeToImage();
    }
}
//   /** Test for XML based scene - for bonus */
//   @Test
//   public void basicRenderXml() {
//      // enter XML file name and parse from XML file into scene object
//      // using the code you added in appropriate packages
//      // ...
//      // NB: unit tests is not the correct place to put XML parsing code
//
//      Camera camera2 =
//         camera.setImageWriter(new ImageWriter("xml render test", 1000, 1000))
//         .build();
//         camera2.renderImage();
//         camera2.printGrid(100, new Color(YELLOW));
//         camera2.writeToImage();
//   }