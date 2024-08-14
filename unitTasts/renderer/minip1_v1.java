package renderer;

import lighting.PointLight;
import org.junit.jupiter.api.Test;
import geometries.*;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.*;

///**
// * Test rendering a snowman with multiple shapes, reflections, and lights.
// */
//public class minip1_v1 {
//    private final Scene scene = new Scene("Test scene");
//
//    /** Camera builder for the tests with multiple shapes */
//    private final Camera.Builder cameraBuilder = Camera.getBuilder()
//            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
//            .setRayTracer(new SimpleRayTracer(scene));
//
//    @Test
//    public void createSceneWithTenShapes() {
//        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));
//
//        //creating sky and snow (ground and background)
//        scene.geometries.add(
//                // Sky plane
//                new Plane(new Point(0, 0, -400), new Vector(1.5, 0, 1))
//                        .setEmission(new Color(BLUE))
//                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30).setKr(0)),
//                // Ground
//                new Plane(new Point(0, -80, -150), new Vector(0, 1, 0))
//                        .setEmission(new Color(GRAY))
//                        .setMaterial(new Material().setKr(1).setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30))
//        );
//
//        scene.geometries.add(
//                new Polygon(new Point(70,-80,-180),new Point(76,-80,-180),
//                        new Point(76,10,-180),new Point(70,10,-180)),
//
//                new Triangle(new Point(53,0,-180),new Point(95,0,-180),new Point(76,40,-180))
//                        .setEmission(new Color(GREEN)),
//                new Triangle(new Point(53,0,-180),new Point(60,-5,-140),new Point(76,40,-180))
//                        .setEmission(new Color(0,140,0))
//                        .setMaterial(new Material().setKt(0.5).setKr(0.2)),
//                new Triangle(new Point(95,0,-180),new Point(60,-5,-140),new Point(76,40,-180))
//                        .setEmission(new Color(0,140,0))
//                        .setMaterial(new Material().setKt(0.5).setKr(0.2)),
//
//                new Triangle(new Point(53,-20,-180),new Point(95,-20,-180),new Point(76,20,-180))
//                        .setEmission(new Color(GREEN)),
//                new Triangle(new Point(53,-20,-180),new Point(60,-25,-140),new Point(76,20,-180))
//                        .setEmission(new Color(0,180,0))
//                        .setMaterial(new Material().setKt(0.5).setKr(0.2)),
//                new Triangle(new Point(95,-20,-180),new Point(60,-25,-140),new Point(76,20,-180))
//                        .setEmission(new Color(0,180,0))
//                        .setMaterial(new Material().setKt(0.5).setKr(0.2)),
//
//                new Sphere(new Point(120,30,-170),5).setEmission(new Color(BLACK))
//                        .setMaterial(new Material().setKd(0.7).setKs(0.4).setKt(0.5).setKt(0.8))
//        );
//
//        //creating snowmen
//        scene.geometries.add(
//                // Bottom sphere of the snowman
//                new Sphere(new Point(0, -55, -150), 25)
//                        .setEmission(new Color(GRAY)) // Emission color to give the sphere a white tint
//                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.5)).setShininess(100).setKr(0).setKt(0)),
//                // Middle sphere of the snowman
//                new Sphere(new Point(0, -20, -150), 20)
//                        .setEmission(new Color(GRAY)) // Emission color to give the sphere a white tint
//                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.5)).setShininess(100).setKr(0).setKt(0)),
//                // Top sphere of the snowman
//                new Sphere(new Point(0, 5, -150), 15)
//                        .setEmission(new Color(GRAY)) // Emission color to give the sphere a white tint
//                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.5)).setShininess(100).setKr(0).setKt(0)),
//
//                // Left eye
//                new Sphere(new Point(-5, 12, -137), 2)
//                        .setEmission(new Color(BLACK))
//                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30).setKt(0.2)),
//                // Right eye
//                new Sphere(new Point(5, 12, -137), 2)
//                        .setEmission(new Color(BLACK))
//                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30).setKt(0.2)),
//
//                // Mouth - five small spheres
//                new Sphere(new Point(-6, 2, -135), 1)
//                        .setEmission(new Color(BLACK))
//                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
//                new Sphere(new Point(-3, 1, -135), 1)
//                        .setEmission(new Color(BLACK))
//                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
//                new Sphere(new Point(0, 0, -135), 1)
//                        .setEmission(new Color(BLACK))
//                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
//                new Sphere(new Point(3, 1, -135), 1)
//                        .setEmission(new Color(BLACK))
//                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
//                new Sphere(new Point(6, 2, -135), 1)
//                        .setEmission(new Color(BLACK))
//                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
//
//                // Nose (triangle)
//                new Triangle(new Point(0, 8, -135), new Point(2, 5, -135), new Point(-2, 5, -135))
//                        .setEmission(new Color(ORANGE))
//                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30))
//        );
//
//        // Adding arms using triangles
//        scene.geometries.add(
//                // Left arm
//                new Triangle(
//                        new Point(-45, -23, -145), // Bottom-left
//                        new Point(-18, -20, -145), // Top-right
//                        new Point(-18, -23, -145)  // Bottom-right
//                )
//                        .setEmission(new Color(139, 69, 19)) // Brown color
//                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)),
//
//                // Right arm
//                new Triangle(
//                        new Point(45, -23, -145), // Bottom-right
//                        new Point(18, -20, -145), // Top-left
//                        new Point(18, -23, -145)  // Bottom-left
//                )
//                        .setEmission(new Color(139, 69, 19)) // Brown color
//                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30))
//        );
//
//        // Adding buttons using a loop
//        double buttonRadius = 1.5;
//        for (int i = 0; i < 6; i++) {
//            double yPosition = -50 + i * 8;
//            scene.geometries.add(
//                    new Sphere(new Point(0, yPosition, -125), buttonRadius)
//                            .setEmission(new Color(BLACK))
//                            .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30))
//            );
//        }
//
//
//        // Adding snowflakes in random positions
//        double snowflakeRadius = 1;
//        for (int i = 0; i < 30; i++) {
//            double x = -100 + Math.random() * 200;
//            double y = -100 + Math.random() * 200;
//            scene.geometries.add(
//                    new Sphere(new Point(x, y, -99), snowflakeRadius)
//                            .setEmission(new Color(WHITE))
//                            .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30))
//            );
//        }
//
//
//        // Adding clouds
//        for (int i = 0; i < 15; i++) {
//            double x = -50 + Math.random() * 100;
//            double y = 30 + Math.random() * 20;
//            double z = -150 + Math.random() * 20;
//            for (int j = 0; j < 5; j++) {
//                double cloudX = x + Math.random() * 10;
//                double cloudY = y + Math.random() * 10;
//                double cloudZ = z + Math.random() * 10;
//                scene.geometries.add(
//                        new Sphere(new Point(cloudX, cloudY, cloudZ), 5)
//                                .setEmission(new Color(LIGHT_GRAY))
//                                .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.5)).setShininess(100).setKr(0).setKt(0))
//                );
//            }
//        }
//
//
//        // Adding snowdrifts at different heights
//        scene.geometries.add(
//                // Lower snowdrift
//                new Sphere(new Point(-100, -80, -180), 10)
//                        .setEmission(new Color(LIGHT_GRAY))
//                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(150)),
//                // Middle snowdrift
//                new Sphere(new Point(-150, -80, -180), 15)
//                        .setEmission(new Color(LIGHT_GRAY))
//                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(150)),
//                // Upper snowdrift
//                new Sphere(new Point(100, -80, -180), 25)
//                        .setEmission(new Color(LIGHT_GRAY))
//                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(150))
//        );
//
//
//        // Adding lights to the scene
//        scene.lights.add(new SpotLight(new Color(WHITE), new Point(150, 100, 50), new Vector(-1, 1, -2))
//                .setKl(0.000001).setKq(0.000005));
//        scene.lights.add(new PointLight(new Color(WHITE), new Point(-50, 50, -50))
//                .setKl(0.0005).setKq(0.0005));
//        scene.lights.add(new PointLight(new Color(WHITE), new Point(50, 50, -50))
//                .setKl(0.0005).setKq(0.0005));
//        scene.lights.add(new SpotLight(new Color(BLUE), new Point(150, 0, 50), new Vector(-1, 1, -1))
//                .setKl(0.000001).setKq(0.000005));
//
//        // Setting up the camera and rendering the image
//        cameraBuilder
//                .setVpSize(200, 160)
//                .setVpDistance(100)
//                .setImageWriter(new ImageWriter("minip1_v1 with AA", 800, 600))
//                .build()
//                .setMultiThreading(true)
//                .renderImageWithAntiAliasing(80)
//                .writeToImage();
//    }
//}

// without BVH
public class minip1_v1 {
    private Scene scene;
    private Camera.Builder cameraBuilder;

    private void setupScene() {
        scene = new Scene("Test scene");
        cameraBuilder = Camera.getBuilder()
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setRayTracer(new SimpleRayTracer(scene));

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        // Add planes (always without BVH)
        scene.geometries.add(
                new Plane(new Point(0, 0, -400), new Vector(1.5, 0, 1))
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30).setKr(0)),
                new Plane(new Point(0, -80, -150), new Vector(0, 1, 0))
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKr(1).setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30))
        );

        // Add lights
        scene.lights.add(new SpotLight(new Color(WHITE), new Point(150, 100, 50), new Vector(-1, 1, -2))
                .setKl(0.000001).setKq(0.000005));
        scene.lights.add(new PointLight(new Color(WHITE), new Point(-50, 50, -50))
                .setKl(0.0005).setKq(0.0005));
        scene.lights.add(new PointLight(new Color(WHITE), new Point(50, 50, -50))
                .setKl(0.0005).setKq(0.0005));
        scene.lights.add(new SpotLight(new Color(BLUE), new Point(150, 0, 50), new Vector(-1, 1, -1))
                .setKl(0.000001).setKq(0.000005));
    }

    private List<Intersectable> createGeometries() {
        List<Intersectable> geometries = new ArrayList<>();

        //creating snowmen
        geometries.add(new Sphere(new Point(0, -55, -150), 25)
                        .setEmission(new Color(GRAY)) // Emission color to give the sphere a white tint
                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.5)).setShininess(100).setKr(0).setKt(0)));

        geometries.add(new Sphere(new Point(0, -20, -150), 20)
                        .setEmission(new Color(GRAY)) // Emission color to give the sphere a white tint
                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.5)).setShininess(100).setKr(0).setKt(0)));

        geometries.add(new Sphere(new Point(0, 5, -150), 15)
                        .setEmission(new Color(GRAY)) // Emission color to give the sphere a white tint
                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.5)).setShininess(100).setKr(0).setKt(0)));

                // Left eye
        geometries.add(new Sphere(new Point(-5, 12, -137), 2)
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30).setKt(0.2)));
                // Right eye
        geometries.add(new Sphere(new Point(5, 12, -137), 2)
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30).setKt(0.2)));

                // Mouth - five small spheres
        geometries.add(new Sphere(new Point(-6, 2, -135), 1)
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)));
        geometries.add(new Sphere(new Point(-3, 1, -135), 1)
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)));
        geometries.add(new Sphere(new Point(0, 0, -135), 1)
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)));
        geometries.add(new Sphere(new Point(3, 1, -135), 1)
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)));
        geometries.add(new Sphere(new Point(6, 2, -135), 1)
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)));

                // Nose (triangle)
        geometries.add(new Triangle(new Point(0, 8, -135), new Point(2, 5, -135), new Point(-2, 5, -135))
                        .setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)));

        geometries.add(new Polygon(new Point(70,-80,-180),new Point(76,-80,-180),
                new Point(76,10,-180),new Point(70,10,-180)));

        geometries.add(new Triangle(new Point(53,0,-180),new Point(95,0,-180),new Point(76,40,-180))
                .setEmission(new Color(GREEN)));
        geometries.add(new Triangle(new Point(53,0,-180),new Point(60,-5,-140),new Point(76,40,-180))
                .setEmission(new Color(0,140,0))
                .setMaterial(new Material().setKt(0.5).setKr(0.2)));
        geometries.add(new Triangle(new Point(95,0,-180),new Point(60,-5,-140),new Point(76,40,-180))
                .setEmission(new Color(0,140,0))
                .setMaterial(new Material().setKt(0.5).setKr(0.2)));

        geometries.add(new Triangle(new Point(53,-20,-180),new Point(95,-20,-180),new Point(76,20,-180))
                .setEmission(new Color(GREEN)));
        geometries.add(new Triangle(new Point(53,-20,-180),new Point(60,-25,-140),new Point(76,20,-180))
                .setEmission(new Color(0,180,0))
                .setMaterial(new Material().setKt(0.5).setKr(0.2)));
        geometries.add(new Triangle(new Point(95,-20,-180),new Point(60,-25,-140),new Point(76,20,-180))
                .setEmission(new Color(0,180,0))
                .setMaterial(new Material().setKt(0.5).setKr(0.2)));

        geometries.add(new Sphere(new Point(120,30,-170),5).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKd(0.7).setKs(0.4).setKt(0.5).setKt(0.8)));

        //arms
        geometries.add(new Triangle(new Point(-45, -23, -145), new Point(-18, -20, -145),
                new Point(-18, -23, -145))
                .setEmission(new Color(139, 69, 19)) // Brown color
                .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)));


        geometries.add(new Triangle(new Point(45, -23, -145),new Point(18, -20, -145),
                new Point(18, -23, -145))
                .setEmission(new Color(139, 69, 19)) // Brown color
                .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30)));

        // Adding buttons using a loop
        double buttonRadius = 1.5;
        for (int i = 0; i < 6; i++) {
            double yPosition = -50 + i * 8;
            geometries.add(
                    new Sphere(new Point(0, yPosition, -125), buttonRadius)
                            .setEmission(new Color(BLACK))
                            .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30))
            );
        }

        // Adding snowflakes in random positions
        double snowflakeRadius = 1;
        for (int i = 0; i < 30; i++) {
            double x = -100 + Math.random() * 200;
            double y = -100 + Math.random() * 200;
            geometries.add(
                    new Sphere(new Point(x, y, -99), snowflakeRadius)
                            .setEmission(new Color(WHITE))
                            .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30))
            );
        }

        // Adding clouds
        for (int i = 0; i < 15; i++) {
            double x = -50 + Math.random() * 100;
            double y = 30 + Math.random() * 20;
            double z = -150 + Math.random() * 20;
            for (int j = 0; j < 5; j++) {
                double cloudX = x + Math.random() * 10;
                double cloudY = y + Math.random() * 10;
                double cloudZ = z + Math.random() * 10;
                geometries.add(
                        new Sphere(new Point(cloudX, cloudY, cloudZ), 5)
                                .setEmission(new Color(LIGHT_GRAY))
                                .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.5)).setShininess(100).setKr(0).setKt(0))
                );
            }
        }

        // Adding snowdrifts at different heights
        geometries.add(new Sphere(new Point(-100, -80, -180), 10)
                .setEmission(new Color(LIGHT_GRAY))
                .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(150)));

        geometries.add(new Sphere(new Point(-150, -80, -180), 15)
                .setEmission(new Color(LIGHT_GRAY))
                .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(150)));

        geometries.add(new Sphere(new Point(100, -80, -180), 25)
                .setEmission(new Color(LIGHT_GRAY))
                .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(150)));

        return geometries;
    }

    @Test
    public void createSceneWithTenShapes() {
        setupScene();
        List<Intersectable> geometries = createGeometries();

        List<Intersectable> sortedGeometries = scene.sortBoundingBoxesByZ(geometries);

        // Render with BVH
        scene.geometries.add(new BVHNode(sortedGeometries));
        renderImage("minip1_v1 without AA");
    }

    private void renderImage(String imageName) {
        cameraBuilder
                .setVpSize(200, 160)
                .setVpDistance(100)
                .setImageWriter(new ImageWriter(imageName, 800, 600))
                .build()
                .setMultiThreading(true)
                .renderImageWithAntiAliasing(80)
                .writeToImage();
    }
}