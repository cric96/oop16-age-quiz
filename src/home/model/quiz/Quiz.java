package home.model.quiz;

import java.util.Iterator;

import home.model.query.Query;

/**
 *Quiz allows to iterate all the queries this game contains.
 */
public interface Quiz extends Iterator<Query> { }
