package home.view.world.fx;

import java.util.Map;
import java.util.Optional;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;

/**
 * controller for base.fxml file.
 */
public class FXMLControllerWorld {
    private WorldController controller;
    private final Popup pop = new Popup();
    private static final int BACK_WIDTH = 60;
    private static final int BACK_HEIGHT = 50;
    private static final int STATS_BOX = 40;
    private static final int DROP_SHADOW = 10;
    private static final int TITLE_FONT = 25;
    private Pair<Double, Double> mousePosition;
//    private static final double DIALOG_OPACITY = 0.9;

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
        final int buildingSize = 200;
        int actualCol = 0;
        this.buildingPane.getChildren().clear();
        final Image img = new Image(ResourceManager.load(kingdom.getPath()).toExternalForm());
        final ImageView kingButton = new ImageView(img);
        kingButton.setFitHeight(buildingSize);
        kingButton.setFitWidth(buildingSize);
        kingButton.setOnMouseExited(e -> {
           onMouseExited(kingButton, Optional.empty());
        });

        kingButton.setOnMouseEntered(e -> {
            onMouseEntered(kingButton, Optional.of(Color.WHITE));
            this.mousePosition = Pair.createPair(kingButton.getLayoutX(), kingButton.getLayoutY());
            this.controller.pressOnKingdom();
        });

        this.buildingPane.getColumnConstraints().get(0).setHalignment(HPos.CENTER);
        this.buildingPane.add(kingButton, actualCol, actualRow);
        actualCol++;
        for (final Map.Entry<BuildingType, Pair<ImageInfo, Boolean>> building : buildings.entrySet()) {
            final Image buildImg = new Image(ResourceManager.load(building.getValue().getX().getPath()).toExternalForm());
            final ImageView buildButton = new ImageView(buildImg);
            buildButton.setFitHeight(buildingSize);
            buildButton.setFitWidth(buildingSize);
            buildButton.setOnMouseEntered(e -> {
                onMouseEntered(buildButton, Optional.of(Color.WHITE));
                this.mousePosition = Pair.createPair(buildButton.getLayoutX(), buildButton.getLayoutY());
                this.controller.pressOnBuilding(building.getKey());
             });
            buildButton.setOnMouseExited(e -> {
                onMouseExited(buildButton, Optional.empty());
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
    /**
     * 
     */
    @FXML
    public void initialize() {
        this.backMenuButton.setOnMouseEntered(e -> {
            onMouseEntered(this.backMenuButton, Optional.of(Color.WHITE));
        });
        this.backMenuButton.setOnMouseExited(e -> {
            onMouseExited(this.backMenuButton, Optional.empty());
        });
        this.statsImg.setOnMouseEntered(e -> {
            onMouseEntered(this.statsImg, Optional.of(Color.WHITE));
        });
        this.statsImg.setOnMouseExited(e -> {
            onMouseExited(this.statsImg, Optional.of(Color.BLACK));
        });
        titleText.setText(Utility.getTitle());
        titleText.setFont(Utility.titleFont(TITLE_FONT));
        String fileName = ResourceManager.load(Images.BACK_HOME_PICTURE.getPath()).toExternalForm();
        final Image backImg = new Image(fileName);
        final ImageView backButton = new ImageView(backImg);
        backButton.setFitWidth(BACK_WIDTH);
        backButton.setFitHeight(BACK_HEIGHT);
        this.backMenuButton.setBackground(null);
        this.backMenuButton.setGraphic(backButton);
        fileName = ResourceManager.load(Images.STATS_ICON.getPath()).toExternalForm();
        final Image imgStats = new Image(fileName);
        final ImageView statsView = new ImageView(imgStats);
        onMouseExited(this.statsImg, Optional.of(Color.BLACK));
        statsView.setFitWidth(STATS_BOX);
        statsView.setFitHeight(STATS_BOX);
        this.statsImg.setBackground(null);
        this.statsImg.setGraphic(statsView);
        this.statsPane.setVgap(1);
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
        this.pop.hide();
        this.controller.goOnMenu();
    }

    /**
     * 
     * @param controller 
     */
    public void setController(final WorldController controller) {
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
        if ((this.pop.getAnchorX() != this.mousePosition.getX()) || (this.pop.getAnchorY() != this.mousePosition.getY()) || !this.pop.isShowing()) {
            initBuildingStage();
            final Parent p = new ParentDialog(controller, Optional.of(building), dialog, this.pop);
            this.pop.getContent().addAll(p);
            this.pop.show(this.buildingPane.getScene().getWindow());
        }
    }

    /**
     * 
     * @param dialog 
     */
    public void showBuildingDialog(final Dialog dialog) {
        if ((this.pop.getAnchorX() != this.mousePosition.getX()) || (this.pop.getAnchorY() != this.mousePosition.getY()) || !this.pop.isShowing()) {
          initBuildingStage();
          final Parent p = new ParentDialog(controller, Optional.empty(), dialog, pop);
          this.pop.getContent().add(p);
          this.pop.show(this.buildingPane.getScene().getWindow());
        }
    }

    private void onMouseExited(final Node n, final Optional<Color> c) {
        if (c.isPresent()) {
            final DropShadow dropS = new DropShadow(DROP_SHADOW, c.get());
            dropS.setInput(new Glow());
            n.setEffect(dropS);
        } else {
            n.setEffect(null);
        }
    }

    private void onMouseEntered(final Node n, final Optional<Color> c) {
        final DropShadow dropS = new DropShadow(DROP_SHADOW, c.get());
        dropS.setInput(new Glow());
        n.setEffect(dropS);
    }
}
