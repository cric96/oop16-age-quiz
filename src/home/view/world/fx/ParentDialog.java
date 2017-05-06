package home.view.world.fx;

import java.io.IOException;

import home.controller.WorldController;
import home.controller.dialog.Dialog;
import home.model.building.BuildingType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class ParentDialog extends Parent{
    private FXMLInfoBuildingController c;

    public ParentDialog(WorldController controller, final BuildingType building, final Dialog dialog, Stage stageBuilding) {
        super();
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("buildingInfoDialog.fxml"));
        try {
            Parent p = fxmlLoader.load();
            this.getChildren().add(p);
            c = fxmlLoader.<FXMLInfoBuildingController>getController();
            c.setBuildingController(controller, building);
        } catch (IOException e) {
            e.printStackTrace();
        }
        c.setStart(true);
        c.setUpgrade(!dialog.levelUpEnabled());
        c.setExperience(dialog.getExperience());
        c.setLevel(dialog.getLevel());
        c.setName(building.toString());
    }

    public ParentDialog(WorldController controller, final Dialog dialog, Stage stageBuilding) {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("buildingInfoDialog.fxml"));
        try {
            Parent p = fxmlLoader.load();
            this.getChildren().add(p);
            c = fxmlLoader.<FXMLInfoBuildingController>getController();
            c.setBuildingController(controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
        c.setStart(false);
        c.setUpgrade(!dialog.levelUpEnabled());
        c.setExperience(dialog.getExperience());
        c.setLevel(dialog.getLevel());
        c.setName("Castle");
    }
}
