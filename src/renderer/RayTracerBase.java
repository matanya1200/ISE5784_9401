package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracerBase {

    protected Scene scene;

    /**
     * Constructor that accepts a scene.
     * @param scene the scene to trace rays in.
     */
    RayTracerBase(Scene scene){
        this.scene = scene;
    }

    /**
     * Trace a ray and return the color of the closest intersection.
     * @param ray the ray to trace.
     * @return the color at the closest intersection.
     */
    public abstract Color traceRay(Ray ray);
}
