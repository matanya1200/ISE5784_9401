package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import scene.Scene;

public class TenShapesWithReflection {
    private final Scene scene = new Scene("Test scene");

    /** Camera builder for the tests with multiple shapes */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    @Test
    public void createSceneWithTenShapes() {
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        // Adding shapes to form an object (e.g., a house)
        scene.geometries.add(
                // House base
                new Polygon(new Point(-50, -40, -100), new Point(50, -40, -100), new Point(50, 0, -100), new Point(-50, 0, -100))
                        .setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
                // Roof
                new Triangle(new Point(-60, 0, -100), new Point(60, 0, -100), new Point(0, 50, -100))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
                // Door
                new Polygon(new Point(-10, -40, -99), new Point(10, -40, -99), new Point(10, -15, -99), new Point(-10, -15, -99))
                        .setEmission(new Color(165, 42, 42)) // Brown color for the door
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
                // Windows
                new Polygon(new Point(-40, -10, -99), new Point(-30, -10, -99), new Point(-30, 0, -99), new Point(-40, 0, -99))
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
                new Polygon(new Point(30, -10, -99), new Point(40, -10, -99), new Point(40, 0, -99), new Point(30, 0, -99))
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
                // Chimney
                new Polygon(new Point(20, 20, -100), new Point(30, 20, -100), new Point(30, 40, -100), new Point(20, 40, -100))
                        .setEmission(new Color(DARK_GRAY))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
                new Triangle(new Point(15, 40, -100), new Point(35, 40, -100), new Point(25, 50, -100))
                        .setEmission(new Color(DARK_GRAY))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
                // Ground
                new Plane(new Point(0, -40, -150), new Vector(0, 1, 0))
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKr(1).setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
                // Tree trunk
                new Polygon(new Point(-70, -40, -100), new Point(-65, -40, -100), new Point(-65, 10, -100), new Point(-70, 10, -100))
                        .setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
                // Tree leaves
                new Sphere(new Point(-67.5, 20, -100), 10)
                        .setEmission(new Color(GREEN))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
                // Sky plane
                new Plane(new Point(0, 0, -150), new Vector(0, 0, 1))
                        .setEmission(new Color(BLUE)) // Sky blue color
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30).setKr(0).setKt(0)),
                // Sun
                new Sphere(new Point(50, 100, -100), 20)
                        .setEmission(new Color(YELLOW)) // Sun color
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30).setKr(0).setKt(0))
        );

        // Adding lights to the scene
        scene.lights.add(new SpotLight(new Color(WHITE), new Point(195, 195, 50), new Vector(-0, -0, -1))
                .setKl(0.00001).setKq(0.000005));
//        scene.lights.add(new SpotLight(new Color(WHITE), new Point(-150, 50, 50), new Vector(1, -1, -2))
//                .setKl(0.00001).setKq(0.000005));

        // Setting up the camera and rendering the image
        cameraBuilder
                .setVpSize(200, 200)
                .setVpDistance(100)
                .setImageWriter(new ImageWriter("TenShapesWithReflection", 800, 800))
                .build()
                .renderImage()
                .writeToImage();
    }
}
