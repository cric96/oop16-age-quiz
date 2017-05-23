package home.view.fx.parents;

import home.view.fx.FXMLController;

/**
 * 
 * model a parent that use java fxml.
 */
public interface ParentFXML extends Parent {
    /**
     * @return the fxmlController (used by FXWorldView to set graphic elements)
     */
    FXMLController getFxmlControllerWorld();
}
