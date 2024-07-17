package renderer;

import geometries.Polygon;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

public class minip1_v3 {
    private final Scene scene = new Scene("Test scene");

    /** Camera builder for the tests with multiple shapes */
    private final Camera.Builder cameraBuilder = Camera.getBuilder().setLocation(new Point(-20,60,100))
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    @Test
    public void createSceneWithTenShapes() {
        //points
        Point A = new Point(-5,5,-80);
        Point B = new Point(5,5,-80);
        Point C = new Point(5,-5,-80);
        Point D = new Point(-5,-5,-80);

        Point E = new Point(-5,5,-90);
        Point F = new Point(5,5,-90);
        Point G = new Point(5,-5,-90);
        Point H = new Point(-5,-5,-90);

        Point I = new Point(20,0,-85);
        Point J = new Point(-20,0,-85);
        Point K = new Point(0,20,-85);
        Point L = new Point(0,-20,-85);


        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        //creating cube
        scene.geometries.add(
                new Polygon(new Point(-50, -50, -100), new Point(0, -30, -150),
                        new Point(0, 50, -150), new Point(-50, 50, -100))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.3)).setShininess(10).setKr(0.2).setKt(0.2)),
                new Polygon(new Point(0, -30, -150), new Point(50, -50, -100),
                        new Point(50, 50, -100), new Point(0, 50, -150))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.3)).setShininess(10).setKr(0.2).setKt(0.2)),
                new Polygon(new Point(50, -50, -100), new Point(0, -30, -150),
                        new Point(-50, -50, -100), new Point(0, -70, -50))
                        .setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(new Double3(0.7)).setKs(new Double3(0.3)).setShininess(10).setKr(0).setKt(0.4))
        );

        scene.geometries.add(
                new Polygon(A,B,C,D).setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0).setKt(1)),
                new Polygon(E,F,G,H).setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(1).setKt(0)),
                new Polygon(C,G,H,D).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.2)),
                new Polygon(B,C,G,F).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.2)),
                new Polygon(A,B,F,E).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.2)),
                new Polygon(D,H,E,A).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.2))
        );

        scene.geometries.add(
                new Triangle(B,C,I).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.7)),
                new Triangle(C,G,I).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.2)),
                new Triangle(G,F,I).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0).setKt(0.2)),
                new Triangle(F,B,I).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.2))
        );

        scene.geometries.add(
                new Triangle(A,D,J).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.7)),
                new Triangle(D,H,J).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.2)),
                new Triangle(H,E,J).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0).setKt(0.2)),
                new Triangle(E,A,J).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.2))
        );

        scene.geometries.add(
                new Triangle(A,B,K).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.7)),
                new Triangle(B,F,K).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.2)),
                new Triangle(F,E,K).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0).setKt(0.2)),
                new Triangle(E,A,K).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.2))
        );

        scene.geometries.add(
                new Triangle(D,C,L).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.7)),
                new Triangle(C,G,L).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.2)),
                new Triangle(G,H,L).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0).setKt(0.2)),
                new Triangle(H,D,L).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10).setKr(0.2).setKt(0.2))
        );

        scene.lights.add(new SpotLight(new Color(550, 200, 200), new Point(100, 100, 0), new Vector(0, 0, -2)) //
                .setKl(4E-5).setKq(2E-7));

        scene.lights.add(new SpotLight(new Color(550, 200, 200), new Point(-100, -100, 0), new Vector(0, 0, -2)) //
                .setKl(4E-5).setKq(2E-7));

        scene.lights.add(new SpotLight(new Color(200, 200, 200), new Point(0, 300, 0), new Vector(0, 0, -2)) //
                .setKl(4E-5).setKq(2E-7));


        // Setting up the camera and rendering the image
        cameraBuilder
                .setVpSize(200, 160)
                .setVpDistance(100)
                .setImageWriter(new ImageWriter("Minip1_V3 with AA", 800, 600))
                .build()
                .renderImageWithAntiAliasing(85)
                .writeToImage();
    }
}
