package renderer;

import geometries.*;
import lighting.*;
import primitives.*;
import scene.Scene;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.*;

public class sss {
    private final Scene scene = new Scene("Test scene");

    /** Camera builder for the tests with multiple shapes */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    void buildSphere(Point p1, double r, Color color) {
        scene.geometries.add(new Sphere(p1,r).setEmission(color)
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(1)));
    }

    @Test
    public void test() {
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene.geometries.add(new Plane(new Point(0, 0, -130), new Vector(0, 0, 1))
                .setEmission(new Color(BLACK))
                .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30).setKr(0.9)));

        Point p1 = new Point(-50,50,-100);
        Point p2 = new Point(50,50,-100);
        Point p3 = new Point(50,-50,-100);
        Point p4 = new Point(-50,-50,-100);

        int numSpheres = 100;

        // Measure time without BVH
        long startTime = System.currentTimeMillis();

        for (int i = 1; i < numSpheres; i++) {
            Color c = new Color(255-i*2,0,0);
            buildSphere(p1,(i/5+i%5),c);
        }

        for (int i = 1; i < numSpheres; i++) {
            Color c = new Color(0,255-i*2,0);
            buildSphere(p2,(i/5+i%5),c);
        }

        for (int i = 1; i < numSpheres; i++) {
            Color c = new Color(0,0,255-i*2);
            buildSphere(p3,(i/5+i%5),c);
        }

        for (int i = 1; i < numSpheres; i++) {
            Color c = new Color(255-i*2,255-i*2,0);
            buildSphere(p4,(i/5+i%5),c);
        }

        scene.lights.add(new SpotLight(new Color(WHITE), new Point(-150, -100, 100), new Vector(-1, 0.5, -2))
                .setKl(0.00001).setKq(0.00005));

        cameraBuilder
                .setVpSize(200, 160)
                .setVpDistance(100)
                .setImageWriter(new ImageWriter("sss_without_bvh", 800, 600))
                .build()
                .setMultiThreading(false)
                .renderImageWithAntiAliasing(30)
                .writeToImage();

        long endTime = System.currentTimeMillis();
        System.out.println("Time without BVH: " + (endTime - startTime) + " ms");

        // Reset the scene and measure time with BVH
        scene.geometries = new Geometries();
        startTime = System.currentTimeMillis();

        List<Intersectable> spheres = new ArrayList<>();

        scene.geometries.add(new Plane(new Point(0, 0, -130), new Vector(0, 0, 1))
                .setEmission(new Color(BLACK))
                .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30).setKr(0.9)));

        for (int i = 1; i < numSpheres; i++) {
            Color c = new Color(255-i*2,0,0);
            spheres.add(new Sphere(p1,(i/5+i%5)).setEmission(c)
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(1)));
        }

        for (int i = 1; i < numSpheres; i++) {
            Color c = new Color(0,255-i*2,0);
            spheres.add(new Sphere(p2,(i/5+i%5)).setEmission(c)
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(1)));
        }

        for (int i = 1; i < numSpheres; i++) {
            Color c = new Color(0,0,255-i*2);
            spheres.add(new Sphere(p3,(i/5+i%5)).setEmission(c)
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(1)));
        }

        for (int i = 1; i < numSpheres; i++) {
            Color c = new Color(255-i*2,255-i*2,0);
            spheres.add(new Sphere(p4,(i/5+i%5)).setEmission(c)
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(1)));
        }

        scene.geometries.add(spheres.toArray(new Intersectable[0]));

        cameraBuilder
                .setVpSize(200, 160)
                .setVpDistance(100)
                .setImageWriter(new ImageWriter("sss_with_bvh", 800, 600))
                .build()
                .setMultiThreading(false)
                .renderImageWithAntiAliasing(30)
                .writeToImage();

        endTime = System.currentTimeMillis();
        System.out.println("Time with BVH: " + (endTime - startTime) + " ms");
    }
}