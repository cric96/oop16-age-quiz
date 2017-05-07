package home.model.query;

//package-protected
final class QueryFile {
    private static final String ITALIAN_FILE = "/queries.xml";

    private QueryFile() { }
    public static String getPath() {
        return ITALIAN_FILE;
    }
}
