package home.view.fx;

import java.io.IOException;

import home.view.fx.parent.FXMLController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * class used to load an fxml file.
 */
public final class FxmlResourceManager {
    private static final String ERROR = "ERROR DURING THE FXML LOADING";
    private final String path;
    private final FXMLController fxmlController;

    /**
     * 
     * @param path
     *          of fxml to loas
     * @param fxmlController
     *          the controller of this fxml file.
     */
    public FxmlResourceManager(final String path, final FXMLController fxmlController) {
        this.path = path;
        this.fxmlController = fxmlController;
    }

    /**
     * to load the file.
     * @return 
     *      the loaded parent
     */
    public Parent load() {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FxmlResourceManager.class.getResource(this.path));
        loader.setController(this.fxmlController);
        Parent parent = new Pane();
        try {
            parent = loader.load();
        } catch (IOException e) {
            System.err.println(ERROR + " Exception : " + e.getMessage());
        }
        return parent;
    }

}
