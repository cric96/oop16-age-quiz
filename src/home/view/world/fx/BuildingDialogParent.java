package home.view.world.fx;

import java.util.Arrays;
import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
import home.model.building.BuildingType;
import javafx.scene.control.Button;
import javafx.stage.Popup;

/**
 *sub class to create a general building pop up parent.
 */
public class BuildingDialogParent extends DialogParent {

    /**
     * 
     * @param controller 
     *          observer of this view
     * @param building 
     *          buildig representer by this parent
     * @param dialog 
     *          information showed in this dialog
     * @param pop 
     *          container of this parent
     */
    public BuildingDialogParent(final WorldObserver controller, final BuildingType building, final Dialog dialog,
            final Popup pop) {
        super(dialog, pop);
        final Button start = new Button(this.getBundle().getString("STRQUIZ"));
        start.setOnMouseClicked(e -> {
            pop.hide();
            controller.createQuiz(building);
        });
        final Button upgrade = new Button(this.getBundle().getString("UPGRADE"));
        upgrade.setOnMouseClicked(e -> {
            pop.hide();
            controller.nextLevel(building);
        });
        upgrade.setDisable(!dialog.levelUpEnabled());
        this.getController().setButtonBox(Arrays.asList(upgrade, start));
    }

}
