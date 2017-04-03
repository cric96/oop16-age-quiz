package home.model.query;

import java.util.List;

import home.model.level.ImmutableLevel;

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
}
