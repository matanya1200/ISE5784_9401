package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.*;
import primitives.*;
import scene.Scene;

import java.util.List;
import java.util.Random;

import static java.lang.Math.*;
import static primitives.Util.alignZero;

/**
 * SimpleRayTracer class extends RayTracerBase and implements a simple ray tracing algorithm.
 */
public class SimpleRayTracer extends RayTracerBase {

    private static final Double3 INITIAL_K = Double3.ONE;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final int NUM_SAMPLES = 4;

    /**
     * Constructs a SimpleRayTracer with the given scene.
     *
     * @param scene the scene to be rendered
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /**
     * Calculates the color at the intersection point.
     *
     * @param geopoint the intersection point
     * @param ray the ray that intersects the geometry
     * @return the color at the intersection point
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    /**
     * Calculates the color at the intersection point, taking into account recursive reflections and refractions.
     *
     * @param geopoint the intersection point
     * @param ray the ray that intersects the geometry
     * @param level the recursion level
     * @param k the attenuation factor
     * @return the color at the intersection point
     */
    private Color calcColor(GeoPoint geopoint, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(geopoint, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(geopoint, ray, level, k));
    }

    /**
     * Constructs the reflected ray.
     *
     * @param gp the intersection point
     * @param v the direction vector of the ray
     * @param n the normal vector at the intersection point
     * @return the reflected ray
     */
    private Ray constructReflectedRay(GeoPoint gp, Vector v, Vector n) {
        double nv = v.dotProduct(n);
        if(nv == 0)
            return null;
        Vector r = v.subtract(n.scale(2 * nv));
        return new Ray(gp.point, r, n);
    }

    /**
     * Constructs the refracted ray.
     *
     * @param gp the intersection point
     * @param v the direction vector of the ray
     * @param n the normal vector at the intersection point
     * @return the refracted ray
     */
    private Ray constructRefractedRay(GeoPoint gp, Vector v, Vector n) {
        return new Ray(gp.point, v, n); // Use the new constructor
    }

    /**
     * Calculates the global effects (reflections and refractions) at the intersection point.
     *
     * @param gp the intersection point
     * @param ray the ray that intersects the geometry
     * @param level the recursion level
     * @param k the attenuation factor
     * @return the color contribution from global effects
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Material material = gp.geometry.getMaterial();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        return calcGlobalEffect(constructRefractedRay(gp, v, n), material.kT, level, k)
                .add(calcGlobalEffect(constructReflectedRay(gp, v, n), material.kR, level, k));
    }

    /**
     * Calculates the global effect (reflection or refraction) for a given ray.
     *
     * @param ray the ray (reflected or refracted)
     * @param kx the attenuation factor for the effect
     * @param level the recursion level
     * @param k the accumulated attenuation factor
     * @return the color contribution from the global effect
     */
    private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        return gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx);
    }

    /**
     * Finds the closest intersection point of a ray with the geometries in the scene.
     *
     * @param ray the ray to be traced
     * @return the closest intersection point, or null if no intersection is found
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        return ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
    }

    /**
     * Calculates the local effects (diffusive and specular) at the intersection point.
     *
     * @param geopoint the intersection point
     * @param ray the ray that intersects the geometry
     * @param k the attenuation factor
     * @return the color contribution from local effects
     */
    private Color calcLocalEffects(GeoPoint geopoint, Ray ray, Double3 k) {
        Vector v = ray.getDir();
        Vector n = geopoint.geometry.getNormal(geopoint.point);
        double nv = alignZero(n.dotProduct(v));
        Color color = geopoint.geometry.getEmission();
        if (nv == 0) return color;

        Material material = geopoint.geometry.getMaterial();

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geopoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Double3 transparency = transparency(geopoint, lightSource, l, n);
                if (transparency.product(k).greaterThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(geopoint.point).scale(transparency);
                    color = color.add(lightIntensity.scale(
                            calcDiffusive(material,nl).add(calcSpecular(material,n,l,nl,v))
                    ));
                }
            }
        }
        return color;
    }

    /**
     * Calculates the transparency factor at the intersection point.
     *
     * @param gp the intersection point
     * @param ls the light source
     * @param l the direction vector to the light source
     * @param n the normal vector at the intersection point
     * @return the transparency factor
     */
    private Double3 transparency(GeoPoint gp, LightSource ls, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n); // Use the new constructor

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections == null) {
            return Double3.ONE;
        }

        Double3 ktr = Double3.ONE;
        double lightDistance = ls.getDistance(gp.point);
        for (GeoPoint intersection : intersections) {
            if (alignZero(intersection.point.distance(gp.point) - lightDistance) <= 0) {
                ktr = ktr.product(intersection.geometry.getMaterial().kT);
                if (ktr.equals(Double3.ZERO)) {
                    break;
                }
            }
        }

        return ktr;
    }

    /**
     * Calculates the diffusive component of the color at the intersection point.
     *
     * @param material the material of the intersected geometry
     * @param nl the dot product of the normal vector and the light direction vector
     * @return the diffusive color component
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(abs(nl));
    }

    /**
     * Calculates the specular component of the color at the intersection point.
     *
     * @param material the material of the intersected geometry
     * @param n the normal vector at the intersection point
     * @param l the direction vector to the light source
     * @param nl the dot product of the normal vector and the light direction vector
     * @param v the direction vector of the ray
     * @return the specular color component
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector reflectVector = l.subtract(n.scale(nl * 2));
        double minusVR = -alignZero(v.dotProduct(reflectVector));
        return minusVR <= 0 ? Double3.ZERO : material.kS.scale(pow(minusVR, material.nShininess));
    }

    /**
     * Calculates the color of a pixel using anti-aliasing by shooting multiple rays per pixel and averaging the colors.
     *
     * @param centerRay the center ray of the pixel
     * @return the averaged color of the pixel
     */
    public Color calcColorWithAntiAliasing(Ray centerRay) {
        Color finalColor = Color.BLACK;
        Random rand = new Random();
        for (int i = 0; i < NUM_SAMPLES; i++) {
            // Generate random offset for the ray
            double offsetX = rand.nextDouble() - 0.5;
            double offsetY = rand.nextDouble() - 0.5;
            // Create a new ray with the offset
            Ray offsetRay = centerRay.createWithOffset(offsetX, offsetY);
            // Trace the offset ray and add the color to the final color
            finalColor = finalColor.add(traceRay(offsetRay));
        }
        // Average the color by the number of samples
        return finalColor.reduce(NUM_SAMPLES);
    }
}

