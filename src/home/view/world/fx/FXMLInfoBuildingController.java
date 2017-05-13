package home.view.world.fx;

import java.util.Optional;
import java.util.ResourceBundle;

import home.controller.observer.WorldObserver;
import home.model.building.BuildingType;
import home.utility.Pair;
import home.utility.ResourceManager;
import home.utility.BundleLanguageManager;
import home.utility.Utility;
import home.view.fx.Images;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;

/**
 * 
 */
public class FXMLInfoBuildingController extends Parent {
    private static final int TITLE_FONT = 15;

    @FXML
    private Text name;
    @FXML
    private Label level;
    @FXML
    private Label experience;
    @FXML
    private Button start;
    @FXML
    private Button upgrade;
    @FXML
    private Button closeButton;

    /**
     * @param visible the start to set
     */
    public void setStart(final boolean visible) {
       this.start.setVisible(visible);
    }

    /**
     * @param disabled the upgrade to set
     */
    public void setUpgrade(final boolean disabled) {
        this.upgrade.setDisable(disabled);
    }

    /**
     * 
     */
    @FXML
    public void initialize() {
        final Pair<Integer, Integer> closeButtonDimension = Pair.createPair(20, 20);
        final double dropShadow = 0.4;
        this.closeButton.setBackground(null);
        final DropShadow dropS = new DropShadow(dropShadow, Color.WHITE);
        dropS.setInput(new Glow());
        this.closeButton.setOnMouseEntered(e -> {
            this.closeButton.setEffect(dropS);
        });
        this.closeButton.setOnMouseExited(e -> {
            this.closeButton.setEffect(null);
        });
        final ImageView exitImg = new ImageView(new Image(ResourceManager.load(Images.X_CROSS.getPath()).toExternalForm()));
        exitImg.setFitHeight(closeButtonDimension.getX());
        exitImg.setFitWidth(closeButtonDimension.getY());
        this.closeButton.setGraphic(exitImg);
        final ResourceBundle bundle = BundleLanguageManager.get().getBundle("LabelBundle");
        this.start.setText(bundle.getString("STRQUIZ"));
        this.upgrade.setText(bundle.getString("UPGRADE"));
        this.experience.setFont(Utility.getGeneralFont());
        this.level.setFont(Utility.getGeneralFont());
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name.setText(name);
        this.name.setFont(Utility.getGeneralFont(TITLE_FONT));
    }

    /**
     * @param level the level to set
     */
    public void setLevel(final int level) {
        this.level.setText("Lv. " + Integer.valueOf(level));
    }

    /**
     * @param experience the experience to set
     */
    public void setExperience(final int experience) {
        this.experience.setText(BundleLanguageManager.get()
                                                     .getBundle("LabelBundle")
                                                     .getString("EXP") +  " : " + Integer.valueOf(experience));
    }

    /**
     * set controller world.
     * @param controller 
     * @param building 
     */
    public void setBuildingController(final WorldObserver controller, final Optional<BuildingType> building) {
        this.upgrade.setOnMouseClicked(e -> {
            if (building.equals(Optional.empty())) {
                controller.nextEra();
                this.upgrade.getScene().getWindow().hide();
            } else {
                controller.nextLevel(building.get());
                this.upgrade.getScene().getWindow().hide();
            }
        });
        this.start.setOnMouseClicked(e -> {
            controller.createQuiz(building.get());
            this.start.getScene().getWindow().hide();
        });
    }


    //TO-DO pensa a come migliorare senza passare pop
    /**
     * set Popup to hide.
     * @param pop 
     */
    public void setPop(final Popup pop) {
        this.closeButton.setOnMouseClicked(e -> {
               pop.hide();
        });
    }
}
