package home.view.world.fx;

import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Map;
import home.controller.WorldController;
import home.controller.dialog.Dialog;
import home.model.building.BuildingType;
import home.model.image.ImageInfo;
import home.utility.Pair;
import home.utility.ResourceManager;
import home.utility.Utility;
import home.view.fx.Images;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * controller for base.fxml file.
 */
public class FXMLControllerWorld {
    private WorldController controller;
    private static final int BACK_WIDTH = 40;
    private static final int BACK_HEIGHT = 30;
    private static final int STATS_BOX = 40;
    private static final int DROP_SHADOW = 10;
    private static final int TITLE_FONT = 20;
    private Pair<Double, Double> mousePosition;
    private static final double DIALOG_OPACITY = 0.9;
    private Stage stageBuilding = new Stage();
    private ImageView statsView;

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
    private GridPane statsPane;
    @FXML
    private Label profileName;
    @FXML
    private GridPane buildingPane;

    /**
     * @param buildings 
     * @param kingdom 
     */
    public void setBuildingPane(final Map<BuildingType, Pair<ImageInfo, Boolean>> buildings, final ImageInfo kingdom) {
        int actualRow = 0;
        final int maxCol = 2;
        int maxRow = 1;
        final int buildingSize = 160;
        int actualCol = 0;

        final Image img = new Image(ResourceManager.load(kingdom.getPath()).toExternalForm());
        final ImageView kingButton = new ImageView(img);
        kingButton.setFitHeight(buildingSize);
        kingButton.setFitWidth(buildingSize);
        kingButton.setOnMouseExited(e -> {
            kingButton.setEffect(null);
        });
        kingButton.setOnMouseEntered(e -> {
            final DropShadow dropS = new DropShadow(DROP_SHADOW, Color.WHITE);
            dropS.setInput(new Glow());
            kingButton.setEffect(dropS);
        });

        this.buildingPane.getChildren().clear();

        kingButton.setOnMouseClicked(e -> {
            this.mousePosition = Pair.createPair(kingButton.getLayoutX(), kingButton.getLayoutY());
            this.controller.pressOnKingdom();
        });
        this.buildingPane.getColumnConstraints().get(0).setHalignment(HPos.CENTER);
        this.buildingPane.add(kingButton, actualCol, actualRow);
        actualCol++;
        for (final Map.Entry<BuildingType, Pair<ImageInfo, Boolean>> building : buildings.entrySet()) {
            final Image buildImg = new Image(ResourceManager.load(building.getValue().getX().getPath()).toExternalForm());
            final ImageView buildButton = new ImageView(buildImg);
            buildButton.setOnMouseClicked(e -> {
                this.mousePosition = Pair.createPair(buildButton.getLayoutX(), buildButton.getLayoutY());
                this.controller.pressOnBuilding(building.getKey());
            });
            buildButton.setFitHeight(buildingSize);
            buildButton.setFitWidth(buildingSize);
            buildButton.setOnMouseExited(e -> {
                buildButton.setEffect(null);
            });
            buildButton.setOnMouseEntered(e -> {
                final DropShadow dropS = new DropShadow(DROP_SHADOW, Color.WHITE);
                dropS.setInput(new Glow());
                buildButton.setEffect(dropS);
            });
            if (!building.getValue().getY()) {
                buildButton.setOpacity(0.5);
            }
            buildButton.setDisable(!building.getValue().getY());
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
        int actualRow = 0;
        final int labelCol = 0;
        final int barCol = 1;
        final int valCol = 2;

        this.statsPane.getChildren().clear();
        for (final Map.Entry<String, Integer> status : statusScose.entrySet()) {
            this.statsPane.add(new Label(status.getKey()), labelCol, actualRow);
            this.statsPane.add(new Label(status.getValue() + "/" + maxValue), valCol, actualRow);
            final Number f = (status.getValue() / Double.valueOf(maxValue));
            this.statsPane.add(new ProgressBar(f.doubleValue()), barCol, actualRow);
            actualRow++;
        }
    }

    @FXML
    private void initialize() {
        titleText.setText(Utility.getTitle());
        titleText.setFont(Utility.titleFont(TITLE_FONT));
        String fileName = ResourceManager.load(Images.BACK_HOME_PICTURE.getPath()).toExternalForm();
        final Image img = new Image(fileName);
        final ImageView profileImage = new ImageView(img);
        profileImage.setFitWidth(BACK_WIDTH);
        profileImage.setFitHeight(BACK_HEIGHT);
        this.backMenuButton.setBackground(null);
        this.backMenuButton.setGraphic(profileImage);
        fileName = ResourceManager.load(Images.STATS_ICON.getPath()).toExternalForm();
        final Image imgStats = new Image(fileName);
        statsView = new ImageView(imgStats);
        exitFromStats();
        statsView.setFitWidth(STATS_BOX);
        statsView.setFitHeight(STATS_BOX);
        this.statsImg.setBackground(null);
        this.statsImg.setGraphic(statsView);
        this.statsPane.setVgap(1);
        stageBuilding = new Stage();
    }

    /**
     * @return the backMenuButton.
     */
    public Button getBackMenuButton() {
        return backMenuButton;
    }

    /**
     * action clicked button home.
     */
    @FXML
    public void backHomePressed() {
        this.controller.goOnMenu();
    }

    /**
     * 
     * @param controller 
     */
    public void setController(final WorldController controller) {
        this.controller = controller;
    }

    @FXML
    private void overInBackButton() {
        final DropShadow dropS = new DropShadow(DROP_SHADOW, Color.WHITE);
        dropS.setInput(new Glow());
        this.backMenuButton.setEffect(dropS);
    }

    @FXML
    private void exitFromBackButton() {
        this.backMenuButton.setEffect(null);
    }

    @FXML
    void overInStats() {
        final DropShadow dropS = new DropShadow(DROP_SHADOW, Color.WHITE);
        dropS.setInput(new Glow());
        this.statsView.setEffect(dropS);
    }

    @FXML
    private void exitFromStats() {
        final DropShadow dropS = new DropShadow(DROP_SHADOW, Color.BLACK);
        dropS.setInput(new Glow());
        this.statsView.setEffect(dropS);
    }
    /**
     * 
     * @param building 
     * @param dialog 
     */
    public void showBuildingDialog(final BuildingType building, final Dialog dialog) {
        stageBuilding = new Stage();
        stageBuilding.setOpacity(DIALOG_OPACITY);
        stageBuilding.setX(mousePosition.getX());
        stageBuilding.setY(mousePosition.getY());
        this.stageBuilding.initModality(Modality.APPLICATION_MODAL);
        stageBuilding.setResizable(false);
        stageBuilding.setScene(new Scene(new ParentDialog(controller, building, dialog)));
        stageBuilding.initOwner(this.buildingPane.getScene().getWindow());
        stageBuilding.showAndWait();
    }

    /**
     * 
     * @param dialog 
     */
    public void showBuildingDialog(final Dialog dialog) {
        stageBuilding = new Stage();
        stageBuilding.setOpacity(DIALOG_OPACITY);
        stageBuilding.setY(mousePosition.getY());
        this.stageBuilding.initModality(Modality.APPLICATION_MODAL);
        stageBuilding.setResizable(false);
        stageBuilding.setScene(new Scene(new ParentDialog(controller, dialog)));
        stageBuilding.initOwner(this.buildingPane.getScene().getWindow());
        stageBuilding.showAndWait();
    }
}
