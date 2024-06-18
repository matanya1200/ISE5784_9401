package renderer;

import primitives.*;
import scene.Scene;

/**
 * SimpleRayTracer is a basic implementation of the RayTracerBase class.
 * It performs ray tracing by finding intersections of rays with geometries
 * in the scene and calculates the color of the closest intersection point.
 */
public class SimpleRayTracer extends RayTracerBase{

    /**
     * Constructs a SimpleRayTracer with the specified scene.
     *
     * @param scene the scene to be used for ray tracing
     */
    SimpleRayTracer(Scene scene){
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
        Point point = ray.findClosestPoint(scene.geometries.findIntersections(ray));

        if(point == null){
            return scene.background;
        }

        return calcColor(point);
    }

    /**
     * Calculates the color at a given point.
     * In this basic implementation, it returns the intensity of the ambient light in the scene.
     *
     * @param point the point for which the color is to be calculated
     * @return the color at the given point
     */
    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }

}
