package home.view.world.fx;

import java.util.Optional;

import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
import javafx.stage.Popup;
/**
 * 
 *
 */
public class KingdomDialogParent extends DialogParent {
    /**
     * 
     * @param controller 
     * @param dialog 
     * @param pop 
     */
    public KingdomDialogParent(final WorldObserver controller, final Dialog dialog, final Popup pop) {
        super(dialog, pop);
        this.getController().setStart(false);
        this.getController().setBuildingController(controller, Optional.empty());
    }

}
