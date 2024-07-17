package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import scene.Scene;

public class MultipleShapes {
    private final Scene scene = new Scene("Test scene");

    /** Camera builder for the tests with triangles */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    @Test
    public void createSceneWithMultipleShapes() {
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene.geometries.add(
                new Sphere(new Point(-40, -10, -90), 20)
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30).setKt(1)),
                new Triangle(new Point(20, 20, -75), new Point(30, 10, -75), new Point(10, 10, -75))
                        .setEmission(new Color(GREEN))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
                new Plane(new Point(0, -20, -130), new Vector(0, 0, 1)) // שינוי כיוון המישור לניצב למצלמה
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30).setKr(0.9)),
                new Triangle(new Point(0, -50, -80), new Point(0, -30, -80), new Point(20, -30, -80))
                        .setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
                new Polygon(new Point(-2,-65,-95), new Point(-2,-25,-95),
                        new Point(27,-25,-95), new Point(27,-65,-95))
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKr(1)),
                new Sphere(new Point(20, -20, -70), 15)
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKt(0.9).setKr(0.5).setKd(new Double3(0.2)).setKs(new Double3(0.5)).setShininess(50))
        );

        scene.lights.add(new SpotLight(new Color(WHITE), new Point(50,50,50), new Vector(0, 1, -2))
                .setKl(0.00001).setKq(0.000005));

        cameraBuilder
                .setVpSize(150, 150)
                .setVpDistance(100)
                .setImageWriter(new ImageWriter("MultipleShapes", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }
}
