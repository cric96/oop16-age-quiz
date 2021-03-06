package home.model.query;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import home.model.level.Level;
import home.utility.BundleLanguageManager;
import home.utility.Pair;

//package-protected
final class CachedQueryLoader extends QueryLoaderDecorator {
    private static final int MINUTES_TO_REFRESH = 10;
    private static final long SIZE = 10_000;
    private static Optional<Locale> lastLanguage = Optional.empty();
    private static final Cache<Pair<Category, Integer>, List<Query>> CACHE = Caffeine.newBuilder()
            .maximumSize(SIZE)
            .expireAfterWrite(MINUTES_TO_REFRESH, TimeUnit.MINUTES)
            .build();
    CachedQueryLoader(final QueryLoader queryLoader) {
        super(queryLoader);
    }
    @Override
    public List<Query> getQueries(final Category cat, final Level level) {
        if (!lastLanguage.isPresent()) {
            lastLanguage = Optional.of(BundleLanguageManager.get().getCurrentLocale());
        } else if (lastLanguage.get() != BundleLanguageManager.get().getCurrentLocale()) {
            CACHE.asMap().clear();
            lastLanguage = Optional.of(BundleLanguageManager.get().getCurrentLocale());
        }
        final Pair<Category, Integer> key = Pair.createPair(cat, level.getIncrementalLevel()); 
        if (!CACHE.asMap().containsKey(key)) {
            CACHE.put(key, super.getQueries(cat, level));
        }
        return CACHE.asMap().get(key);
    }

}
