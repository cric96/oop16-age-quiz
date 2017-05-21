package home.view.world.fx;

import java.util.List;

import home.utility.BundleLanguageManager;
import home.utility.Bundles;
import home.utility.Pair;
import home.utility.ResourceManager;
import home.utility.view.FontManager;
import home.view.fx.FXMLController;
import home.view.fx.Images;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;

/**
 * controller of fxml view that represent dialog building/kingdom
 */
final class FXMLInfoBuilding extends Parent implements FXMLController {
    private static final int TITLE_FONT = 15;

    @FXML
    private Text name;
    @FXML
    private Label level;
    @FXML
    private Label experience;
    @FXML
    private HBox buttonBox;
    @FXML
    private Button closeButton;

    @FXML
    private void initialize() { //NOPMD - private metod called by itself when fxml file is load.
        final Pair<Integer, Integer> closeButtonDimension = Pair.createPair(20, 20);
        this.closeButton.getStylesheets().add(ResourceManager.load("/style/gameButtons.css").toExternalForm());
        this.closeButton.getStyleClass().add("generalNode");
        final ImageView exitImg = new ImageView(
                new Image(ResourceManager.load(Images.X_CROSS.getPath()).toExternalForm()));
        exitImg.setFitHeight(closeButtonDimension.getX());
        exitImg.setFitWidth(closeButtonDimension.getY());
        this.closeButton.setGraphic(exitImg);
        this.experience.setFont(FontManager.getGeneralFont());
        this.level.setFont(FontManager.getGeneralFont());
    }

    /**
     * name of the building represented by this scene.
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name.setText(name);
        this.name.setFont(FontManager.getGeneralFont(TITLE_FONT));
    }

    /**
     * set the level of the building represented by this scene.
     * @param level
     *            the level to set
     */
    public void setLevel(final int level) {
        this.level.setText("Lv. " + Integer.valueOf(level));
    }

    /**
     * set the value of necessary ecperience to upgrade the building/kingdom represented by this scene.
     * @param experience
     *            the experience to set
     */
    public void setExperience(final int experience) {
        this.experience.setText(BundleLanguageManager.get().getBundle(Bundles.LABEL).getString("EXP") + " : "
                + Integer.valueOf(experience));
    }

    /**
     * set the action when close button in this parent is clicked.
     * @param pop 
     *          to hide
     */
    public void setPop(final Popup pop) {
        this.closeButton.setOnMouseClicked(e -> {
            pop.hide();
        });
    }

    /**
     * add a list of button in this parent.
     * @param buttons 
     *          set of buttons in the dialog
     */
    public void setButtonBox(final List<Button> buttons) {
        for (final Button button: buttons) {
            this.buttonBox.getChildren().add(button);
        }
    }

}
