package home.model.query;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import home.model.level.ImmutableLevel;
import home.model.level.Level;
import home.utility.Utility;

/**
 *
 */
public class XMLQueryLoader implements QueryLoader {
    private static final String CATEGORY = "category";
    private static final String TEXT = "text";
    private final Document doc;
    /**
     * 
     * @param fileName name of the XML file
     * @throws ParserConfigurationException 
     * @throws IOException 
     * @throws SAXException 
     */
    public XMLQueryLoader(final File fileName) throws SAXException, IOException, ParserConfigurationException {
        Objects.requireNonNull(fileName);
        doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
    }
    private NodeList getLevel(final ImmutableLevel level, final NodeList list) {
        return null;
    }
    private NodeList getCategory(final Category cat, final NodeList list) {
        return null;
    }
    @Override
    public List<Query> getQueries(final Category cat, final ImmutableLevel level) {
        if (Utility.checkNullOb(cat, level)) {
            throw new IllegalArgumentException("You need to specify valid arguments");
        }
        NodeList categories = doc.getElementsByTagName(CATEGORY);
        NodeList queries = this.getLevel(level, this.getCategory(cat, doc.getElementsByTagName(CATEGORY)));
        Query.Builder qBuilder = Query.Builder.createBuilder();
        for (int i = 0; i < queries.getLength(); i++) {
            Node query = queries.item(i);
            for (int j = 0; j < query.getChildNodes().getLength(); j++) {
                Node elem = query.getChildNodes().item(j);
                if (elem.getNodeType() == Node.ELEMENT_NODE) {
                    if (elem.getNodeName().equals(TEXT)) {
                        qBuilder.addQuestion(elem.getNodeValue());
                    } else {
                        if (elem.hasAttributes()) {
                        qBuilder.addCorrectAnswer(elem.getNodeValue());
                        }
                        qBuilder.addAnswer(elem.getNodeValue());
                    }
                }
            }
        }
        return null;
    }
}
