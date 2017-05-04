package home.view.world.fx;

import java.util.Map;

import home.controller.WorldController;
import home.utility.ResourceManager;
import home.utility.Utility;
import home.view.fx.Images;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class FXMLControllerWorld {
    private WorldController controller;
    private static final int BACK_WIDTH = 40;
    private static final int BACK_HEIGHT = 30;
    private static final int STATS_BOX = 40;
    private static final int DROP_SHADOW = 10;
    private static final int TITLE_FONT = 20;
    private ImageView statsView;

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

    /**
     * @param profileName the profileName to set
     */
    public void setProfileName(final String profileName) {
        this.profileName.setText(profileName);
    }

    /**
     * @param statusScose Map of status
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
            final ProgressBar bar = new ProgressBar(f.doubleValue());
            this.statsPane.add(bar, barCol, actualRow);
            actualRow++;
        }
        this.statsPane.autosize();
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
    private void overInStats() {
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

}
