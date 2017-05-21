package home.view.world.fx;

import java.util.Map;
import java.util.ResourceBundle;

import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
import home.model.building.BuildingType;
import home.model.image.ImageInfo;
import home.utility.BundleLanguageManager;
import home.utility.Bundles;
import home.utility.Pair;
import home.utility.ResourceManager;
import home.utility.Utility;
import home.utility.view.FontManager;
import home.view.fx.CSSManager;
import home.view.fx.FXMLController;
import home.view.fx.Images;
import home.view.fx.StyleSheet;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;

/**
 * controller for base.fxml file.
 */
final class FXMLControllerWorld implements FXMLController {
    private WorldObserver controller;
    private final Popup pop = new Popup();
    private static final int BACK_WIDTH = 60;
    private static final int BACK_HEIGHT = 50;
    private static final int STATS_BOX = 40;
    private static final int TITLE_FONT = 25;
    private Pair<Double, Double> mousePosition;

    @FXML
    private Label statistics;
    @FXML
    private Label experienceLabel;
    @FXML
    private Label eraLabel;
    @FXML
    private Button backMenuButton;
    @FXML
    private Text titleText;
    @FXML
    private Button statsImg;
    @FXML
    private GridPane statisticsPane;
    @FXML
    private Label profileName;
    @FXML
    private GridPane buildingPane;
    @FXML
    private Label experienceText;
    @FXML
    private Tooltip backButtonText;

    @FXML
    private void initialize() { //NOPMD - private metod called by itself when fxml file is load.
        CSSManager.addStyleSheet(StyleSheet.GAME_BUTTONS, this.backMenuButton);
        CSSManager.addStyleClass("generalNode", this.backMenuButton);
        CSSManager.addStyleSheet(StyleSheet.GAME_BUTTONS, this.statsImg);
        CSSManager.addStyleClass("statsImage", this.statsImg);
        titleText.setText(Utility.getTitle());
        titleText.setFont(FontManager.titleFont(TITLE_FONT));
        String fileName = ResourceManager.load(Images.BACK_HOME_PICTURE.getPath()).toExternalForm();
        final ImageView backButton = new ImageView(new Image(fileName));
        backButton.setFitWidth(BACK_WIDTH);
        backButton.setFitHeight(BACK_HEIGHT);
        this.backMenuButton.setGraphic(backButton);
        fileName = ResourceManager.load(Images.STATS_ICON.getPath()).toExternalForm();
        final ImageView statsView = new ImageView(new Image(fileName));
        statsView.setFitWidth(STATS_BOX);
        statsView.setFitHeight(STATS_BOX);
        this.statsImg.setGraphic(statsView);
        this.statisticsPane.setVgap(1);
        this.statistics.setFont(FontManager.getGeneralFont(16));
        this.backButtonText.setFont(FontManager.getGeneralFont());
        this.eraLabel.setFont(FontManager.getGeneralFont(TITLE_FONT));
        this.experienceLabel.setFont(FontManager.getGeneralFont(TITLE_FONT));
        this.experienceText.setFont(FontManager.getGeneralFont(TITLE_FONT));
    }

    /**
     * @param buildings 
     * @param kingdom 
     */
    public void setBuildingPane(final Map<BuildingType, Pair<ImageInfo, Boolean>> buildings, final ImageInfo kingdom) {
        int actualRow = 0;
        final int maxCol = 2;
        int maxRow = 1;
        int actualCol = 0;
        this.buildingPane.getChildren().clear();
        final BuildingButton kingButton = new BuildingButtonKingdom(kingdom.getPath());
        kingButton.setOnMouseEntered(e -> {
            this.mousePosition = Pair.createPair(kingButton.getLayoutX(), kingButton.getLayoutY());
            this.controller.pressOnKingdom();
        });
        this.buildingPane.getColumnConstraints().get(0).setHalignment(HPos.CENTER);
        this.buildingPane.add(kingButton, actualCol, actualRow);
        actualCol++;
        for (final Map.Entry<BuildingType, Pair<ImageInfo, Boolean>> building : buildings.entrySet()) {
            final BuildingButton buildButton = new BuildingButtonGeneral(building);
            buildButton.setOnMouseEntered(e -> {
                this.mousePosition = Pair.createPair(buildButton.getLayoutX(), buildButton.getLayoutY());
                this.controller.pressOnBuilding(building.getKey());
             });
            this.buildingPane.getColumnConstraints().get(actualCol).setHalignment(HPos.CENTER);
            this.buildingPane.add(buildButton, actualCol, actualRow);
            if (actualCol == maxCol) {
                if (maxRow == actualRow) {
                    this.buildingPane.addRow(++maxRow);
                }
                actualCol = 0;
                actualRow++;
            } else {
                actualCol++;
            }
        }
    }

    /**
     * @param profileName
     *            the profileName to set
     */
    public void setProfileName(final String profileName) {
        this.profileName.setText(profileName);
    }

    /**
     * @param experienceLabel
     *            the experienceLabel to set
     */
    public void setExperienceLabel(final int experienceLabel) {
        this.experienceLabel.setText(String.valueOf(experienceLabel));
    }

    /**
     * @param eraLabel
     *            the eraLabel to set
     */
    public void setEraLabel(final String eraLabel) {
        this.eraLabel.setText(eraLabel);
    }

    /**
     * @param statusScose
     *            Map of status
     */
    public void setStatsPane(final Map<String, Integer> statusScose) {
        final int maxValue = 100;
        final int labelCol = 0;
        final int barCol = 1;
        final int valCol = 2;
        int actualRow = 0;
        this.statisticsPane.getChildren().clear();
        for (final Map.Entry<String, Integer> status : statusScose.entrySet()) {
            final Label name = new Label(status.getKey());
            name.setFont(FontManager.getGeneralFont());
            final Label value = new Label(status.getValue() + "/" + maxValue);
            value.setFont(FontManager.getGeneralFont());
            this.statisticsPane.add(name, labelCol, actualRow);
            this.statisticsPane.add(value, valCol, actualRow);
            final Number f = (status.getValue() / Double.valueOf(maxValue));
            this.statisticsPane.add(new ProgressBar(f.doubleValue()), barCol, actualRow);
            actualRow++;
        }
    }

    /**
     * action clicked button home.
     */
    @FXML
    public void backHomePressed() {
        this.pop.hide();
        this.controller.goOnMenu();
    }

    /**
     * 
     * @param controller 
     */
    public void setController(final WorldObserver controller) {
        this.controller = controller;
    }

    private void initBuildingStage() {
        this.pop.hide();
        this.pop.setAnchorX(this.mousePosition.getX());
        this.pop.setAnchorY(this.mousePosition.getY());
    }

    /**
     * 
     * @param building 
     * @param dialog 
     */
    public void showBuildingDialog(final BuildingType building, final Dialog dialog) {
        if (this.checkAndPreparePopup()) {
            final DialogParent p = new BuildingDialogParent(controller, building, dialog, this.pop);
            this.popUpShow(p);
        }
    }

    /**
     * 
     * @param dialog 
     */
    public void showBuildingDialog(final Dialog dialog) {
        if (this.checkAndPreparePopup()) {
          final DialogParent p = new KingdomDialogParent(this.controller, dialog, this.pop);
          this.popUpShow(p);
        }
    }
    //REFACTOR
    private void popUpShow(final DialogParent dialog) {
        this.pop.getContent().add(dialog);
        this.pop.show(this.buildingPane.getScene().getWindow());
    }
    private boolean checkAndPreparePopup() {
        if ((this.pop.getAnchorX() != this.mousePosition.getX()) || (this.pop.getAnchorY() != this.mousePosition.getY()) || !this.pop.isShowing()) {
            initBuildingStage();
            return true;
        }
        return false;
    }
    /**
     * method used to refresh label if I change language game.
     */
    public void refresh() {
        final ResourceBundle bundleButton = BundleLanguageManager.get().getBundle(Bundles.BUTTON);
        final ResourceBundle bundleLabel = BundleLanguageManager.get().getBundle(Bundles.LABEL);
        this.statistics.setText(bundleLabel.getString("STATS") + ":");
        this.experienceText.setText(bundleLabel.getString("EXP") + ":");
        this.backButtonText.setText(bundleButton.getString("BACK"));
    }
}
