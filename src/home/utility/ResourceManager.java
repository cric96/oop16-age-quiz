package home.utility;

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
     *  the URL associated with this resource
     */
    public static URL load(final String resourceName) {
        return ResourceManager.class.getResource(resourceName);
    }
}
