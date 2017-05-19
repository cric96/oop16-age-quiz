package home.view.world.fx;

import java.util.List;

import home.utility.BundleLanguageManager;
import home.utility.Bundles;
import home.utility.Pair;
import home.utility.ResourceManager;
import home.utility.Utility;
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
 * 
 */
public class FXMLInfoBuilding extends Parent {
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


    /**
     * 
     */
    @FXML
    public void initialize() {
        final Pair<Integer, Integer> closeButtonDimension = Pair.createPair(20, 20);
        this.closeButton.getStylesheets().add(ResourceManager.load("/style/gameButtons.css").toExternalForm());
        this.closeButton.getStyleClass().add("generalNode");
        final ImageView exitImg = new ImageView(
                new Image(ResourceManager.load(Images.X_CROSS.getPath()).toExternalForm()));
        exitImg.setFitHeight(closeButtonDimension.getX());
        exitImg.setFitWidth(closeButtonDimension.getY());
        this.closeButton.setGraphic(exitImg);
        this.experience.setFont(Utility.getGeneralFont());
        this.level.setFont(Utility.getGeneralFont());
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name.setText(name);
        this.name.setFont(Utility.getGeneralFont(TITLE_FONT));
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(final int level) {
        this.level.setText("Lv. " + Integer.valueOf(level));
    }

    /**
     * @param experience
     *            the experience to set
     */
    public void setExperience(final int experience) {
        this.experience.setText(BundleLanguageManager.get().getBundle(Bundles.LABEL).getString("EXP") + " : "
                + Integer.valueOf(experience));
    }

    /**
     * set Popup to hide.
     * 
     * @param pop 
     */
    public void setPop(final Popup pop) {
        this.closeButton.setOnMouseClicked(e -> {
            pop.hide();
        });
    }

    /**
     * @param buttons 
     */
    public void setButtonBox(final List<Button> buttons) {
        for (final Button button: buttons) {
            this.buttonBox.getChildren().add(button);
        }
    }

}
