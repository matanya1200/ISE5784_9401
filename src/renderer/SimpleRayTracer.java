package renderer;

import geometries.Intersectable.GeoPoint;
import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * SimpleRayTracer is a basic implementation of the RayTracerBase class.
 * It performs ray tracing by finding intersections of rays with geometries
 * in the scene and calculates the color of the closest intersection point.
 */
public class SimpleRayTracer extends RayTracerBase {

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
        Color color = geoPoint.geometry.getEmission()
                .add(scene.ambientLight.getIntensity());
        color = color.add(calcLocalEffects(geoPoint, ray));
        return color;
    }

    /**
     * Calculates the local effects of lighting on a given GeoPoint.
     *
     * @param geoPoint the GeoPoint
     * @param ray the ray
     * @return the color resulting from local lighting effects
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Vector v = ray.getDir().normalize();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point).normalize();
        Material material = geoPoint.geometry.getMaterial();
        Color color = Color.BLACK;

        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(geoPoint.point).normalize();
            if (n.dotProduct(l) * n.dotProduct(v) > 0) { // sign of dotProduct is positive when both are above or below surface
                Color lightIntensity = lightSource.getIntensity(geoPoint.point);
                color = color.add(calcDiffusive(material.kD, l, n, lightIntensity),
                        calcSpecular(material.kS, l, n, v, material.nShininess, lightIntensity));
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

}