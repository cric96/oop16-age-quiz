package home.view.world.fx;

import home.controller.WorldController;
import home.model.building.BuildingType;
import home.utility.Utility;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * 
 */
public class FXMLInfoBuildingController extends Parent {
    private WorldController controller;
    private BuildingType building;
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

    @FXML
    void initialize() {
        this.experience.setFont(Utility.getGeneralFont());
        this.level.setFont(Utility.getGeneralFont());
        this.start.setOnMouseClicked(e -> {
            this.controller.createQuiz(building);
            this.start.getScene().getWindow().hide();
        });
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
        this.experience.setText("Experience: " + Integer.valueOf(experience));
    }

    /**
     * set controller world.
     * @param controller 
     * @param building 
     */
    public void setBuildingController(final WorldController controller, final BuildingType building) {
        this.controller = controller;
        this.building = building;
        this.upgrade.setOnMouseClicked(e -> {
            this.controller.createQuiz(building);
            this.upgrade.getScene().getWindow().hide();
        });
    }

    /**
     * set controller world.
     * @param controller 
     */
    public void setBuildingController(final WorldController controller) {
        this.controller = controller;
        this.upgrade.setOnMouseClicked(e -> {
           this.controller.nextEra();
           this.upgrade.getScene().getWindow().hide();
        });
    }
}
