package home.view.fx;

import home.utility.ResourceManager;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * a utility class used to manage.
 */
public final class CSSManager {
    /**
     * add a style sheet to a parent node, and remove the stylesheet attached.
     * @param sheet
     *  the sheet to attach on the node
     * @param node
     *  the node where you put the stylesheet
     */
   public static void addStyleSheet(final StyleSheet sheet, final Parent node) {
      final String sheetFile = ResourceManager.load(sheet.toString()).toExternalForm();
      node.getStylesheets().clear();
      node.getStylesheets().add(sheetFile);
   }
   /**
    * add a style class into a node and remove the class attached.
    * @param styleClass
    *   the name of the class
    * @param node
    *   the node where you add the class
    */
   public static void addStyleClass(final String styleClass, final Node node) {
       node.getStyleClass().clear();
       node.getStyleClass().add(styleClass);
   }
   private CSSManager() { } 
}
