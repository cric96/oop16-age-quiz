package home.model.query;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import home.model.level.ImmutableLevel;
import home.utility.ResourceManager;

/**
 *
 */
public interface QueryLoader {
/**
 *  This method has to load some queries by specific level and category.
 * @param cat the query category to load
 * @param level the query level to load
 * @return 
 *      a List of specific queries.
 */
    List<Query> getQueries(Category cat, ImmutableLevel level);
    /**
     * 
     * @return a QueryLoader
     */
    static QueryLoader getQueryLoader() {
        //final File file = new File(.getFile());
        try {
            return new XMLQueryLoader(ResourceManager.load("/queries.xml").toExternalForm());
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new RuntimeException("Something goes wrong with resource loader");
        }
    }
}
