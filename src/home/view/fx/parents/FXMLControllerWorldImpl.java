package home.view.fx.parents;

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
import home.view.fx.buttons.BuildingButton;
import home.view.fx.buttons.WorldButtonFactory;
import home.view.fx.dialogs.DialogParentWorld;
import home.view.fx.dialogs.WorldDialogFactory;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
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
final class FXMLControllerWorldImpl implements FXMLController, FXMLControllerWorld {
    private WorldObserver controller;
    private final Popup pop = new Popup();
    private static final int BACK_WIDTH = 60;
    private static final int BACK_HEIGHT = 50;
    private static final int STATS_BOX = 40;
    private static final int TITLE_FONT = 30;
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

    /* (non-Javadoc)
     * @see home.view.fx.parents.FXMLControllerW#setBuildingPane(java.util.Map, home.model.image.ImageInfo)
     */
    @Override
    public void setBuildingPane(final Map<BuildingType, Pair<ImageInfo, Boolean>> buildings, final ImageInfo kingdom) {
        int actualRow = 0;
        final int maxCol = 2;
        int maxRow = 1;
        int actualCol = 0;
        this.buildingPane.getChildren().clear();
        final BuildingButton kingButton = WorldButtonFactory.createKingdomButton(kingdom.getPath());
        ((Node) kingButton).setOnMouseEntered(e -> {
            this.mousePosition = Pair.createPair(((Node) kingButton).getLayoutX(), ((Node) kingButton).getLayoutY());
            this.controller.pressOnKingdom();
        });
        this.buildingPane.getColumnConstraints().get(0).setHalignment(HPos.CENTER);
        this.buildingPane.add((Node) kingButton, actualCol, actualRow);
        actualCol++;
        for (final Map.Entry<BuildingType, Pair<ImageInfo, Boolean>> building : buildings.entrySet()) {
            final BuildingButton buildButton = WorldButtonFactory.createBuildingButton(building);
            ((Node) buildButton).setOnMouseEntered(e -> {
                this.mousePosition = Pair.createPair(((Node) buildButton).getLayoutX(), ((Node) buildButton).getLayoutY());
                this.controller.pressOnBuilding(building.getKey());
             });
            this.buildingPane.getColumnConstraints().get(actualCol).setHalignment(HPos.CENTER);
            this.buildingPane.add((Node) buildButton, actualCol, actualRow);
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

    /* (non-Javadoc)
     * @see home.view.fx.parents.FXMLControllerW#setExperienceLabel(int)
     */
    @Override
    public void setExperienceLabel(final int experienceLabel) {
        this.experienceLabel.setText(String.valueOf(experienceLabel));
    }

    /* (non-Javadoc)
     * @see home.view.fx.parents.FXMLControllerW#setEraLabel(java.lang.String)
     */
    @Override
    public void setEraLabel(final String eraLabel) {
        this.eraLabel.setText(eraLabel);
    }

    /* (non-Javadoc)
     * @see home.view.fx.parents.FXMLControllerW#setStatsPane(java.util.Map)
     */
    @Override
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
     * if a pup building/kingdom is showing hide it.
     */
    @FXML
    public void backHomePressed() {
        this.pop.hide();
        this.controller.goOnMenu();
    }

    /* (non-Javadoc)
     * @see home.view.fx.parents.FXMLControllerW#setController(home.controller.observer.WorldObserver)
     */
    @Override
    public void setController(final WorldObserver controller) {
        this.controller = controller;
    }

    private void initBuildingStage() {
        this.pop.hide();
        this.pop.setAnchorX(this.mousePosition.getX());
        this.pop.setAnchorY(this.mousePosition.getY());
    }

    /* (non-Javadoc)
     * @see home.view.fx.parents.FXMLControllerW#showBuildingDialog(home.model.building.BuildingType, home.controller.dialog.Dialog)
     */
    @Override
    public void showBuildingDialog(final BuildingType building, final Dialog dialog) {
        if (this.checkAndPreparePopup()) {
            final DialogParentWorld p = WorldDialogFactory.createBuildingParent(controller, building, dialog, this.pop);
            this.popUpShow(p);
        }
    }

    /* (non-Javadoc)
     * @see home.view.fx.parents.FXMLControllerW#showBuildingDialog(home.controller.dialog.Dialog)
     */
    @Override
    public void showBuildingDialog(final Dialog dialog) {
        if (this.checkAndPreparePopup()) {
          final DialogParentWorld p = WorldDialogFactory.createKingdomDialogParent(controller, dialog, this.pop);
          this.popUpShow(p);
        }
    }
    //REFACTOR
    private void popUpShow(final DialogParentWorld dialog) {
        this.pop.getContent().add((Node) dialog);
        this.pop.show(this.buildingPane.getScene().getWindow());
    }
    private boolean checkAndPreparePopup() {
        if ((this.pop.getAnchorX() != this.mousePosition.getX()) || (this.pop.getAnchorY() != this.mousePosition.getY()) || !this.pop.isShowing()) {
            initBuildingStage();
            return true;
        }
        return false;
    }
    /* (non-Javadoc)
     * @see home.view.fx.parents.FXMLControllerW#refresh()
     */
    @Override
    public void refresh() {
        final ResourceBundle bundleButton = BundleLanguageManager.get().getBundle(Bundles.BUTTON);
        final ResourceBundle bundleLabel = BundleLanguageManager.get().getBundle(Bundles.LABEL);
        this.statistics.setText(bundleLabel.getString("STATS") + ":");
        this.experienceText.setText(bundleLabel.getString("EXP") + ":");
        this.backButtonText.setText(bundleButton.getString("BACK"));
    }
}
