package home.view.world.fx;

import java.io.IOException;
import java.util.Optional;

import home.controller.WorldController;
import home.controller.dialog.Dialog;
import home.model.building.BuildingType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * class who implement a dialog to start quiz or upgrade a building.
 */
public class ParentDialog extends Parent {

    /**
     * create a parent for a general building dialog.
     * @param controller 
     * @param building 
     * @param dialog 
     */
    public ParentDialog(final WorldController controller, final Optional<BuildingType> building, final Dialog dialog) {
        super();
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("buildingInfoDialog.fxml"));
        FXMLInfoBuildingController c = new FXMLInfoBuildingController();
        try {
            final Parent p = fxmlLoader.load();
            this.getChildren().add(p);
            c = fxmlLoader.<FXMLInfoBuildingController>getController();
            c.setBuildingController(controller, building);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (building.isPresent()) {
            c.setStart(true);
        } else {
            c.setStart(false);
        }
        c.setUpgrade(!dialog.levelUpEnabled());
        c.setExperience(dialog.getExperience());
        c.setLevel(dialog.getLevel());
        c.setName(dialog.getName());
    }
}
