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
     * @throws IOException
     *      if the file load gone wrong
     */
    public Parent load() {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FxmlResourceManager.class.getResource(this.path));
        loader.setController(this.fxmlController);
        Parent parent = new Pane();
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

}
