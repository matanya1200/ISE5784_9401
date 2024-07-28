package renderer;

import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

public class minip1_v4 {
    private final Scene scene = new Scene("Test scene");

    /** Camera builder for the tests with multiple shapes */
    private final Camera.Builder cameraBuilder = Camera.getBuilder().setLocation(new Point(-400,0,20))
            .setDirection(new Vector(1, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    void buildtriangle(Point p1, Point p2, Point p3, Color color) {
        scene.geometries.add(new Triangle(p1, p2, p3).setEmission(color)
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(1).setKt(0.4)));
    }

    @Test
    public void test() {

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene.geometries.add(new Plane(new Point(0,0,-400),new Vector(0,0,1)).setEmission(new Color(black))
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKr(0.9).setKt(0.5)));

        Point A = new Point(0, -50, -200);
        Point B = new Point(25, 0, -200);
        Point C = new Point(0, 0, -225);
        Point D = new Point(-25, 0, -200);
        Point E = new Point(0, 0, -175);

        Point B1 = new Point(12.5,2,-212.5);
        Point B2 = new Point(12.5,-2,-212.5);
        Point C1 = new Point(-12.5,2,-212.5);
        Point C2 = new Point(-12.5,-2,-212.5);
        Point D1 = new Point(-12.5,2,-187.5);
        Point D2 = new Point(-12.5,-2,-187.5);
        Point E1 = new Point(12.5,2,-187.5);
        Point E2 = new Point(12.5,-2,-187.5);

        Point F = new Point(-12.5,10,-200);
        Point G = new Point(0,10,-212.5);
        Point H = new Point(12.5,10,-200);
        Point I = new Point(0,10,-187.5);


        scene.geometries.add(new Sphere(new Point(250,250,-300),100)
                .setEmission(new Color(BLUE))
                .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30).setKt(1)));
        scene.geometries.add(new Polygon(new Point(-80,0,-200),new Point(-75,-5,-200),new Point(-70,0,-200),new Point(-75,5,-200))
                .setEmission(new Color(GREEN))
                .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setShininess(30).setKt(1)));


        scene.geometries.add(new Polygon(B, B1, C, B2).setEmission(new Color(BLUE))
                .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0).setKt(1)));
        scene.geometries.add(new Polygon(C, C1, D, C2).setEmission(new Color(BLUE))
                .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0).setKt(1)));
        scene.geometries.add(new Polygon(D, D1, E, D2).setEmission(new Color(BLUE))
                .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0).setKt(1)));
        scene.geometries.add(new Polygon(E, E1, B, E2).setEmission(new Color(BLUE))
                .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0).setKt(1)));

        scene.geometries.add(new Polygon(F,G,H,I).setEmission(new Color(BLUE))
                .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0).setKt(1)));

        //base
        buildtriangle(A, B, B2, new Color(BLUE));
        buildtriangle(A, B2, C, new Color(BLUE));
        buildtriangle(A, C, C2, new Color(BLUE));
        buildtriangle(A, C2, D, new Color(BLUE));
        buildtriangle(A, D, D2, new Color(BLUE));
        buildtriangle(A, D2, E, new Color(BLUE));
        buildtriangle(A, E, E2, new Color(BLUE));
        buildtriangle(A, E2, B, new Color(BLUE));

        //upper
        buildtriangle(B,H,B1, new Color(BLUE));
        buildtriangle(H,B1,G, new Color(BLUE));
        buildtriangle(B1,G,C, new Color(BLUE));
        buildtriangle(C,G,C1, new Color(BLUE));
        buildtriangle(G,C1,F, new Color(BLUE));
        buildtriangle(C1,F,D, new Color(BLUE));
        buildtriangle(D,F,D1, new Color(BLUE));
        buildtriangle(F,D1,I, new Color(BLUE));
        buildtriangle(D1,I,E, new Color(BLUE));
        buildtriangle(E,I,E1, new Color(BLUE));
        buildtriangle(I,E1,H, new Color(BLUE));
        buildtriangle(E1,H,B, new Color(BLUE));


        scene.lights.add(new SpotLight(new Color(WHITE), new Point(-150, -100, 100), new Vector(-1, 0.5, -2))
                .setKl(0.000001).setKq(0.000005));

        cameraBuilder
                .setVpSize(200, 160)
                .setVpDistance(100)
                .setImageWriter(new ImageWriter("minip1_v4 without AA", 800, 600))
                .build()
                .setMultiThreading(true)
                .renderImage()
                .writeToImage();
    }
}
