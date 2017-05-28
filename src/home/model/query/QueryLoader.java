package home.model.query;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import home.model.level.Level;
import home.utility.BundleLanguageManager;
import home.utility.Bundles;
import home.utility.ResourceManager;

/**
 *
 */
public interface QueryLoader {
/**
 *  This method has to load some queries by specific level and category.
 * @throws IllegalArgumentException
 *  when it can't find any query for that category or level.
 * @param cat 
 *  the query category to load.
 * @param level
 *  the query level to load.
 * @return 
 *      a List of specific queries.
 */
    List<Query> getQueries(Category cat, Level level);
    /**
     * @throws RuntimeException
     *  when something goes wrong reading from the xml file.
     * @return 
     *  a QueryLoader
     */
    static QueryLoader getQueryLoader() {
        final String file = BundleLanguageManager.get().getBundle(Bundles.QUERY).getString("FILE");
        try {
            return new ShuffleQueryLoader(
                    new CachedQueryLoader(
                    new XMLQueryLoader(ResourceManager.load(file).toExternalForm())));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new RuntimeException("Something goes wrong with resource loader");
        }
    }
}
