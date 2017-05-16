package home.view.world.fx;

import java.util.Optional;

import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
import home.model.building.BuildingType;
import javafx.stage.Popup;
/**
 *
 *
 */
public class BuildingDialogParent extends DialogParent {

    /**
     * 
     * @param controller 
     * @param of 
     * @param dialog 
     * @param pop 
     */
    public BuildingDialogParent(final WorldObserver controller, final Optional<BuildingType> of, final Dialog dialog, final Popup pop) {
        super(dialog, pop);
        this.getController().setStart(true);
        this.getController().setBuildingController(controller, of);
    }

}
