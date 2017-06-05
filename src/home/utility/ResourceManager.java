package home.utility;

import java.io.InputStream;
import java.net.URL;

/**
 * an utility class used to load resource.
 */
public final class ResourceManager {
    private ResourceManager() { }
    /**
     * load a resource with the filename specified.
     * @param resourceName
     *  the name of resource
     * @return
     *  the URL associated to this resource
     */
    public static URL load(final String resourceName) {
        return ResourceManager.class.getResource(resourceName);
    }
    /**
     * load a resource with the filename specified as a stream.
     * @param resourceName
     *  the resource name
     * @return
     *  the InputStream associated to this resource
     */
    public static InputStream loadAsStream(final String resourceName) {
        return ResourceManager.class.getResourceAsStream(resourceName);
    }
}
