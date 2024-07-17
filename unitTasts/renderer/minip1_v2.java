package renderer;

import org.junit.jupiter.api.Test;
import geometries.*;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

/**
 * Test rendering a snowman with multiple shapes, reflections, and lights.
 */
public class minip1_v2 {
    private final Scene scene = new Scene("Test scene");

    /** Camera builder for the tests with multiple shapes */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    @Test
    public void createSceneWithTenShapes() {
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        //creating cube
        scene.geometries.add(
                new Polygon(new Point(-50, -50, -100),new Point(0,-30,-150),
                        new Point(0,50,-150),new Point(-50,50,-100))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.3)).setShininess(10).setKr(0.2).setKt(0.4)),
                new Polygon(new Point(0,-30,-150),new Point(50,-50,-100),
                        new Point(50,50,-100),new Point(0,50,-150))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.3)).setShininess(10).setKr(0.2).setKt(0.4)),
                new Polygon(new Point(-50, -50, -100), new Point(0,-80,-100),
                        new Point(50, -50, -100), new Point(0, -20, -100))
                        .setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.3)).setShininess(10).setKr(0.2).setKt(0.4)),
                new Polygon(new Point(-50, 50, -100), new Point(0,70,-100),
                        new Point(50, 50, -100), new Point(0, 30, -100))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.3)).setShininess(10).setKr(0).setKt(0)),

                new Sphere(new Point(0, -29, -100), 15).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.5)).setShininess(100).setKr(0).setKt(0)),
                new Sphere(new Point(0, -5, -100), 12).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.5)).setShininess(100).setKr(0).setKt(0)),
                new Sphere(new Point(0, 14, -100), 9).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.5)).setShininess(100).setKr(0).setKt(0)),

                new Sphere(new Point(-3, 16, -92), 2).setEmission(new Color(BLACK)),  // עין שמאלית
                new Sphere(new Point(3, 16, -92), 2).setEmission(new Color(BLACK)),   // עין ימנית
                new Polygon(new Point(-2, 13, -90), new Point(2, 13, -90), new Point(0, 11, -90)) // פה
                        .setEmission(new Color(ORANGE)),

                new Sphere(new Point(-5, 10, -90), 1).setEmission(new Color(BLACK)),  // כדור 1
                new Sphere(new Point(-2.5, 9, -90), 1).setEmission(new Color(BLACK)),  // כדור 2
                new Sphere(new Point(0, 8, -90), 1).setEmission(new Color(BLACK)),   // כדור 3 (מרכזי)
                new Sphere(new Point(2.5, 9, -90), 1).setEmission(new Color(BLACK)),   // כדור 4
                new Sphere(new Point(5, 10, -90), 1).setEmission(new Color(BLACK)) ,   // כדור 5

                new Triangle(new Point(-10, -5, -98), new Point(-30, -2, -85), new Point(-10, -1, -98))
                        .setEmission(new Color(black)),

                new Triangle(new Point(10, -5, -98), new Point(30, -2, -85), new Point(10, -1, -98))
                        .setEmission(new Color(black)),

                // כפתורים
                new Sphere(new Point(0, -25, -85), 1).setEmission(new Color(BLACK)),    // כפתור 1
                new Sphere(new Point(0, -20, -85), 1).setEmission(new Color(BLACK)),    // כפתור 2
                new Sphere(new Point(0, -15, -85), 1).setEmission(new Color(BLACK)),    // כפתור 3
                new Sphere(new Point(0, -10, -85), 1).setEmission(new Color(BLACK)),    // כפתור 4
                new Sphere(new Point(0, -5, -85), 1).setEmission(new Color(BLACK)),    // כפתור 5
                new Sphere(new Point(0, 0, -85), 1).setEmission(new Color(BLACK))    // כפתור 6
                );

        scene.lights.add(new SpotLight(new Color(100, 300, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));
//        scene.lights.add(new PointLight(new Color(83, 122, 195), new Point(-50, -50, -50))
//                .setKl(0.0001).setKq(0.0001));
//        scene.lights.add(new PointLight(new Color(83, 122, 195), new Point(50, -50, -50))
//                .setKl(0.0001).setKq(0.0001));
//        scene.lights.add(new PointLight(new Color(83, 122, 195), new Point(-50, 50, -50))
//                .setKl(0.0001).setKq(0.0001));
//        scene.lights.add(new PointLight(new Color(83, 122, 195), new Point(50, 50, -50))
//                .setKl(0.0001).setKq(0.0001));
//        scene.lights.add(
//                new SpotLight(new Color(83, 122, 195), new Point(-100, -100, 200), new Vector(1, 1, -3)) //
//                        .setKl(1E-5).setKq(1.5E-7));
//        scene.lights.add(new PointLight(new Color(83, 122, 195), new Point(-100, -400, 800)).setKq(0.000001));

        // Setting up the camera and rendering the image
        cameraBuilder
                .setVpSize(200, 160)
                .setVpDistance(100)
                .setImageWriter(new ImageWriter("Minip1_v2 with AA", 800, 600))
                .build()
                .renderImageWithAntiAliasing(80)
                .writeToImage();
    }
}
