package home.model.query;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import home.model.level.ImmutableLevel;
import home.utility.Utility;

//package-protected
class XMLQueryLoader implements QueryLoader {
    private static final String CAT_NAME = "category";
    private static final String TEXT = "text";
    private static final String CAT_ATTRIBUTE = "name";
    private static final String LEVEL_ATTRIBUTE = "number";
    private final Document doc;
    XMLQueryLoader(final String fileName) throws SAXException, IOException, ParserConfigurationException {
        Objects.requireNonNull(fileName);
        doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
        doc.getDocumentElement().normalize();
    }
    private NodeList getSubList(final String attrValue, final String attrName, final NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            final Node elem = list.item(i);
            if (elem.getNodeType() == Node.ELEMENT_NODE 
                    && elem.getAttributes().getNamedItem(attrName).getNodeValue().equals(attrValue)) {
                return elem.getChildNodes();
            }
        }
        throw new IllegalArgumentException("Can't find NodeList");
    }
    @Override
    public List<Query> getQueries(final Category cat, final ImmutableLevel level) {
        if (Utility.checkNullOb(cat, level)) {
            throw new IllegalArgumentException("You need to specify valid arguments");
        }
        //ottengo l'albero delle domande d'interesse con radice "level"
        final NodeList categories = this.getSubList(cat.name(), CAT_ATTRIBUTE, doc.getElementsByTagName(CAT_NAME));
        final NodeList queries = this.getSubList(String.valueOf(level.getIncrementalLevel()), LEVEL_ATTRIBUTE, categories);
        //creo la lista di domande da restituire
        final List<Query> queryList = new ArrayList<>();
        for (int i = 0; i < queries.getLength(); i++) {
            final Query.Builder qBuilder = Query.Builder.createBuilder();
            qBuilder.addCategory(cat);
            qBuilder.addDifficulty(level.getIncrementalLevel());
            final Node query = queries.item(i);
            if (query.getNodeType() == Node.ELEMENT_NODE) {
                for (int j = 0; j < query.getChildNodes().getLength(); j++) { 
                    final Node elem = query.getChildNodes().item(j);
                    if (elem.getNodeType() == Node.ELEMENT_NODE) {
                        if (elem.getNodeName().equals(TEXT)) {
                            qBuilder.addQuestion(elem.getTextContent());
                        } else {
                            qBuilder.addAnswer(elem.getTextContent());
                            if (elem.hasAttributes()) {
                                qBuilder.addCorrectAnswer(elem.getTextContent());
                            }
                        }
                    }
                }
                queryList.add(qBuilder.build());
            }
        }
        return queryList;
    }
}
