package home.model.query;

import java.util.List;

import home.model.level.Level;

//package-protected
abstract class QueryLoaderDecorator implements QueryLoader {
    private final QueryLoader queryLoader;
    QueryLoaderDecorator(final QueryLoader queryLoader) {
        this.queryLoader = queryLoader;
    }
    @Override
    public List<Query> getQueries(final Category cat, final Level level) {
        return this.queryLoader.getQueries(cat, level);
    }
    protected QueryLoader getDecoratedQueryLoader() {
        return this.queryLoader;
    }

}
