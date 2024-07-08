package renderer;

import geometries.Intersectable.GeoPoint;
import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * SimpleRayTracer is a basic implementation of the RayTracerBase class.
 * It performs ray tracing by finding intersections of rays with geometries
 * in the scene and calculates the color of the closest intersection point.
 */
public class SimpleRayTracer extends RayTracerBase {

    private static final double DELTA = 0.1;

    /**
     * Constructs a SimpleRayTracer with the specified scene.
     *
     * @param scene the scene to be used for ray tracing
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Traces a ray and calculates the color at the intersection point.
     * If no intersection is found, the background color of the scene is returned.
     *
     * @param ray the ray to be traced
     * @return the color at the intersection point or the background color if no intersection is found
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestGeoPoint = ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
        if (closestGeoPoint == null) {
            return scene.background;
        }
        return calcColor(closestGeoPoint, ray);
    }

    /**
     * Calculates the color at a given GeoPoint.
     *
     * @param geoPoint the GeoPoint
     * @param ray the ray
     * @return the color at the GeoPoint
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        Color ambientLight = scene.ambientLight.getIntensity();
        Color emissionLight = geoPoint.geometry.getEmission();
        Color pointColor = ambientLight.add(emissionLight);
        return pointColor.add(calcLocalEffects(geoPoint, ray));
    }

    /**
     * Calculates the local effects of lighting on a given GeoPoint.
     *
     * @param gp the GeoPoint
     * @param ray the ray
     * @return the color resulting from local lighting effects
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;

        Material material = gp.geometry.getMaterial();
        int nShininess = material.nShininess;
        Double3 kd = material.kD;
        Double3 ks = material.kS;
        Color color = Color.BLACK;

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                if (unshaded(gp, l, n, lightSource)) {
                    Color lightIntensity = lightSource.getIntensity(gp.point);
                    color = color.add(
                            calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity)
                    );
                }
            }
        }
        return color;
    }

    /**
     * Calculates the diffuse component of lighting.
     *
     * @param kD the diffuse coefficient
     * @param l the light direction
     * @param n the normal vector
     * @param lightIntensity the intensity of the light
     * @return the diffuse color
     */
    private Color calcDiffusive(Double3 kD, Vector l, Vector n, Color lightIntensity) {
        double ln = Math.abs(l.dotProduct(n));
        return lightIntensity.scale(kD).scale(ln);
    }

    /**
     * Calculates the specular component of lighting.
     *
     * @param kS the specular coefficient
     * @param l the light direction
     * @param n the normal vector
     * @param v the view direction
     * @param shininess the shininess coefficient
     * @param lightIntensity the intensity of the light
     * @return the specular color
     */
    private Color calcSpecular(Double3 kS, Vector l, Vector n, Vector v, double shininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(2 * l.dotProduct(n))); // reflection direction
        double vr = Math.max(0, -v.dotProduct(r));
        return lightIntensity.scale(kS).scale(Math.pow(vr, shininess));
    }

    private boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource light) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections == null) {
            return true;
        }

        double lightDistance = light.getDistance(gp.point);
        for (GeoPoint geoPoint : intersections) {
            if (alignZero(geoPoint.point.distance(gp.point) - lightDistance) <= 0) {
                return false;
            }
        }
        return true;
    }

}