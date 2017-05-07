package home.model.query;

import java.util.Collections;
import java.util.List;

import home.model.level.ImmutableLevel;
/*A decoration that allows to shuffle and randomize questions from file
 */
//package-protected
final class ShuffleQueryLoader extends QueryLoaderDecorator {

    ShuffleQueryLoader(final QueryLoader queryLoader) {
        super(queryLoader);
    }
    @Override
    public List<Query> getQueries(final Category cat, final ImmutableLevel lev) {
        final List<Query> list = super.getQueries(cat, lev);
        Collections.shuffle(list);
        return list;
    }

}
